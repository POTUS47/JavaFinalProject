package com.finalproject.service;
import com.finalproject.DTO.ProductDTOs.*;
import com.finalproject.DTO.Result;
import com.finalproject.exception.BusinessTagException;
import com.finalproject.model.*;
import com.finalproject.repository.*;
import com.finalproject.util.SnowflakeIdGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final SnowflakeIdGenerator idGenerator;

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductImageRepository productImageRepository;
    @Autowired
    private StoreBusinessDirectionRepository storeBusinessDirectionRepository;
    @Autowired
    private ProductDetailRepository productDetailRepository;
    @Autowired
    private ImageService imageService;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private SubCategoryRepository subCategoryRepository;


    @Value("${api.base-url}")
    private String baseUrl;

    @Autowired
    private RestTemplate restTemplate;

    ProductService(){
        idGenerator = new SnowflakeIdGenerator();
    }

    public Result<String> addProduct( String userId,addProductDTO newProduct) {
        // 检查tag和subtag
        Result<String> result = checkTagAndSubTag(newProduct.getTag(),newProduct.getSubTag());
        if(result.getCode()!=200){
            return result;
        }
        String combinedTag=result.getData();

        // 创建并保存商品实体
        Result<String> res=createAndSaveProduct(newProduct,userId);
        if(res.getCode()==404){
            return Result.error(404,"不存在商家所以保存失败");
        }

        // 管理商家的运营方向
        manageStoreBusinessDirection(combinedTag,userId);
        return Result.success(res.getData());
    }

    private Result<String> checkTagAndSubTag(String tag,String subTag) {
        // 检查tag是否在category表中存在此category_name
        Optional<Category> categoryOpt = categoryRepository.findByCategoryName(tag);
        if (categoryOpt.isEmpty()) {
            return Result.error(404, "商品分类错误: " + tag);
        }

        // 检查subTag是否在sub_category的subcategory_id中存在
        Optional<SubCategory> subCategoryOpt = subCategoryRepository.findBySubCategoryId(subTag);
        if (subCategoryOpt.isEmpty()) {
            return Result.error(404, "商品小分类错误: " + subTag);
        }

        // 存储小分类对应的category_name
        SubCategory subCategory = subCategoryOpt.get();
        String categoryName = categoryOpt.get().getCategoryName();

        // 拼接tag和小分类的name并放在result的data中返回
        String resultData = categoryName + tag;

        return Result.success(resultData);

    }

    public Result<String> addPhoto(List<MultipartFile> files, String productId) throws IOException {
        for(MultipartFile file : files){
            Result<String> result=imageService.saveImage(file,"商品图片");
            if(result.getCode()==200){
                String picId=result.getData();

                //把图片id和商品id填入数据库Product_image表中
                ProductImage productImage = new ProductImage();
                productImage.setProductId(productId);
                productImage.setImageId(picId);
                // 保存到数据库
                productImageRepository.save(productImage);
            }
            else{
                return Result.error(500,"上传图片失败");
            }
        }
        return Result.success();
    }

    public Result<String> addDesPic(List<uploadDesPic> files, String productId) throws IOException {
        for(uploadDesPic file : files){
            Result<String> result=imageService.saveImage(file.getPic(),"商品详情");
            if(result.getCode()==200){
                String picId=result.getData();

                //把图片id和商品id填入数据库Product_image表中
                ProductDetail productDetail = new ProductDetail();
                productDetail.setProductId(productId);
                productDetail.setImageId(picId);
                productDetail.setDescription(file.getDescription());
                // 保存到数据库
                productDetailRepository.save(productDetail);
            }
            else{
                return Result.error(500,"上传图片失败");
            }
        }
        return Result.success();
    }

    private Optional<Store> getStoreFromSubsystem(String storeId){
        String url = baseUrl + "/api/users/store/" + storeId;
        ResponseEntity<Optional<Store>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                } );
        return response.getBody();
    }

    private Boolean getProductMarkFromSubsystem(String productId,String userId){
        String url = baseUrl + "/api/shopping/internal/is-product-bookmarked/" + userId+"/"+productId;
        ResponseEntity<Result<Boolean>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                } );
        return response.getBody().getData();
    }

    private Boolean getStoreMarkFromSubsystem(String userId,String storeId){
        String url = baseUrl + "/api/shopping/internal/is-store-bookmarked/" + userId+"/"+storeId;
        ResponseEntity<Result<Boolean>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                } );
        return response.getBody().getData();
    }

    private Result<String> createAndSaveProduct(addProductDTO newProduct,String storeId) {
        String productId = "p" + idGenerator.nextId();

        // 调用用户子系统的 StoreController API 获取 Store 信息
        Optional<Store> result = getStoreFromSubsystem(storeId);
        if(result.isEmpty()){
            return Result.error(404);
        }
        Store store=result.get();
        Product product = new Product(productId,
                newProduct.getProductName(),
                newProduct.getProductPrice(),
                newProduct.getQuantity(),
                newProduct.getTag(),
                newProduct.getDescription(),
                newProduct.getSubTag(),
                store,storeId);

        productRepository.save(product);

        return Result.success(productId);
    }

    public Result<productDetailDTO>getProductDetail(String productId,String userId) {
        //商品相关
        Optional<Product> product=productRepository.findById(productId);
        if(product.isEmpty()){
            return Result.error(404,"不存在该id的商品");
        }
        Product pro = product.get();
        //商家相关信息
        Store store=pro.getStore();

        //商品图片相关信息
        List<String> imageIds = productImageRepository.findByProductId(productId).stream()
                .map(ProductImage::getImageId) // 假设ProductImage类有getImageId方法
                .toList();


        //商品详情信息
        List<DesPic> productDetails = productDetailRepository.findByProductId(productId).stream()
                .map(detail -> new DesPic(detail.getImageId(), detail.getDescription()))  // 映射为DesPic对象
                .collect(Collectors.toList());


        //返回结果
        productDetailDTO res=new productDetailDTO(pro.getProductName(),
                pro.getProductPrice(),
                pro.getTag(),
                pro.getSubCategory(),
                pro.getDescription(),
                pro.getStoreTag(),
                pro.getQuantity(),
                store.getStoreName(),
                store.getAccountId(),
                store.getAddress(),
                store.getStoreScore(),
                getProductMarkFromSubsystem(productId,userId),
                getStoreMarkFromSubsystem(store.getAccountId(),userId),
                imageIds,
                productDetails);


        return Result.success(res);
    }

    public Optional<Product> getProductById(String productId) {
        return productRepository.findById(productId);
    }

    public List<Product> getProductsByStoreId(String storeId) {
        return productRepository.findByStoreId(storeId);
    }

    public List<ProductImage> getProductImagesByProductId(String productId) {
        return productImageRepository.findByProductId(productId);
    }

    private void manageStoreBusinessDirection(String tag, String storeId) {
        // 根据 storeId 和 tag 在 store_business_direction 表中查找是否存在
        Optional<StoreBusinessDirection> storeBusinessDirectionOpt = storeBusinessDirectionRepository
                .findByStoreIdAndBusinessTag(storeId, tag);

        if (storeBusinessDirectionOpt.isPresent()) {
            StoreBusinessDirection storeBusinessDirection = storeBusinessDirectionOpt.get();
            storeBusinessDirection.setLinkCount(storeBusinessDirection.getLinkCount() + 1);
            storeBusinessDirectionRepository.save(storeBusinessDirection);
        } else {
            StoreBusinessDirection newStoreBusinessDirection = new StoreBusinessDirection(storeId,tag,1);
            storeBusinessDirectionRepository.save(newStoreBusinessDirection);
        }
    }

}

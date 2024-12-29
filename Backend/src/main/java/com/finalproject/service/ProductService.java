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
import java.util.*;
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
        manageStoreBusinessDirection(combinedTag,userId,combinedTag,false);
        return Result.success(res.getData());
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

        // 商品图片相关信息
        List<String> imageUrls = productImageRepository.findByProductId(productId).stream()
                .map(image -> baseUrl + "/images/" + image.getImageId())  // 拼接为完整的图片URL
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
                imageUrls,
                productDetails);


        return Result.success(res);
    }

    public Result<List<ShowProductDTO>> searchInStore(String storeId,String keyword){
        String likePattern = "%" + String.join("%", keyword.split("")) + "%";
        List<Product> products = productRepository.searchProducts(storeId, keyword, likePattern);
        List<ShowProductDTO> result = new ArrayList<>();


        //todo 默认图片id
        for (Product product : products) {
            Optional<ProductImage> imageOptional = productImageRepository.findFirstByProductId(product.getProductId());
            String imageId = imageOptional.map(ProductImage::getImageId).orElse("1");
            ShowProductDTO dto = new ShowProductDTO(product.getProductId(),
                    product.getProductName(),
                    product.getProductPrice(),
                    product.getQuantity(),
                    product.getTag(),
                    product.getSubCategory(),
                    product.getDescription(),
                    imageId,
                    product.getStoreTag());
            result.add(dto);
        }
        return Result.success(result);
    }

    public Result<String> deleteProImage(String productId,String imageId) {
        Optional<ProductImage> productImage=productImageRepository.findById(imageId);
        if(productImage.isEmpty()){
            return Result.error(404,"不存在该图片id");
        }
        else if(!productImage.get().getProductId().equals(productId)){
            return Result.error(403,"不是该商品的图片，无权操作");
        }
        return imageService.deleteImage(imageId);
    }

    public Result<List<DesPic>> getDesPic(String productId) {
        Collection<ProductDetail> productDetails = productDetailRepository.findByProductId(productId);
        List<DesPic> pro = new ArrayList<>();
        for(ProductDetail productDetail : productDetails){
            String id=productDetail.getImageId();
            String url=baseUrl+"/images/"+id;
            DesPic ne=new DesPic(url,productDetail.getDescription());
            pro.add(ne);
        }
        if(pro.isEmpty()){
            String defaultDes="本商品暂无详情介绍";
            String defaultId="1";
            String url = baseUrl + "/images/" + defaultId;
            DesPic temp=new DesPic(url,defaultDes);
            pro.add(temp);
        }
        return Result.success(pro);
    }

    public Result<String> updateDescription(UpdateDescriptionRequest request){
        Optional<ProductDetail> res=productDetailRepository.findById(request.getImageId());
        if(res.isEmpty()){
            return Result.error(404,"不存在该图片");
        }
        ProductDetail productDetail=res.get();
        productDetail.setDescription(request.getDescription());
        productDetailRepository.save(productDetail);
        return Result.success();
    }

    public Result<String> deleteDetail(String imageId){
        Optional<ProductDetail> res=productDetailRepository.findById(imageId);
        if(res.isEmpty()){
            return Result.error(404,"不存在该id");
        }
        ProductDetail productDetail=res.get();
        String productId=productDetail.getProductId();
        productDetailRepository.delete(productDetail);
        return Result.success(productId);
    }

    public Optional<Product> getProductById(String productId) {
        return productRepository.findById(productId);
    }

    private void manageStoreBusinessDirection(String tag, String storeId,String oldTag,Boolean isUpdate) {
        if(isUpdate){
            Optional<StoreBusinessDirection> old=storeBusinessDirectionRepository.
                    findByStoreIdAndBusinessTag(storeId,oldTag);
            StoreBusinessDirection temp=old.get();
            int tempCount=temp.getLinkCount()-1;
            storeBusinessDirectionRepository.updateLinkCount(tempCount,storeId,oldTag);
        }
        // 根据 storeId 和 tag 在 store_business_direction 表中查找是否存在
        Optional<StoreBusinessDirection> storeBusinessDirectionOpt = storeBusinessDirectionRepository
                .findByStoreIdAndBusinessTag(storeId, tag);

        if (storeBusinessDirectionOpt.isPresent()) {
            StoreBusinessDirection storeBusinessDirection = storeBusinessDirectionOpt.get();
            int count=storeBusinessDirection.getLinkCount() + 1;
            storeBusinessDirectionRepository.updateLinkCount(count,storeId,tag);
        } else {
            StoreBusinessDirection newStoreBusinessDirection = new StoreBusinessDirection(storeId,tag,1);
            storeBusinessDirectionRepository.save(newStoreBusinessDirection);
        }
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
//        Optional<Store> result = getStoreFromSubsystem(storeId);
//        if(result.isEmpty()){
//            return Result.error(404);
//        }
//        Store store=result.get();
        Product product = new Product(productId,
                newProduct.getProductName(),
                newProduct.getProductPrice(),
                newProduct.getQuantity(),
                newProduct.getTag(),
                newProduct.getDescription(),
                newProduct.getSubTag(),
                storeId,
                newProduct.getStoreTag());

        productRepository.save(product);

        return Result.success(productId);
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
        String categoryName = subCategory.getSubcategoryName();

        // 拼接tag和小分类的name并放在result的data中返回
        String resultData = tag+categoryName  ;

        return Result.success(resultData);

    }

    //todo 数据库存默认图片
    public Result<List<String>> getProductImages(String productId) {
        List<ProductImage> images=getProductImagesByProductId(productId);
        List<String> res=new ArrayList<>();
        if(images.isEmpty()){
            String defaultId="1";
            String url = baseUrl + "/images/" + defaultId;
            res.add(url);
        }
        for(ProductImage image:images){
            String url = baseUrl + "/images/" + image.getImageId();
            res.add(url);
        }
        return Result.success(res);
    }

    public Result<String> editPro(ShowProductDTO dto,String storeId) {

        Optional<Product> optionalProduct = productRepository.findById(dto.getProductId());

        if (optionalProduct.isEmpty()) {
            return Result.error(404, "商品未找到");
        }
        Product product = optionalProduct.get();
        if(!product.getStoreId().equals(storeId)){
            return Result.error(403,"无权操作，不是本商家的商品");
        }

        //检查tag
        Result<String> result = checkTagAndSubTag(dto.getTag(),dto.getSubTag());
        if(result.getCode()!=200){
            return result;
        }
        String combinedTag=result.getData();

        //更新商品
        Optional.ofNullable(dto.getProductName()).ifPresent(product::setProductName);
        Optional.ofNullable(dto.getProductPrice()).ifPresent(product::setProductPrice);
        Optional.of(dto.getQuantity()).ifPresent(product::setQuantity);
        Optional.ofNullable(dto.getTag()).ifPresent(product::setTag);
        Optional.ofNullable(dto.getSubTag()).ifPresent(product::setSubCategory);
        Optional.ofNullable(dto.getDescription()).ifPresent(product::setDescription);
        Optional.ofNullable(dto.getStoreTag()).ifPresent(product::setStoreTag);

        productRepository.save(product);

        //商家运营方向管理
        manageStoreBusinessDirection(combinedTag,storeId,combinedTag,false);
        return Result.success(product.getProductId());
    }

    public List<Product> getProductsByStoreId(String storeId) {
        return productRepository.findByStoreId(storeId);
    }

    public List<ProductImage> getProductImagesByProductId(String productId) {
        return productImageRepository.findByProductId(productId);
    }

    public Integer reduceProductById(String productId) {
        Optional<Product> product=productRepository.findById(productId);
        if(product.isEmpty()){
            return 404;
        }
        Product pro = product.get();
        pro.setQuantity(pro.getQuantity()-1);
        productRepository.save(pro);
        return 200;
    }

    public Result<List<ProductDTO>> getAllProduct(String storeId, String viewType) {
        List<Product> res=productRepository.findByStoreId(storeId);
        List<ProductDTO> response=new ArrayList<>();
        if(res.isEmpty()){
            return Result.error(404,"本店没有商品");
        }
        for(Product product:res){
            ProductDTO dto=new ProductDTO();
            dto.setProductName(product.getProductName());
            dto.setProductPrice(product.getProductPrice());
            dto.setQuantity(product.getQuantity());
            dto.setTag(product.getTag());
            dto.setDescription(product.getDescription());
            dto.setStoreTag(product.getStoreTag());
            dto.setProductId(product.getProductId());
            response.add(dto);
        }
        return Result.success(response);
    }

    public  Result<List<CatSubDTO>> getAllCategoriesWithSubcategories() {
        // 查询所有大分类
        List<Category> largeCategories = categoryRepository.findAll();

        // 为每个大分类查询其子分类
        List<CatSubDTO> result = new ArrayList<>();

        for (Category lc : largeCategories) {
            List<SubCategory> subCategories = subCategoryRepository.findBycategoryName(lc.getCategoryName());

            List<CategoryDTO> subCategoryDTOs = subCategories.stream()
                    .map(sc -> new CategoryDTO(sc.getSubcategoryName(), sc.getSubcategoryId()))
                    .collect(Collectors.toList());

            CatSubDTO temp= new CatSubDTO(lc.getCategoryName(),subCategoryDTOs);
            result.add(temp);
        }

        return Result.success(result);
    }

    public Result<List<GCDDTO>> getProductsBySubTagId(String subId){
        String Name = switch (subId) {
            case "00000" -> "匠心器皿";
            case "01000" -> "织艺锦缎";
            case "02000" -> "墨香文房";
            case "03000" -> "金彩流光";
            case "04000" -> "逸趣风雅";
            default -> "";
        };

// 如果 Name 为空，则直接根据 subId 查询
        List<Product> products;
        if (!Name.isEmpty()) {
            // 根据 Tagname 查询商品
            products = productRepository.findBytag(Name);
        } else {
            // 如果 Name 为空，则直接根据 subId 查询商品
            products = productRepository.findBysubCategory(subId);  // 根据 subId 查询商品
        }

        List<GCDDTO> response = new ArrayList<>();

        for (Product pro : products) {
            GCDDTO product = new GCDDTO();
            product.setProductName(pro.getProductName());
            product.setProductPrice(pro.getProductPrice());
            product.setProductId(pro.getProductId());

            Optional<ProductImage> temp = productImageRepository.findFirstByProductId(pro.getProductId());
            if (temp.isPresent()) {
                ProductImage image = temp.get();
                String imageId = (image.getImageId() != null) ? image.getImageId() : "1";
                String url = baseUrl + "/images/" + imageId;
                product.setImageUrl(url);
            } else {
                String url = baseUrl + "/images/1"; // 默认图片
                product.setImageUrl(url);
            }
            response.add(product);
        }

        return Result.success(response);


    }


}

package com.finalproject.service;


import com.finalproject.DTO.ProductDTOs.*;
import com.finalproject.DTO.Result;
import com.finalproject.exception.BusinessTagException;
import com.finalproject.model.*;
import com.finalproject.repository.ProductRepository;
import com.finalproject.repository.ProductImageRepository;
import com.finalproject.repository.StoreBusinessDirectionRepository;
import com.finalproject.repository.SubCategoryRepository;
import com.finalproject.util.SnowflakeIdGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final SnowflakeIdGenerator idGenerator;

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductImageRepository productImageRepository;
    private StoreBusinessDirectionRepository storeBusinessDirectionRepository;
    private SubCategoryRepository subCategoryRepository;

    @Value("${api.base-url}")
    private String baseUrl;

    @Autowired
    private RestTemplate restTemplate;

    ProductService(){
        idGenerator = new SnowflakeIdGenerator();
    }

    public Result<String> addProduct( String userId,addProductDTO newProduct) {
        // 创建并保存商品实体
        if(createAndSaveProduct(newProduct,userId).getCode()==404){
            return Result.error(404,"不存在商家所以保存失败");
        }

        // 处理商品详情
        //saveProductDetails(product.getId(), newProduct.getPicDes());

        // 管理商家的运营方向
        //manageStoreBusinessDirection(newProduct);

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
                newProduct.getSubTag(),store);

        productRepository.save(product);

        return Result.success();
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

        //商品图片相关信息 todo
        //商品详情信息 todo

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
                getStoreMarkFromSubsystem(store.getAccountId(),userId));


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


//    private void saveProductDetails(String productId, List<ProductDetailDTO> details) {
//        if (details != null && !details.isEmpty()) {
//            details.forEach(detail -> {
//                ProductDetail pd = new ProductDetail(productId, detail);
//                productDetailRepository.save(pd);
//            });
//        }
//    }

//    private void manageStoreBusinessDirection(addProductDTO newProduct)  {
//        Optional<SubCategory> subCategory = subCategoryRepository.findById(newProduct.getSubTag());
//        if (subCategory.isEmpty()) {
//            throw new BusinessTagException("SUBCATEGORY_NOT_FOUND", "子类别不存在");
//        }
//
//        // 检查该商家的TAG是否已经存在
//        String businessTag = newProduct.getTag() + subCategory.get().getSubcategoryName();
//        Optional<StoreBusinessDirection> existingTag = storeBusinessDirectionRepository.findByStoreIdAndBusinessTag(
//                newProduct.getStoreId(), businessTag);
//
//        existingTag.orElseGet(() -> {
//            StoreBusinessDirection newStoreTag = new StoreBusinessDirection(
//                    newProduct.getStoreId(), businessTag, 0);
//            return storeBusinessDirectionRepository.save(newStoreTag);
//        }).addLinkCount();
//
//    }
}

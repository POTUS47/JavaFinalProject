package com.finalproject.service;
import com.finalproject.DTO.FavouriteDTOs.*;
import com.finalproject.DTO.ImageModels;
import com.finalproject.DTO.Result;
import com.finalproject.model.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.finalproject.repository.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.*;


@Service
public class FavouriteService {
    private final BookmarkStoreRepository bookmarkStoreRepository;
    private final BookmarkProductRepository bookmarkProductRepository;
    private final RestTemplate restTemplate;

    // 构造函数注入
    public FavouriteService(
            BookmarkStoreRepository bookmarkStoreRepository,
            BookmarkProductRepository bookmarkProductRepository,
            RestTemplate restTemplate) {
        this.bookmarkStoreRepository = bookmarkStoreRepository;
        this.bookmarkProductRepository = bookmarkProductRepository;
        this.restTemplate = restTemplate;
    }

    @Value("${api.base-url}")
    private String baseUrl;

    @Transactional
    public Optional<Product> getProductById(String productId) {
        String url = baseUrl + "/api/productController/product/" + productId;
        ResponseEntity<Optional<Product>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );
        return response.getBody();
    }

    @Transactional
    public List<Product> getProductsByStoreId(String storeId) {
        if (storeId == null || storeId.trim().isEmpty()) {
            return Collections.emptyList();
        }
        String url = baseUrl + "/api/productController/products/" + storeId;
        ResponseEntity<List<Product>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Product>>() {}
        );
        return response.getBody();
    }

    @Transactional
    public Optional<Store> getStoreById(String storeId) {
        if (storeId == null || storeId.trim().isEmpty()) {
            return Optional.empty();
        }
        String url = baseUrl + "/api/users/getStore/" + storeId;
        ResponseEntity<Optional<Store>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );
        return Optional.ofNullable(response.getBody()).orElse(Optional.empty());
    }

    @Transactional
    public Optional<Buyer> getBuyerById(String buyerId) {
        String url = baseUrl + "/api/users/buyer/" + buyerId;
        ResponseEntity<Optional<Buyer>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );
        return response.getBody();
    }

    // 可以根据productId获取商品的所有图片的url!
    @Transactional
    public List<String> getProductImagesById(String productId) {
        // 获取商品的图片信息
        String url = baseUrl + "/api/productController/productImages/" + productId;
        ResponseEntity<List<ProductImage>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ProductImage>>() {
                });

        List<ProductImage>productImages = response.getBody();
        List<String>imageUrls=new ArrayList<>();
        if (productImages != null) {
            for (ProductImage productImage : productImages){
                String imageUrl=baseUrl+"/images/"+productImage.getImageId();
                imageUrls.add(imageUrl);
            }
        }
        if(imageUrls.isEmpty()){
            imageUrls.add(baseUrl+"/images/1");
        }
        return imageUrls;
    }

    @Transactional
    public Result<List<FavouriteStoresDTO>>getFavouriteStores(String userId){
        if (userId == null || userId.trim().isEmpty()) {
            return Result.error(400, "用户ID不能为空");
        }
        // 查询该用户收藏的所有店铺
        //list的是model
        List<BookmarkStore> bookmarkedStores = bookmarkStoreRepository.findByBuyerId(userId);
        if (bookmarkedStores.isEmpty()) {
            return Result.error(404, "没有收藏任何店铺");
        }

        // 构造返回的DTO列表
        List<FavouriteStoresDTO> favouriteStores = new ArrayList<>();

        // 遍历用户“收藏店铺”的model
        for (BookmarkStore bookmarkStore : bookmarkedStores) {
            System.out.println("-----------------");
            System.out.println(bookmarkStore.getStoreAccountId());
            // 初始化DTO
            FavouriteStoresDTO favouriteStoreDTO = new FavouriteStoresDTO();
            favouriteStoreDTO.setBuyerId(bookmarkStore.getBuyerAccountId());
            favouriteStoreDTO.setStoreId(bookmarkStore.getStoreAccountId());

            // 获取店铺信息
            Store store = getStoreById(bookmarkStore.getStoreAccountId()).get();
            favouriteStoreDTO.setStoreName(store.getStoreName());
            favouriteStoreDTO.setStoreScore(store.getStoreScore());

            // 获取该店铺下的商品信息
            List<ProductDTO> productDTOList = new ArrayList<>();
            List<Product> products = getProductsByStoreId(store.getAccountId());

            if (products != null) {
                for (Product product : products) {
                    ProductDTO productDTO = new ProductDTO();
                    productDTO.setProductId(product.getProductId());
                    productDTO.setProductName(product.getProductName());
                    productDTO.setProductPrice(product.getProductPrice());

                    // 获取商品的图片信息
                    List<String> productImageUrls = getProductImagesById(product.getProductId());
                    if (!productImageUrls.isEmpty()) {
                        productDTO.setProductPic(productImageUrls.getFirst());
                    }

                    productDTOList.add(productDTO);
                }
            }

            // 设置该店铺下的所有商品信息
            favouriteStoreDTO.setProducts(productDTOList);
            favouriteStores.add(favouriteStoreDTO);
        }

        return Result.success(favouriteStores);
    }

    @Transactional
    public Result<List<FavouriteProductsDTO>>getFavouriteProducts(String userId){
        List<BookmarkProduct> bookmarkedProducts = bookmarkProductRepository.findByBuyerId(userId);
        if (bookmarkedProducts.isEmpty()) {
            return Result.error(404, "没有收藏任何商品");
        }

        List<FavouriteProductsDTO> favouriteProducts = new ArrayList<>();
        for(BookmarkProduct bookmarkProduct : bookmarkedProducts){
            FavouriteProductsDTO favouriteProductsDTO = new FavouriteProductsDTO();

            favouriteProductsDTO.setBuyerId(bookmarkProduct.getBuyerId());
            favouriteProductsDTO.setProductId(bookmarkProduct.getProductId());

            // 获取商品
            Product product = bookmarkProduct.getProduct();
            favouriteProductsDTO.setProductName(product.getProductName());
            favouriteProductsDTO.setProductPrice(product.getProductPrice());
            favouriteProductsDTO.setTag(product.getTag());
            favouriteProductsDTO.setQuantity(product.getQuantity());

            // 获取店铺
            Store store = product.getStore();
            favouriteProductsDTO.setStoreId(store.getAccountId());

            List<String> productImageUrls = getProductImagesById(product.getProductId());
            if (!productImageUrls.isEmpty()) {
                favouriteProductsDTO.setProductPic(productImageUrls.getFirst());
            }

            favouriteProducts.add(favouriteProductsDTO);
        }

        return Result.success(favouriteProducts);

    }


    @Transactional
    public Result<String> bookmarkStore(String userId, String storeId) {
        // 查询商家信息
        Optional<Store> storeOpt = getStoreById(storeId);
        if (storeOpt.isEmpty()) {
            return Result.error(404, "未找到商家信息");
        }

        // 收藏过则取消收藏
        boolean isBookmarked = bookmarkStoreRepository.existsByBuyerIdAndStoreId(userId, storeId);
        if (isBookmarked) {
            try {
                bookmarkStoreRepository.deleteBookmarkStore(userId, storeId);
                return Result.success(200,"取消收藏成功");
            } catch (Exception e) {
                return Result.error(500, "数据库操作异常");
            }
        }

        try {
            System.out.println("保存");
            bookmarkStoreRepository.insertBookmarkStore(userId, storeId);
            return Result.success(200,"收藏成功");
        } catch (Exception e) {
            return Result.error(500, "数据库操作异常");
        }

    }

    @Transactional
    public Result<String> bookmarkProduct(String userId, String productId) {
        if (productId == null || productId.trim().isEmpty()) {
            return Result.error(400, "商品ID不能为空");
        }
        if (userId == null || userId.trim().isEmpty()) {
            return Result.error(400, "用户ID不能为空");
        }

        Optional<Product> productOpt = getProductById(productId);
        if (productOpt.isEmpty()) {
            return Result.error(404, "未找到商品信息");
        }

        // 查询买家信息
        Optional<Buyer> buyerOpt = getBuyerById(userId);
        if (buyerOpt.isEmpty()) {
            return Result.error(404, "未找到买家信息");
        }

        // 收藏则取消收藏
        boolean isBookmarked = bookmarkProductRepository.existsByBuyerIdAndProductId(userId, productId);
        if (isBookmarked) {
            try {
                System.out.println("保存");
                bookmarkProductRepository.deleteBookmarkProduct(userId, productId);
                return Result.success(200,"取消收藏成功");
            } catch (Exception e) {
                return Result.error(500, "数据库操作异常");
            }
        }

        try {
            System.out.println("保存");
            bookmarkProductRepository.insertBookmarkProduct(userId, productId);
            // bookmarkProductRecord(userId, productId);
            return Result.success(200,"收藏成功");
        } catch (Exception e) {
            return Result.error(500, "数据库操作异常");
        }

    }

    @Transactional
    public Result<Boolean>isStoreBookmarked(String userId, String storeId){
        boolean isBookmarked = bookmarkStoreRepository.existsByBuyerIdAndStoreId(userId, storeId);
        return Result.success(isBookmarked);
    }

    @Transactional
    public Result<Boolean>isProductBookmarked(String userId, String productId){
        if (userId == null || userId.trim().isEmpty() ||
                productId == null || productId.trim().isEmpty()) {
            return Result.error(500,"用户ID或商品ID不能为空");
        }
        boolean isBookmarked = bookmarkProductRepository.existsByBuyerIdAndProductId(userId, productId);
        return Result.success(isBookmarked);
    }

    // 更新用户特征向量
    @Transactional
    public Result<String> bookmarkProductRecord(String userId, String productId) {
        Optional<Product> productOpt = getProductById(productId);
        if (productOpt.isEmpty()) {
            return Result.error(404,"收藏的商品不存在");
        }

        String url = baseUrl + "/api/productController/recommend/updateUserFeature" +
                "/"+userId+"/"+productOpt.get().getProductName()+"/"+productOpt.get().getDescription();

        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                null,
                new ParameterizedTypeReference<String>() {
                });
        return Result.success(response.getBody());
    }


}

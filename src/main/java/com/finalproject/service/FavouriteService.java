package com.finalproject.service;
import com.finalproject.DTO.FavouriteDTOs.*;
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
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class FavouriteService {
    private final StoreRepository storeRepository;
    private final ProductRepository productRepository;
    private final BuyerRepository buyerRepository;
    private final BookmarkStoreRepository bookmarkStoreRepository;
    private final BookmarkProductRepository bookmarkProductRepository;
    private final ProductImageRepository productImageRepository;
    private final RestTemplate restTemplate;

    // 构造函数注入
    public FavouriteService(StoreRepository storeRepository,
                       ProductRepository productRepository,
                       BuyerRepository buyerRepository,
                       BookmarkStoreRepository bookmarkStoreRepository,
                       BookmarkProductRepository bookmarkProductRepository,
                       ProductImageRepository productImageRepository,
                            RestTemplate restTemplate) {
        this.storeRepository = storeRepository;
        this.productRepository = productRepository;
        this.buyerRepository = buyerRepository;
        this.bookmarkStoreRepository = bookmarkStoreRepository;
        this.bookmarkProductRepository = bookmarkProductRepository;
        this.productImageRepository = productImageRepository;
        this.restTemplate = restTemplate;
    }

    @Value("${api.base-url}")
    private String baseUrl;

    // 把所有检查用户存不存在的接口都删掉了
    @Transactional
    public Result<List<FavouriteStoresDTO>>getFavouriteStores(String userId){

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
            // 初始化DTO
            FavouriteStoresDTO favouriteStoreDTO = new FavouriteStoresDTO();

            favouriteStoreDTO.setBuyerId(bookmarkStore.getBuyerAccountId());
            favouriteStoreDTO.setStoreId(bookmarkStore.getStoreAccountId());

            // 获取店铺信息
            // 有外码关系
            Store store = bookmarkStore.getStore();
            favouriteStoreDTO.setStoreName(store.getStoreName());
            favouriteStoreDTO.setStoreScore(store.getStoreScore());


            // 获取该店铺下的商品信息
            List<ProductDTO> productDTOList = new ArrayList<>();
            String storeId = bookmarkStore.getStoreAccountId();
            String url = baseUrl + "/api/productController/products/" + storeId;
            ResponseEntity<List<Product>> response = restTemplate.exchange(url, HttpMethod.GET, null,
                    new ParameterizedTypeReference<List<Product>>() {} );
            List<Product> products = response.getBody();

            if (products != null) {
                for (Product product : products) {
                    ProductDTO productDTO = new ProductDTO();
                    productDTO.setProductId(product.getProductId());
                    productDTO.setProductName(product.getProductName());
                    productDTO.setProductPrice(product.getProductPrice());

                    // 获取商品的图片信息
                    String url1 = baseUrl + "/api/productController/productImages/" + storeId;
                    ResponseEntity<List<ProductImage>> productImageResponse = restTemplate.exchange(url1, HttpMethod.GET, null,
                            new ParameterizedTypeReference<List<ProductImage>>() {} );
                    List<ProductImage> productImages = productImageResponse.getBody();

                    if (!productImages.isEmpty()) {
                        // 取第一张图片
                        String imageId = productImages.getFirst().getImageId();
                        productDTO.setProductPic("http://47.97.59.189:8080/images/"+imageId);
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

            // 获取商品的图片信息
            List<ProductImage> productImages = productImageRepository.findByProductId(product.getProductId());
            if (!productImages.isEmpty()) {
                // 取第一张图片
                String imageId = productImages.getFirst().getImageId();
                favouriteProductsDTO.setProductPic("http://47.97.59.189:8080/images/"+imageId);
            }

            favouriteProducts.add(favouriteProductsDTO);
        }

        return Result.success(favouriteProducts);

    }


    @Transactional
    public Result<String> bookmarkStore(String userId, String storeId) {

        // 查询商家信息
        System.out.println(userId + "------------------------" + storeId);

        Optional<Store> storeOpt = storeRepository.findById(storeId);
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

        // 查询商家信息
        Optional<Product> productOpt = productRepository.findById(productId);
        if (productOpt.isEmpty()) {
            return Result.error(404, "未找到商品信息");
        }

        // 查询买家信息
        Optional<Buyer> buyerOpt = buyerRepository.findById(userId);
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
        boolean isBookmarked = bookmarkProductRepository.existsByBuyerIdAndProductId(userId, productId);
        return Result.success(isBookmarked);
    }
}

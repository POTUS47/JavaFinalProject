package com.finalproject.controller;
import com.finalproject.DTO.FavouriteDTOs.*;
import com.finalproject.DTO.Result;
import com.finalproject.service.AdministratorService;
import com.finalproject.service.FavouriteService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


// 购物子系统
@RestController
@RequestMapping("/api/shopping")
public class ShoppingController {


    @Resource
    private FavouriteService favouriteService;

    // 获取用户收藏的商品
//    @GetMapping("/GetFavoriteProducts")
//    public ResponseEntity<List<FavouriteProductsDTO>> getFavoriteProducts(@RequestParam String userId) {
//        List<FavouriteProductsDTO> favouriteProducts = favouriteService.getFavoriteProducts(userId);
//        if (favouriteProducts == null || favouriteProducts.isEmpty()) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(favouriteProducts);
//    }
//
    // 获取用户收藏的店铺
    @GetMapping("/favourite/get-favourite-stores")
    public ResponseEntity<Result<List<FavouriteStoresDTO>>> getFavouriteStores(@RequestParam String userId) {
        Result<List<FavouriteStoresDTO>> response = favouriteService.getFavouriteStores(userId);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    // 获取用户收藏的商品
    @GetMapping("/favourite/get-favourite-products")
    public ResponseEntity<Result<List<FavouriteProductsDTO>>> getFavouriteProducts(@RequestParam String userId) {
        Result<List<FavouriteProductsDTO>> response = favouriteService.getFavouriteProducts(userId);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    // 收藏店铺
    @PostMapping("/favourite/bookmark-store")
    public ResponseEntity<Result<String>> bookmarkStore(@RequestBody BookmarkStoreDTO model) {
        Result<String> response = favouriteService.bookmarkStore(model.getUserId(), model.getStoreId());
        return ResponseEntity.status(response.getCode()).body(response);
    }

    // 收藏商品
    @PostMapping("/favourite/bookmark-product")
    public ResponseEntity<Result<String>> bookmarkProduct(@RequestBody BookmarkProductDTO model) {
        Result<String> response = favouriteService.bookmarkProduct(model.getUserId(), model.getProductId());
        return ResponseEntity.status(response.getCode()).body(response);
    }

    // 检查是否收藏店铺
    @GetMapping("/favourite/is-store-bookmarked")
    public ResponseEntity<Result<Boolean>> isStoreBookmarked(@RequestParam String userId, @RequestParam String storeId) {
        Result<Boolean>response = favouriteService.isStoreBookmarked(userId, storeId);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    // 检查是否收藏商品
    @GetMapping("/favourite/is-product-bookmarked")
    public ResponseEntity<Result<Boolean>> isProductBookmarked(@RequestParam String userId, @RequestParam String productId) {
        Result<Boolean>response = favouriteService.isProductBookmarked(userId, productId);
        return ResponseEntity.status(response.getCode()).body(response);
    }



}

package com.finalproject.controller;
import com.finalproject.DTO.FavouriteDTOs.*;
import com.finalproject.DTO.Result;
import com.finalproject.service.FavouriteService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;


// 购物子系统
@RestController
@RequestMapping("/api/shopping")
public class ShoppingController {


    @Resource
    private FavouriteService favouriteService;

    // 获取用户收藏的店铺
    @GetMapping("/favourite/get-favourite-stores")
    public ResponseEntity<Result<List<FavouriteStoresDTO>>> getFavouriteStores(Authentication authentication) {
        String userId = (String) authentication.getPrincipal();
        Result<List<FavouriteStoresDTO>> response = favouriteService.getFavouriteStores(userId);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    // 获取用户收藏的商品
    @GetMapping("/favourite/get-favourite-products")
    public ResponseEntity<Result<List<FavouriteProductsDTO>>> getFavouriteProducts(Authentication authentication) {
        String userId = (String) authentication.getPrincipal();
        Result<List<FavouriteProductsDTO>> response = favouriteService.getFavouriteProducts(userId);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    // 收藏店铺
    @PostMapping("/favourite/bookmark-store")
    public ResponseEntity<Result<String>> bookmarkStore(@RequestBody StoreIdDTO model, Authentication authentication) {
        String userId = (String) authentication.getPrincipal();
        Result<String> response = favouriteService.bookmarkStore(userId, model.getStoreId());
        return ResponseEntity.status(response.getCode()).body(response);
    }

    // 收藏商品
    @PostMapping("/favourite/bookmark-product")
    public ResponseEntity<Result<String>> bookmarkProduct(@RequestBody ProductIdDTO model, Authentication authentication) {
        String userId = (String) authentication.getPrincipal();
        Result<String> response = favouriteService.bookmarkProduct(userId,model.getProductId());
        return ResponseEntity.status(response.getCode()).body(response);
    }

    // 检查是否收藏店铺
    @GetMapping("/favourite/is-store-bookmarked")
    public ResponseEntity<Result<Boolean>> isStoreBookmarked(@RequestBody StoreIdDTO model, Authentication authentication) {
        String userId = (String) authentication.getPrincipal();
        Result<Boolean>response = favouriteService.isStoreBookmarked(userId, model.getStoreId());
        return ResponseEntity.status(response.getCode()).body(response);
    }

    // 检查是否收藏商品
    @GetMapping("/favourite/is-product-bookmarked")
    public ResponseEntity<Result<Boolean>> isProductBookmarked(@RequestBody ProductIdDTO model, Authentication authentication) {
        String userId = (String) authentication.getPrincipal();
        Result<Boolean>response = favouriteService.isProductBookmarked(userId, model.getProductId());
        return ResponseEntity.status(response.getCode()).body(response);
    }



}

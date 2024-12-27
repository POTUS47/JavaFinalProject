package com.finalproject.controller;
import com.finalproject.DTO.FavouriteDTOs.*;
import com.finalproject.DTO.OneYuanShoppingRecordDTOs.OneYuanShoppingRecordDTO;
import com.finalproject.DTO.Result;
import com.finalproject.service.FavouriteService;
import jakarta.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.finalproject.service.OneYuanShoppingRecordService;


// 购物子系统
@RestController
@RequestMapping("/api/shopping")
public class ShoppingController {


    @Resource
    private FavouriteService favouriteService;
    private OneYuanShoppingRecordService oneYuanShoppingRecordService;

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
    
    // 查看参与的一元购
    // @GetMapping("/participants/{buyerId}")
    // public ResponseEntity<Result<List<OneYuanShoppingRecordDTO>>> getParticipatedRecords(Authentication authentication) {
    //     String userId = (String) authentication.getPrincipal();
    //     Result<List<OneYuanShoppingRecordDTO>> response = oneYuanShoppingRecordService.getParticipatedRecords(userId);
    //     return ResponseEntity.status(response.getCode()).body(response);
    // }

}

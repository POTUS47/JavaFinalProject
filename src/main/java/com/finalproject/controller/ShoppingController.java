package com.finalproject.controller;
import com.finalproject.DTO.FavouriteDTOs.*;
import com.finalproject.DTO.OrderDTOs.*;
import com.finalproject.DTO.OrderItemDTOs.*;
import com.finalproject.DTO.ProductDTOs.*;
import com.finalproject.DTO.Result;
import com.finalproject.service.FavouriteService;
import com.finalproject.service.OrderService;
import com.finalproject.service.OrderItemService;
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

    @Resource
    private OrderService orderService;

    @Resource
    private OrderItemService orderItemService;

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
    public ResponseEntity<Result<Boolean>> isStoreBookmarked(@RequestParam StoreIdDTO model, Authentication authentication) {
        String userId = (String) authentication.getPrincipal();
        Result<Boolean>response = favouriteService.isStoreBookmarked(userId, model.getStoreId());
        return ResponseEntity.status(response.getCode()).body(response);
    }

    // 检查是否收藏商品
    @GetMapping("/favourite/is-product-bookmarked")
    public ResponseEntity<Result<Boolean>> isProductBookmarked(@RequestParam ProductIdDTO model, Authentication authentication) {
        String userId = (String) authentication.getPrincipal();
        Result<Boolean>response = favouriteService.isProductBookmarked(userId, model.getProductId());
        return ResponseEntity.status(response.getCode()).body(response);
    }

    // 检查是否收藏商品
    @GetMapping("/internal/is-product-bookmarked/{userId}/{productId}")
    public Result<Boolean> isProductBookmarkedInternal(@PathVariable String userId, @PathVariable String productId) {
        return favouriteService.isProductBookmarked(userId, productId);
    }

    // 检查是否收藏店铺
    @GetMapping("/internal/is-store-bookmarked/{userId}/{storeId}")
    public Result<Boolean> isStoreBookmarkedInternal(@PathVariable String userId, @PathVariable String storeId) {
        return favouriteService.isProductBookmarked(userId, storeId);
    }

    // 可选多个商品添加对应订单
    @PostMapping("/order/add-orders")
    public ResponseEntity<Result<List<OrderRelatedDTO>>> addOrders(@RequestBody ProductIdsDTO productIdsDTO, Authentication authentication) {
        String userId = (String) authentication.getPrincipal();
        Result<List<OrderRelatedDTO>>response = orderService.addOrders(userId, productIdsDTO.getProductIds());
        return ResponseEntity.status(response.getCode()).body(response);
    }

    // 修改订单的收货信息
    @PostMapping("/order/change-buyer-info")
    public ResponseEntity<Result<String>> changeNameAndAddress(@RequestBody ChangeNameAndAddressDTO changeNameAndAddressDTO) {
        Result<String>response = orderService.changeNameAndAddress(changeNameAndAddressDTO);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    // 添加商品评价
    @PatchMapping("/order/remark-order-item")
    public ResponseEntity<Result<String>> remarkOrderItem(@RequestBody UpdateOrderItemRemarkDTO updateOrderItemRemarkDTO) {
        Result<String>response = orderItemService.remarkOrderItem(updateOrderItemRemarkDTO);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    // 添加商品评价
    @GetMapping("/order/is-order-item-remarked")
    public ResponseEntity<Result<Boolean>> isOrderItemRemarked(@RequestBody OrderItemIdDTO orderItemIdDTO) {
        Result<Boolean>response = orderItemService.isOrderItemRemarked(orderItemIdDTO.getOrderItemId());
        return ResponseEntity.status(response.getCode()).body(response);
    }

    // 获取店铺所有订单评论
    @GetMapping("/order/get-store-remarks")
    public ResponseEntity<Result<List<GetStoreRemarkDTO>>> getStoreRemarks(Authentication authentication) {
        String userId = (String) authentication.getPrincipal();
        Result<List<GetStoreRemarkDTO>> response = orderItemService.getStoreRemarks(userId);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    // 判断是否存在当前商品的订单项
    @GetMapping("/order/is-exist-product-order")
    public ResponseEntity<Result<Boolean>> IsExistProductOrder(@RequestParam ProductIdDTO productIdDTO) {
        Result<Boolean> response = orderItemService.isExistProductOrder(productIdDTO.getProductId());
        return ResponseEntity.status(response.getCode()).body(response);
    }


}

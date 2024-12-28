package com.finalproject.controller;
import com.finalproject.DTO.FavouriteDTOs.*;
import com.finalproject.DTO.OrderDTOs.*;
import com.finalproject.DTO.OrderItemDTOs.*;
import com.finalproject.DTO.ProductDTOs.*;
import com.finalproject.DTO.Result;
import com.finalproject.model.OrderItem;
import com.finalproject.repository.OrderItemRepository;
import com.finalproject.service.FavouriteService;
import com.finalproject.service.OrderService;
import com.finalproject.service.OrderItemService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


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
    public ResponseEntity<Result<Boolean>> isOrderItemRemarked(@RequestParam String orderItemId) {
        Result<Boolean>response = orderItemService.isOrderItemRemarked(orderItemId);
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

    // 更改订单项的状态为有售后(子系统接口)
    @PutMapping("/order/return/{user_id}/{item_id}")
    public ResponseEntity<Result<Map<String,String>>>
    returnOrderItem(@PathVariable String user_id,@PathVariable String item_id){
        Result<Map<String,String>> response =orderItemService.returnOrderItem(user_id,item_id);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    // 更改订单项的状态为售后结束(子系统接口)
    @PutMapping("/order/end_return/{item_id}")
    public ResponseEntity<Result<Map<String,String>>>
    endReturnOrderItem(@PathVariable String item_id){
        Result<Map<String,String>> response =orderItemService.endAfterSell(item_id);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    // 商家审批是否同意订单项退货，不同意将更改订单项状态为售后结束(子系统接口)
    @PutMapping("/order/approve_return/{user_id}/{item_id}/{is_approve}")
    public ResponseEntity<Result<Map<String,String>>>
    approveReturnOrderItem(@PathVariable String user_id,@PathVariable String item_id
    ,@PathVariable Boolean is_approve){
        Result<Map<String,String>> response = orderItemService.
                approveReturnOrderItem(user_id,item_id,is_approve);
        return ResponseEntity.status(response.getCode()).body(response);
    }


    // 获取卖家待售后订单(子系统接口)
    @GetMapping("/order/store/current_return/{user_id}")
    public ResponseEntity<Result<List<String>>>
    getStoreReturnRequest(@PathVariable String user_id){
        Result<List<String>> response = orderItemService.
                getStoreCurrentAfterSellItem(user_id);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    // 获取卖家历史售后订单(子系统接口)
    @GetMapping("/order/store/history_return/{user_id}")
    public ResponseEntity<Result<List<String>>>
    getStoreHistoryReturnRequest(@PathVariable String user_id){
        Result<List<String>> response = orderItemService.
                getStoreHistoryAfterSellItem(user_id);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    // 获取买家待售后订单(子系统接口)
    @GetMapping("/order/buyer/current_return/{user_id}")
    public ResponseEntity<Result<List<String>>>
    getBuyerReturnRequest(@PathVariable String user_id){
        Result<List<String>> response = orderItemService.
                getBuyerCurrentAfterSellItem(user_id);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    // 获取买家历史售后订单(子系统接口)
    @GetMapping("/order/buyer/history_return/{user_id}")
    public ResponseEntity<Result<List<String>>>
    getBuyerHistoryReturnRequest(@PathVariable String user_id){
        Result<List<String>> response = orderItemService.
                getBuyerHistoryAfterSellItem(user_id);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    // 判断买家是否有权限修改某订单项状态（子系统接口）
    @GetMapping("/order/buyer/have_item/{user_id}/{item_id}")
    public ResponseEntity<Result<Map<String,String>>>
    checkBuyerForItemChange(@PathVariable String user_id,@PathVariable String item_id){
        Result<Map<String,String>> response = orderItemService.
                isUserExistOrderItem(user_id,item_id);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    // 判断卖家是否有权限修改某订单项状态（子系统接口）
    @GetMapping("/order/store/have_item/{user_id}/{item_id}")
    public ResponseEntity<Result<Map<String,String>>>
    checkStoreForItemChange(@PathVariable String user_id,@PathVariable String item_id){
        Result<Map<String,String>> response = orderItemService.
                isStoreExistOrderItem(user_id,item_id);
        return ResponseEntity.status(response.getCode()).body(response);
    }


    // 判断是否存在当前商品的订单项
    @DeleteMapping("/order/delete-order")
    public ResponseEntity<Result<String>> deleteOrder(@RequestParam String orderId) {
        Result<String> response = orderService.deleteOrder(orderId);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    // 获取当前买家所有订单信息
    @GetMapping("/order/get-all-buyer-orders")
    public ResponseEntity<Result<List<OrderCenterDTO>>> getAllBuyerOrders(Authentication authentication) {
        String userId = (String) authentication.getPrincipal();
        Result<List<OrderCenterDTO>> response = orderService.getBuyersAllOrders(userId);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    // 获取当前商家所有订单信息
    @GetMapping("/order/get-all-store-orders")
    public ResponseEntity<Result<List<OrderCenterDTO>>> getAllStoreOrders(Authentication authentication) {
        String userId = (String) authentication.getPrincipal();
        Result<List<OrderCenterDTO>> response = orderService.getStoreAllOrders(userId);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    // 获取单个订单信息
    @GetMapping("/order/get-one-order")
    public ResponseEntity<Result<OrderCenterDTO>> getOneOrder(@RequestParam String orderId) {
        Result<OrderCenterDTO> response = orderService.getOneOrder(orderId);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    // 确认收货
    @PostMapping("/order/receive-order")
    public ResponseEntity<Result<String>> receiveOrder(@RequestBody OrderIdDTO orderIdDTO) {
        Result<String> response = orderService.receiveOrder(orderIdDTO.getOrderId());
        return ResponseEntity.status(response.getCode()).body(response);
    }

    // 返回商家某天的交易量和交易额
    @GetMapping("/order/get-order-by-date")
    public ResponseEntity<Result<OrderStatisticsDTO>> getOrderNumberByDate(@RequestParam String date,Authentication authentication) {
        String userId = (String) authentication.getPrincipal();
        Result<OrderStatisticsDTO> response = orderService.getOrderNumberByDate(userId,date);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    // 商家更新快递单号
    @PatchMapping("/order/update-delivery-number")
    public ResponseEntity<Result<String>> updateDeliveryNumber(@RequestBody OrderDeliveryDTO orderDeliveryDTO){
        Result<String> response = orderService.updateDeliveryNumber(orderDeliveryDTO.getDeliveryNumber(),
                orderDeliveryDTO.getOrderId());
        return ResponseEntity.status(response.getCode()).body(response);
    }

    //Result<CreditsDTO> payOrder (String userId, String orderId, BigDecimal actualPay)
    // 买家支付订单
    @PostMapping("/order/pay-order")
    public ResponseEntity<Result<CreditsDTO>> payOrder(@RequestBody MoneyDTO moneyDTO,Authentication authentication){
        String userId = (String) authentication.getPrincipal();
        Result<CreditsDTO> response = orderService.payOrder(userId,moneyDTO.getOrderId(), BigDecimal.valueOf(moneyDTO.getActualPay()));
        return ResponseEntity.status(response.getCode()).body(response);
    }
}

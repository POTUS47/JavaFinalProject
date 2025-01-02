package com.finalproject.service;

import com.finalproject.DTO.AfterSellDTOs;
import com.finalproject.DTO.OrderItemDTOs.*;
import com.finalproject.DTO.Result;
import com.finalproject.model.Returns;
import com.finalproject.repository.ReturnRepository;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class ReturnService {
    @Resource
    private ReturnRepository returnRepository;
    @Value("${api.base-url}")
    private String baseUrl;

    private final RestTemplate restTemplate;

    public ReturnService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // 创建退货申请
    public Result<Map<String, String>> applyReturn(String orderItemId,String reason) {
        // 保存退货申请到退货表
        Returns returnOrder = new Returns();
        returnOrder.setOrderId(orderItemId);
        returnOrder.setReturnReason(reason);
        returnOrder.setReturnStatus(Returns.ReturnStatus.待审核);
        returnOrder.setReturnTime(LocalDateTime.now());
        returnRepository.save(returnOrder);
        Map<String, String> data = new HashMap<>();
        data.put("message", "退货申请提交成功！");
        data.put("item_Id", orderItemId);
        return Result.success(data);
    }

    // 审批退货申请
    public Result<Map<String, String>> approveReturn(String orderItemId,boolean isApproved,String reason) {
        // 保存退货申请到退货表
        Optional<Returns> returns=returnRepository.findByItemId(orderItemId);
        if(returns.isEmpty()) {
            return Result.error(404, "想要审核的退货单不存在");
        }
        returns.get().setResultReason(reason);
        if(isApproved){
            returns.get().setReturnStatus(Returns.ReturnStatus.审核通过);
        }
        else{
            returns.get().setReturnStatus(Returns.ReturnStatus.申请被拒绝);
        }
        returnRepository.save(returns.get());
        Map<String, String> data = new HashMap<>();
        data.put("message", "退货申请审核成功！");
        data.put("item_Id", orderItemId);
        return Result.success(data);
    }

    // 查看某退货详情
    public Result<AfterSellDTOs.ReturnDTO> getReturnDetail(String itemId) {
        Optional<Returns> returns = returnRepository.findByItemId(itemId);
        if(returns.isEmpty()) {
            return Result.error(404, "想要查看详情的退货单不存在");
        }
        AfterSellDTOs.ReturnDTO response = new AfterSellDTOs.ReturnDTO();
        response.setItemId(itemId);
        response.setReturnStatus(returns.get().getReturnStatus().toString());
        response.setShippingNumber(returns.get().getShippingNumber());
        response.setReturnReason(returns.get().getReturnReason());
        response.setReturnDate(returns.get().getReturnTime());
        response.setResultReason(returns.get().getResultReason());
        return Result.success(response);
    }

    // 填写退货单快递单号
    public Result<Map<String, String>> addExpressNumber(String returnId, String expressNumber) {
        Optional<Returns> returnOrder = returnRepository.findByItemId(returnId);
        if (returnOrder.isEmpty()){
            return Result.error(404, "想填写快递单号的退货单不存在！");
        }
        if(!returnOrder.get().getShippingNumber().isEmpty()){
            return Result.error(400, "退货单已经存在快递单号！");
        }
        returnOrder.get().setShippingNumber(expressNumber);
        returnOrder.get().setReturnStatus(Returns.ReturnStatus.已退货);
        returnRepository.save(returnOrder.get());
        Map<String, String> data = new HashMap<>();
        data.put("message","快递单号填写成功");
        data.put("item_Id", returnId);
        return Result.success(data);
    }

    // 退货单确认收货
    public Result<Map<String, String>> confirmReceive(String returnId) {
        Optional<Returns> returnOrder = returnRepository.findByItemId(returnId);
        if (returnOrder.isEmpty()){
            return Result.error(404, "想确认收货的退货单不存在！");
        }
        if(!returnOrder.get().getReturnStatus().equals(Returns.ReturnStatus.已收货)){
            return Result.error(400, "无法重复确认收货！");
        }
        returnOrder.get().setReturnStatus(Returns.ReturnStatus.已收货);
        returnRepository.save(returnOrder.get());
        Map<String, String> data = new HashMap<>();
        data.put("message","卖家确认收货成功！");
        data.put("item_Id", returnId);
        return Result.success(data);
    }

    // 更改订单项为退款状态
    public Result<Map<String,String>> refundOrderItem(String returnId) {
        Optional<Returns> returnOrder = returnRepository.findByItemId(returnId);
        if (returnOrder.isEmpty()){
            return Result.error(404, "找不到用于退款的退货单！");
        }
        returnOrder.get().setReturnStatus(Returns.ReturnStatus.已退款);
        returnRepository.save(returnOrder.get());
        Map<String, String> data = new HashMap<>();
        data.put("message","退款状态修改成功！");
        return Result.success(data);
    }

    // 检查退货单是否为已被拒绝状态（是否可申诉）
    public Result<Map<String,String>> checkReturnForComplain(String returnId) {
        AfterSellDTOs.ReturnDTO returns=getReturnDetail(returnId).getData();
        String returnStatus=returns.getReturnStatus();
        if(!returnStatus.equals("申请被拒绝") ){
            return Result.error(500,"无可申诉内容！");
        }
        String sellerReason=returns.getResultReason();
        Map<String, String> data = new HashMap<>();
        data.put("message","退货单可被申诉！");
        data.put("seller_reason",sellerReason);
        return Result.success(data);
    }

    // 检查买家身份填写退货单快递单号信息
    public Result<Map<String,String>> checkAndAddShippingInfo(String userID,String returnId,String shippingNumber) {
        Result<Map<String,String>> response;
        response=checkBuyerForItem(userID,returnId);
        if(response.getCode()!=200){
            return response;
        }
        response=addExpressNumber(returnId,shippingNumber);
        return response;
    }

    // 检查卖家身份并确认退货单的收货
    public Result<Map<String,String>> checkAndConfirmReceive(String userID,String returnId) {
        Result<Map<String,String>> response;
        response=checkStoreForItem(userID,returnId);
        if(response.getCode()!=200){
            return response;
        }
        response=confirmReceive(returnId);
        if(response.getCode()!=200){
            return response;
        }
        response=refund(userID,returnId);
        return response;
    }

    //跨子系统的调用
    //根据退货单找价格和订单id
    public Result<PaymentAndIdDTO> getPaymentFromSubSys(String returnId) {
        String url = baseUrl + "/api/shopping/internal/getPaymentAndId/" + returnId;
        ResponseEntity<Result<PaymentAndIdDTO>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );
        return response.getBody();
    }

    //跨子系统的调用
    //根据orderId找买卖双方id
    public Result<BuyerShopperIdDTO> getShopperAndBuyerId(String orderId) {
        String url = baseUrl + "/api/shopping/internal/getStoreBuyerId/" + orderId;
        ResponseEntity<Result<BuyerShopperIdDTO>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );
        return response.getBody();
    }

    //跨子系统的调用
    //根据双方id和金额进行退款
    public Result<Map<String,String>> refundFromSubsys(String storeId,String buyerId,BigDecimal actualPay) {
        String url = baseUrl + "/api/users/"+buyerId+"/pay/"+storeId+"/"+actualPay;
        ResponseEntity<Result<Map<String, String>>> response = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                null,
                new ParameterizedTypeReference<>() {}
        );
        return response.getBody();
    }

    // 退款相关
    public Result<Map<String,String>> refund(String userID,String returnId) {
        // 检查退货单return状态是否为“已收货”。
        if(!hasRecieved(returnId).getData()){
            return Result.error(400,"用户未收货，不可以退货");
        }

        // 根据returnid找到相应的订单项，获取退货价格。
        PaymentAndIdDTO res=getPaymentFromSubSys(returnId).getData();
        String orderId=res.getOrderId();//订单id
        BigDecimal actualPay=res.getActualPay();//实际退货价钱

        // 根据returnid找到订单，获取买卖双方的ID。（这一步顺便要检查user是不是订单中的卖家）
        BuyerShopperIdDTO ids=getShopperAndBuyerId(orderId).getData();
        String actualBuyer=ids.getBuyerId();
        String actualStore=ids.getStoreId();
        if(!actualBuyer.equals(userID)){
            return Result.error(403,"传入的userid不是该订单的买家");
        }
        // 根据买卖双方的ID，获取对应的钱包并扣款交易
        Result<Map<String,String>> refundres=refundFromSubsys(actualStore,actualBuyer,actualPay);
        if(refundres.getCode()!=200){
            return refundres;
        }
        // 调用支付子系统进行真实退款处理？没有

        Result<Map<String,String>> response;
        response=refundOrderItem(returnId);// 修改退货单状态为“已退款状态”
        if(response.getCode()!=200){
            return response;
        }
        response=endReturn(returnId);// 修改订单项状态为“售后结束”（调用外部子系统接口）
        return response;
    }

    //检查退货单是否为已收货
    public Result<Boolean> hasRecieved(String returnId) {
        Optional<Returns> returnOrder = returnRepository.findByItemId(returnId);
        if(returnOrder.isEmpty()){
            return Result.error(404,"不存在该退货单");
        }
        Returns temp=returnOrder.get();
        if(temp.getReturnStatus().toString().equals("已收货")){
            return Result.success(true);
        }
        return Result.success(false);
    }




    //用于子系统沟通(买家申请退货)
    public Result<Map<String,String>> checkBuyerReturn(String userId,String itemId){
        String url = baseUrl + "/api/shopping/order/return/" + userId+"/"+itemId;
        ResponseEntity<Result<Map<String,String>>> response = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                null,
                new ParameterizedTypeReference<>() {
                } );
        return response.getBody();
    }

    //用于子系统沟通（商家审批退货）
    public Result<Map<String,String>> checkStoreReturn(String userId,String itemId,Boolean isApprove){
        String url = baseUrl + "/api/shopping/order/approve_return/" + userId+"/"+itemId+"/"+isApprove;
        ResponseEntity<Result<Map<String,String>>> response = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                null,
                new ParameterizedTypeReference<>() {
                } );
        return response.getBody();
    }

    //用于子系统沟通（卖家查看售后中订单项）
    public Result<List<String>> getSellerReturnRequests(String userId){
        String url = baseUrl + "/api/shopping/order/store/current_return/" + userId;
        ResponseEntity<Result<List<String>>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                } );
        return response.getBody();
    }

    //用于子系统沟通（卖家查看历史售后订单项）
    public Result<List<String>> getSellerHistoryReturnRequests(String userId){
        String url = baseUrl + "/api/shopping/order/store/history_return/" + userId;
        ResponseEntity<Result<List<String>>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                } );
        return response.getBody();
    }

    //用于子系统沟通（买家查看售后中订单项）
    public Result<List<String>> getBuyerReturnRequests(String userId){
        String url = baseUrl + "/api/shopping/order/buyer/current_return/" + userId;
        ResponseEntity<Result<List<String>>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                } );
        return response.getBody();
    }

    //用于子系统沟通（买家查看历史售后订单项）
    public Result<List<String>> getBuyerHistoryReturnRequests(String userId){
        String url = baseUrl + "/api/shopping/order/buyer/history_return/" + userId;
        ResponseEntity<Result<List<String>>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                } );
        return response.getBody();
    }

    //用于子系统沟通（查看买家是否有订单项修改权限）
    public Result<Map<String,String>> checkBuyerForItem(String userId,String itemId){
        String url = baseUrl + "/api/shopping/order/buyer/have_item/" + userId+"/"+itemId;
        ResponseEntity<Result<Map<String,String>>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                } );
        return response.getBody();
    }

    //用于子系统沟通（查看卖家是否有订单项修改权限）
    public Result<Map<String,String>> checkStoreForItem(String userId,String itemId){
        String url = baseUrl + "/api/shopping/order/store/have_item/" + userId+"/"+itemId;
        ResponseEntity<Result<Map<String,String>>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                } );
        return response.getBody();
    }

    //用于子系统沟通（更改订单项的状态为售后结束）
    public Result<Map<String,String>> endReturn(String itemId){
        String url = baseUrl + "/api/shopping/order/end_return/"+itemId;
        ResponseEntity<Result<Map<String,String>>> response = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                null,
                new ParameterizedTypeReference<>() {
                } );
        return response.getBody();
    }



}

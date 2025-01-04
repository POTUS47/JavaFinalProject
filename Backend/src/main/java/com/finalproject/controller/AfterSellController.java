package com.finalproject.controller;

import com.finalproject.DTO.AfterSellDTOs;
import com.finalproject.DTO.Result;
import com.finalproject.service.ComplainService;
import com.finalproject.service.ReturnService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/afterSell")
public class AfterSellController {

    private final ReturnService returnService;
    private final ComplainService complainService;

    AfterSellController(ReturnService returnService, ComplainService complainService) {
        this.returnService = returnService;
        this.complainService = complainService;
    }

    // 买家-订单项-退货申请
    @PostMapping("/orderItem/{orderItemId}/return")
    public ResponseEntity<Result<Map<String, String>>> applyReturn(@PathVariable String orderItemId,
                                                      @RequestBody AfterSellDTOs.ReturnRequestDTO dto,
                                                      Authentication authentication) {
        String userId = (String) authentication.getPrincipal();
        String reason=dto.getReason();
        Result<Map<String, String>> check=returnService.checkBuyerReturn(userId,orderItemId);
        if(check.getCode()!=200){
            return ResponseEntity.status(check.getCode()).body(check);
        }
        Result<Map<String, String>> response = returnService.applyReturn(orderItemId, reason);
        return ResponseEntity.status(response.getCode()).body(response);
    }
    // 商家-订单项-审批退货申请
    @PostMapping("/returns/{orderItemId}/approveReturn")
    public ResponseEntity<Result<Map<String, String>>> approveReturn(@PathVariable String orderItemId,
                                                                   @RequestBody AfterSellDTOs.approveReturnDTO dto,
                                                                   Authentication authentication) {
        String userId = (String) authentication.getPrincipal();
        String reason=dto.getReason();
        boolean isApproved=dto.getResult();
        Result<Map<String, String>> check=returnService.checkStoreReturn(userId,orderItemId,isApproved);
        if(check.getCode()!=200){
            return ResponseEntity.status(check.getCode()).body(check);
        }
        Result<Map<String, String>> response = returnService.approveReturn(orderItemId,isApproved,reason);
        return ResponseEntity.status(response.getCode()).body(response);
    }
    // 卖家-查看处理中退货申请
    @GetMapping("/seller/current_returns")
    public ResponseEntity<Result<List<String>>> getSellerReturnRequests(Authentication authentication) {
        String userId = (String) authentication.getPrincipal();
        Result<List<String>> response = returnService.getSellerReturnRequests(userId);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    // 卖家-查看历史退货申请
    @GetMapping("/seller/history_returns")
    public ResponseEntity<Result<List<String>>> getSellerHistoryReturnRequests(Authentication authentication) {
        // 首先ply找到卖家所有商品，mmy通过商品ID找到所有关联订单，再找到所有关联订单项，再找到其中有售后的订单项
        String userId = (String) authentication.getPrincipal();
        Result<List<String>> response = returnService.getSellerHistoryReturnRequests(userId);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    // 买家-查看处理中退货申请
    @GetMapping("/buyer/current_returns")
    public ResponseEntity<Result<List<String>>> getBuyerReturnRequests(Authentication authentication) {
        String userId = (String) authentication.getPrincipal();
        Result<List<String>> response = returnService.getBuyerReturnRequests(userId);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    // 买家-查看历史退货申请
    @GetMapping("/buyer/history_returns")
    public ResponseEntity<Result<List<String>>> getBuyerHistoryReturnRequests(Authentication authentication) {
        String userId = (String) authentication.getPrincipal();
        Result<List<String>> response = returnService.getBuyerHistoryReturnRequests(userId);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    // 查看退货详情
    @GetMapping("/return/{returnId}")
    public ResponseEntity<Result<AfterSellDTOs.ReturnDTO>> getReturnDetails(@PathVariable String returnId) {
        Result<AfterSellDTOs.ReturnDTO> response = returnService.getReturnDetail(returnId);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    // 买家-退货单填写快递单号
    @PostMapping("/return/{returnId}/addExpressNumber")
    public ResponseEntity<Result<Map<String,String>>> addExpressNumber(@PathVariable String returnId, @RequestBody String shippingNumber, Authentication authentication) {
        String userId = (String) authentication.getPrincipal();
        Result<Map<String,String>> response = returnService.checkAndAddShippingInfo(userId,returnId,shippingNumber);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    // 卖家-退货单确认收货
    @PostMapping("/return/{returnId}/confirmReceive")
    public ResponseEntity<Result<Map<String,String>>> confirmReceive(@PathVariable String returnId,
                                                                     Authentication authentication) {
        String userId = (String) authentication.getPrincipal();
        Result<Map<String,String>> response = returnService.checkAndConfirmReceive(userId,returnId);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    // 卖家-为订单项退款
    @PostMapping("/orderItem/{orderItemId}/refund")
    public ResponseEntity<Result<Map<String,String>>> refundOrderItem(@PathVariable String orderItemId,
                                                                      Authentication authentication) {
        String userId = (String) authentication.getPrincipal();
        Result<Map<String,String>> response = returnService.refund(userId,orderItemId);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    // 卖家-发起申诉仲裁
    @PostMapping("/return/{returnId}/arbitration")
    public ResponseEntity<Result<Map<String,String>>> applyArbitration(@PathVariable String returnId,
                                                           @RequestBody AfterSellDTOs.ComplainRequestDTO dto) {
        String sellerReason=dto.getReason();
        Result<Map<String,String>> response =returnService.checkReturnForComplain(returnId);
        if(response.getCode()!=200){
            return ResponseEntity.status(response.getCode()).body(response);
        }
        String buyerReason= response.getData().get("buyer_reason");
        response = complainService.applyComplain(returnId,buyerReason,sellerReason);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    // 管理员-仲裁申诉结果
    @PostMapping("/arbitration/{complainId}/resolve")
    public ResponseEntity<Result<Map<String,String>>> resolveArbitration(@PathVariable String complainId,
                                                             @RequestBody AfterSellDTOs.approveComplainDTO dto,
                                                                         Authentication authentication) {
        String adminId = (String) authentication.getPrincipal();
        String role = authentication.getAuthorities().iterator().next().getAuthority();
        Result<Map<String,String>> response;
        if(!role.equals("管理员")){
            response=Result.error(403,"无审核仲裁权限！");
        }
        else{
            boolean isApproved=dto.getResult();
            String reason=dto.getReason();
            response = complainService.approveComplain(complainId,isApproved,reason,adminId);
            if(response.getCode()==200){
                Result<Map<String,String>> response2=returnService.approveReturn(complainId,isApproved,"管理员审核意见："+reason);
                if(response2.getCode()!=200){
                    return ResponseEntity.status(response2.getCode()).body(response2);
                }
            }
        }
        return ResponseEntity.status(response.getCode()).body(response);
    }

    //管理员调取所有申诉记录
    @GetMapping("/getAllComplain")
    public ResponseEntity<Result<List<AfterSellDTOs.ComDTO>>> getAllComplain(Authentication authentication) {
        Result<List<AfterSellDTOs.ComDTO>> response=complainService.getAllComplain();
        return ResponseEntity.status(response.getCode()).body(response);
    }

}


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

    // 卖家-退货单确认收货（是否需要增加库存????）
    @PostMapping("/return/{returnId}/confirmReceive")
    public ResponseEntity<Result<Map<String,String>>> confirmReceive(@PathVariable String returnId,
                                                                     Authentication authentication) {
        String userId = (String) authentication.getPrincipal();
        Result<Map<String,String>> response = returnService.checkAndConfirmReceive(userId,returnId);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    // 卖家-为订单项退款(支付宝接口还没有，只是操作钱包！！！！！！)
    @PostMapping("/orderItem/{orderItemId}/refund")
    public ResponseEntity<Result<Map<String,String>>> refundOrderItem(@PathVariable String orderItemId,
                                                                      Authentication authentication) {
        // refund函数待完成：
        // 检查退货单状态是否为“已收货”。
        // 根据returnid找到相应的订单项，获取退货价格。
        // 根据returnid找到订单，获取买卖双方的ID。（这一步顺便要检查user是不是订单中的卖家）
        // 根据买卖双方的ID，获取对应的钱包并扣款交易
        // 调用支付子系统进行真实退款处理？
        // 修改订单项状态为“售后结束”（已有）
        // 修改退货单状态为“已退款”（已有）
        String userId = (String) authentication.getPrincipal();
        Result<Map<String,String>> response = returnService.refund(userId,orderItemId);
        return ResponseEntity.status(response.getCode()).body(response);
    }


    // 买家-发起申诉仲裁
    @PostMapping("/return/{returnId}/arbitration")
    public ResponseEntity<Result<Map<String,String>>> applyArbitration(@PathVariable String returnId,
                                                           @RequestBody AfterSellDTOs.ComplainRequestDTO dto) {
        String buyerReason=dto.getReason();
        Result<Map<String,String>> response =returnService.checkReturnForComplain(returnId);
        if(response.getCode()!=200){
            return ResponseEntity.status(response.getCode()).body(response);
        }
        String sellerReason= response.getData().get("seller_reason");
        response = complainService.applyComplain(returnId,buyerReason,sellerReason);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    // 管理员-仲裁申诉结果(仲裁完要强制进入退货流程吗？)
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
        }
        return ResponseEntity.status(response.getCode()).body(response);
    }

}


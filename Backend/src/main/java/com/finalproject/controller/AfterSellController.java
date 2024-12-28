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
@RequestMapping("/afterSell")
public class AfterSellController {

    // 买家-订单项-退货申请
    // 商家-订单项-审批退货申请
    // 买家-发起申诉仲裁（卖家拒绝退货时，买家提交证据，向管理员发起仲裁）
    // 买家-查看退货列表(售后仅包含退货)
    // 买家-查看退货售后详情
    // 买家-查看申诉列表
    // 买家卖家-查看申诉详情
    // 卖家-查看退货申请
    // 卖家-查看相关申诉
    // 管理员-仲裁申诉结果
    // 买家-退货单填写快递单号（退货发货）
    // 卖家-为订单项退款

    private final ReturnService returnService;
    private final ComplainService complainService;

    AfterSellController(ReturnService returnService, ComplainService complainService) {
        this.returnService = returnService;
        this.complainService = complainService;
    }

    // 买家-订单项-退货申请、、、、、
    @PostMapping("/orderItem/{orderItemId}/return")
    public ResponseEntity<Result<Map<String, String>>> applyReturn(@PathVariable String orderItemId,
                                                      @RequestBody AfterSellDTOs.ReturnRequestDTO dto,
                                                      Authentication authentication) {
        // 首先检查订单项是否存在，并且用户是否是订单项的主人,并且订单是不是已收货状态！！！并更改订单项的状态为有售后！！！！！
        String userId = (String) authentication.getPrincipal();
        String role = authentication.getAuthorities().iterator().next().getAuthority();
        String reason=dto.getReason();
        Result<Map<String, String>> response = returnService.applyReturn(orderItemId, reason);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    // 商家-订单项-审批退货申请、、、、、
    @PostMapping("/returns/{orderItemId}/approveReturn")
    public ResponseEntity<Result<Map<String, String>>> approveReturn(@PathVariable String orderItemId,
                                                                   @RequestBody AfterSellDTOs.approveReturnDTO dto,
                                                                   Authentication authentication) {
        // 首先检查订单项是否存在，并且用户是否是订单项的主人,并且订单是不是已收货状态！！！并更改订单项的状态为有售后！！！！！
        String userId = (String) authentication.getPrincipal();
        String role = authentication.getAuthorities().iterator().next().getAuthority();
        String reason=dto.getReason();
        boolean isApproved=dto.getResult();
        Result<Map<String, String>> response = returnService.approveReturn(orderItemId,isApproved,reason);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    // 卖家-查看退货申请、、、、、、、、、、
    @GetMapping("/seller/returns")
    public ResponseEntity<Result<List<String>>> getSellerReturnRequests(Authentication authentication) {
        // 首先ply找到卖家所有商品，mmy通过商品ID找到所有关联订单，再找到所有关联订单项，再找到其中有售后的订单项
//        String userId = (String) authentication.getPrincipal();
//        Result<List<String>> response = returnService.getSellerReturnList(userId);
//        return ResponseEntity.status(response.getCode()).body(response);
        return null;
    }

    // 买家-查看退货列表、、、、、、、、、、
    @GetMapping("/buyer/returns")
    public ResponseEntity<Result<List<String>>> getReturnList(Authentication authentication) {
        // 首先mmy通过用户ID找到所有关联订单，再找到所有关联订单项，再找到其中有售后的订单项
//        String userId = (String) authentication.getPrincipal();
//        Result<List<String>> response = returnService.getSellerReturnList(userId);
//        return ResponseEntity.status(response.getCode()).body(response);
        return null;
    }

    // 查看退货详情
    @GetMapping("/return/{returnId}")
    public ResponseEntity<Result<AfterSellDTOs.ReturnDTO>> getReturnDetails(@PathVariable String returnId) {
        Result<AfterSellDTOs.ReturnDTO> response = returnService.getReturnDetail(returnId);
        return ResponseEntity.status(response.getCode()).body(response);
    }


    // 买家-退货单填写快递单号、、、、
    @PostMapping("/return/{returnId}/addExpressNumber")
    public ResponseEntity<Result<Map<String,String>>> addExpressNumber(@PathVariable String returnId,
                                                           @RequestBody String shippingNumber) {
        // 检查卖家是否和这个退货单相关
        Result<Map<String,String>> response = returnService.addExpressNumber(returnId, shippingNumber);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    // 卖家-退货单确认收货、、、、、
    @PostMapping("/return/{returnId}/confirmReceive")
    public ResponseEntity<Result<Map<String,String>>> confirmReceive(@PathVariable String returnId,
                                                                     Authentication authentication) {
        String userId = (String) authentication.getPrincipal();
        // 检查卖家是否和这个退货单相关
        Result<Map<String,String>> response = returnService.confirmReceive(returnId);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    // 卖家-为订单项退款？？？？？？？？？？？？？？？？？？
    @PostMapping("/orderItem/{orderItemId}/refund")
    public ResponseEntity<Result<Map<String,String>>> refundOrderItem(@PathVariable String orderItemId) {
        // 根据returnid找到相应的订单项，找到退货价格。
        // 调用支付子系统进行退款处理
        Result<Map<String,String>> response = returnService.refundOrderItem(orderItemId);
        return ResponseEntity.status(response.getCode()).body(response);
    }


    // 买家-发起申诉仲裁
    @PostMapping("/return/{returnId}/arbitration")
    public ResponseEntity<Result<Map<String,String>>> applyArbitration(@PathVariable String returnId,
                                                           @RequestBody AfterSellDTOs.ComplainRequestDTO dto) {
        String buyerReason=dto.getReason();
        String sellerReason=returnService.getReturnDetail(returnId).getData().getResultReason();
        Result<Map<String,String>> response = complainService.applyComplain(returnId,buyerReason,sellerReason);
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
        }
        return ResponseEntity.status(response.getCode()).body(response);
    }





}


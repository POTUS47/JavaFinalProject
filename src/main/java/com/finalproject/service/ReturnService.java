package com.finalproject.service;

import com.finalproject.DTO.AfterSellDTOs;
import com.finalproject.DTO.Result;
import com.finalproject.model.Return;
import com.finalproject.repository.ReturnRepository;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/returns")
public class ReturnService {
    @Resource
    private ReturnRepository returnRepository;

    // 创建退货申请
    public Result<Map<String, String>> applyReturn(String orderItemId,String reason) {
        // 保存退货申请到退货表
        Return returnOrder = new Return();
        returnOrder.setOrderId(orderItemId);
        returnOrder.setReturnReason(reason);
        returnOrder.setReturnStatus(Return.ReturnStatus.待审核);
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
        Optional<Return> returns=returnRepository.findByItemId(orderItemId);
        if(returns.isEmpty()) {
            return Result.error(404, "想要审核的退货单不存在");
        }
        returns.get().setResultReason(reason);
        if(isApproved){
            returns.get().setReturnStatus(Return.ReturnStatus.审核通过);
        }
        else{
            returns.get().setReturnStatus(Return.ReturnStatus.申请被拒绝);
        }
        returnRepository.save(returns.get());
        Map<String, String> data = new HashMap<>();
        data.put("message", "退货申请审核成功！");
        data.put("item_Id", orderItemId);
        return Result.success(data);
    }

    // 查看某退货详情
    public Result<AfterSellDTOs.ReturnDTO> getReturnDetail(String itemId) {
        Optional<Return> returns = returnRepository.findByItemId(itemId);
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
        Optional<Return> returnOrder = returnRepository.findByItemId(returnId);
        if (returnOrder.isEmpty()){
            return Result.error(404, "想填写快递单号的退货单不存在！");
        }
        if(!returnOrder.get().getShippingNumber().isEmpty()){
            return Result.error(400, "退货单已经存在快递单号！");
        }
        returnOrder.get().setShippingNumber(expressNumber);
        returnOrder.get().setReturnStatus(Return.ReturnStatus.已退货);
        returnRepository.save(returnOrder.get());
        Map<String, String> data = new HashMap<>();
        data.put("message","快递单号填写成功");
        data.put("item_Id", returnId);
        return Result.success(data);
    }

    // 退货单确认收货
    public Result<Map<String, String>> confirmReceive(String returnId) {
        Optional<Return> returnOrder = returnRepository.findByItemId(returnId);
        if (returnOrder.isEmpty()){
            return Result.error(404, "想确认收货的退货单不存在！");
        }
        if(!returnOrder.get().getReturnStatus().equals(Return.ReturnStatus.已收货)){
            return Result.error(400, "无法重复确认收货！");
        }
        returnOrder.get().setReturnStatus(Return.ReturnStatus.已收货);
        returnRepository.save(returnOrder.get());
        Map<String, String> data = new HashMap<>();
        data.put("message","卖家确认收货成功！");
        data.put("item_Id", returnId);
        return Result.success(data);
    }

    // 更改订单项为退款状态
    public Result<Map<String,String>> refundOrderItem(String returnId) {
        Optional<Return> returnOrder = returnRepository.findByItemId(returnId);
        if (returnOrder.isEmpty()){
            return Result.error(404, "找不到用于退款的退货单！");
        }
        returnOrder.get().setReturnStatus(Return.ReturnStatus.已退款);
        returnRepository.save(returnOrder.get());
        Map<String, String> data = new HashMap<>();
        data.put("message","退款状态修改成功！");
        return Result.success(data);
    }

}

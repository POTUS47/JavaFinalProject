package com.finalproject.service;

import com.finalproject.DTO.AfterSellDTOs;
import com.finalproject.DTO.Result;
import com.finalproject.model.Complain;
import com.finalproject.repository.ComplainRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class ComplainService {
    @Resource
    private ComplainRepository complainRepository;

    // 创建退货申诉
    public Result<Map<String, String>> applyComplain(String orderItemId, String buyer_reason
            ,String seller_reason) {
        // 保存退货申请到退货表
        Complain complain = new Complain();
        complain.setComplainStatus(Complain.ComplainStatus.待审核);
        complain.setBuyerReason(buyer_reason);
        complain.setItemId(orderItemId);
        complain.setComplainTime(LocalDateTime.now());
        complain.setSellerReason(seller_reason);
        complainRepository.save(complain);
        Map<String, String> data = new HashMap<>();
        data.put("message", "退货申诉提交成功！");
        data.put("item_Id", orderItemId);
        return Result.success(data);
    }

    // 审批退货申诉
    public Result<Map<String, String>> approveComplain(String orderItemId,boolean isApproved,
                                                     String reason,String adminId) {
        // 保存退货申请到退货表
        Optional<Complain> complain=complainRepository.findByItemId(orderItemId);
        if(complain.isEmpty()) {
            return Result.error(404, "想要审核的申诉单不存在");
        }
        complain.get().setResultReason(reason);
        complain.get().setIsComplainSuccess(isApproved);
        complain.get().setComplainStatus(Complain.ComplainStatus.审核完成);
        complain.get().setHandlerId(adminId);
        complainRepository.save(complain.get());
        Map<String, String> data = new HashMap<>();
        data.put("message", "退货申诉审核成功！");
        data.put("item_Id", orderItemId);
        data.put("handler_Id", adminId);
        return Result.success(data);
    }
    // 查看某退货申诉详情
    public Result<AfterSellDTOs.ComplainDTO> getComplainDetail(String itemId) {
        Optional<Complain> complain = complainRepository.findByItemId(itemId);
        if(complain.isEmpty()) {
            return Result.error(404, "想要查看详情的退货申诉不存在");
        }
        AfterSellDTOs.ComplainDTO response = new AfterSellDTOs.ComplainDTO();
        response.setItemId(itemId);
        response.setComplainStatus(complain.get().getComplainStatus().toString());
        response.setComplainDate(complain.get().getComplainTime());
        response.setComplainSuccess(complain.get().getIsComplainSuccess());
        response.setAdminId(complain.get().getHandlerId());
        response.setBuyerReason(complain.get().getBuyerReason());
        response.setSellerReason(complain.get().getSellerReason());
        response.setResultReason(complain.get().getResultReason());
        return Result.success(response);
    }


    public Result<List<AfterSellDTOs.ComDTO>> getAllComplain() {
        List<AfterSellDTOs.ComDTO> response = new ArrayList<>();
        List<Complain> complainList = complainRepository.findAll();
        for (Complain complain : complainList) {
            AfterSellDTOs.ComDTO comDTO = new AfterSellDTOs.ComDTO();
            comDTO.setItemId(complain.getItemId());
            comDTO.setComplainStatus(complain.getComplainStatus().toString());
            comDTO.setComplainDate(complain.getComplainTime());
            comDTO.setComplainSuccess(complain.getIsComplainSuccess());
            comDTO.setBuyerReason(complain.getBuyerReason());
            comDTO.setSellerReason(complain.getSellerReason());
            comDTO.setResultReason(complain.getResultReason());
            comDTO.setAdminId(complain.getHandlerId());
            response.add(comDTO);
        }
        return Result.success(response);
    }
}

package com.finalproject.service;

import com.finalproject.DTO.OneYuanShoppingRecordDTOs;
import com.finalproject.DTO.Result;
import com.finalproject.model.Buyer;
import com.finalproject.model.OneYuanShoppingRecord;
import com.finalproject.repository.BuyerRepository;
import com.finalproject.repository.OneYuanShoppingRecordRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OneYuanShoppingRecordService {

    private final OneYuanShoppingRecordRepository recordRepository;
    private final BuyerRepository buyerRepository;

    // 构造函数注入
    public OneYuanShoppingRecordService(OneYuanShoppingRecordRepository recordRepository, 
                                         BuyerRepository buyerRepository) {
        this.recordRepository = recordRepository;
        this.buyerRepository = buyerRepository;
    }

    @Transactional
    public Result<List<OneYuanShoppingRecordDTO>> getParticipatedRecords(String userId) {
        // 查询买家信息
        Optional<Buyer> buyerOpt = buyerRepository.findById(userId);
        if (buyerOpt.isEmpty()) {
            return Result.error(404, "未找到买家信息");
        }

        // 查询用户参与的一元购记录
        List<OneYuanShoppingRecord> records = recordRepository.findByParticipantsContaining(userId);
        if (records.isEmpty()) {
            return Result.error(404, "没有参与任何一元购");
        }

        // 构造返回的DTO列表
        List<OneYuanShoppingRecordDTO> recordDTOs = new ArrayList<>();
        for (OneYuanShoppingRecord record : records) {
            OneYuanShoppingRecordDTO recordDTO = new OneYuanShoppingRecordDTO();
            recordDTO.setRecordId(record.getRecordId());
            recordDTO.setProductId(record.getProductId());
            recordDTO.setStartTime(record.getStartTime());
            recordDTO.setEndTime(record.getEndTime());
            recordDTO.setMinParticipants(record.getMinParticipants());
            recordDTO.setDrawn(record.isDrawn());
            recordDTO.setResult(record.getResult());
            
            recordDTOs.add(recordDTO);
        }

        return Result.success(recordDTOs);
    }
}
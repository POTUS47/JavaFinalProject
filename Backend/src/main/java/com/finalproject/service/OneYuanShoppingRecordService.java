package com.finalproject.service;

import com.finalproject.DTO.OneYuanShoppingRecordDTOs.*;
import com.finalproject.DTO.Result;
import com.finalproject.model.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.finalproject.repository.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestClientException;
import org.springframework.core.ParameterizedTypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.client.RestTemplate;


@Service
public class OneYuanShoppingRecordService {
    private final OneYuanShoppingRecordRepository oneYuanShoppingRecordRepository;
    private final RecordParticipantRepository recordParticipantRepository;
    private static final Logger logger = LoggerFactory.getLogger(OneYuanShoppingRecordService.class);

    @Value("${api.base-url}")
    private String baseUrl;
    @Autowired
    private RestTemplate restTemplate;

    // 构造函数注入
    public OneYuanShoppingRecordService(OneYuanShoppingRecordRepository oneYuanShoppingRecordRepository,
                                        RecordParticipantRepository recordParticipantRepository,
                                        ProductRepository productRepository,
                                        ProductImageRepository productImageRepository) {
        this.oneYuanShoppingRecordRepository = oneYuanShoppingRecordRepository;
        this.recordParticipantRepository = recordParticipantRepository;
    }



    @Transactional
    public Result<?> createOneYuanRecord(
            @RequestBody CreateOneYuanDTO recordDTO,
            @RequestParam String productId,
            @RequestParam String userId
    ) {
        try {
            // 1. 检查商品是否存在
//            Product product = productRepository.findById(productId)
//                    .orElseThrow(() -> new RuntimeException("商品不存在"));
            // 2. 检查是否有权限创建一元购活动
//            if (!product.getStoreId().equals(userId)) {
//                return Result.error(403, "只有商品所有者可以创建一元购活动");
//            }
            // 3. 检查是否已经存在该商品的一元购活动
//            boolean exists = oneYuanShoppingRecordRepository.existsByProductId(productId);
//            if (exists) {
//                return Result.error(400, "该商品已存在一元购活动");
//            }
            // 4. 创建一元购记录
            OneYuanShoppingRecord record = new OneYuanShoppingRecord();

            // 从DTO复制基本信息
            if (recordDTO.getStartTime() == null) {
                return Result.error(400, "没有1start" );
            }
            record.setStartTime(recordDTO.getStartTime());
            if (record.getStartTime() == null) {
                return Result.error(400, "没有2start" );
            }

            if (recordDTO.getEndTime() == null) {
                return Result.error(400, "没有1end" );
            }
            record.setEndTime(recordDTO.getEndTime());
            if (record.getEndTime() == null) {
                return Result.error(400, "没有2end" );
            }

            record.setMinParticipants(recordDTO.getMinParticipants());

            // 设置其他必要信息
            record.setRecordId(generateUniqueRecordId());
            record.setProductId(productId);
            record.setCurrentParticipants(0);
            record.setDrawn(false);
            record.setResult(null);
            // 5. 保存记录
            OneYuanShoppingRecord savedRecord = oneYuanShoppingRecordRepository.save(record);

            return Result.success("一元购活动创建成功", savedRecord.getRecordId());
        } catch (Exception e) {
            return Result.error(500, "创建一元购活动失败：" + e.getMessage());
        }
    }

    // 生成唯一ID的方法
    private String generateUniqueRecordId() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    @Transactional
    public Result<List<OneYuanShoppingRecordDTO>> getParticipatedRecords(String userId) {
        // 查询用户参与的所有记录
        List<RecordParticipant> participations = recordParticipantRepository.findByAccountId(userId);
        if (participations.isEmpty()) {
            return Result.error(404, "未找到参与记录");
        }

        // 构造返回的DTO列表
        List<OneYuanShoppingRecordDTO> records = new ArrayList<>();

        // 遍历参与记录
        for (RecordParticipant participant : participations) {
            Optional<OneYuanShoppingRecord> recordOpt = oneYuanShoppingRecordRepository
                    .findById(participant.getRecordId());

            if (recordOpt.isPresent()) {
                OneYuanShoppingRecord record = recordOpt.get();
                OneYuanShoppingRecordDTO dto = new OneYuanShoppingRecordDTO();

                // 设置基本信息
                dto.setRecordId(record.getRecordId());
                dto.setStartTime(record.getStartTime());
                dto.setEndTime(record.getEndTime());
                dto.setMinParticipants(record.getMinParticipants());
                dto.setDrawn(record.isDrawn());
                dto.setResult(record.getResult());
                dto.setCurrentParticipants(record.getCurrentParticipants());
                dto.setProductId(record.getProductId());
                records.add(dto);
            }
        }

        return Result.success(records); }


    @Transactional
    public Result<List<OneYuanShoppingRecordDTO>> getAllRecords() {
        // 查询所有的一元购记录
        List<OneYuanShoppingRecord> records = oneYuanShoppingRecordRepository.findAll();

        if (records.isEmpty()) {
            return Result.error(404, "未找到任何一元购记录");
        }

        // 构造返回的DTO列表
        List<OneYuanShoppingRecordDTO> recordDTOs = new ArrayList<>();

        // 遍历记录
        for (OneYuanShoppingRecord record : records) {
            OneYuanShoppingRecordDTO dto = new OneYuanShoppingRecordDTO();

            // 设置基本信息
            dto.setRecordId(record.getRecordId());
            dto.setStartTime(record.getStartTime());
            dto.setEndTime(record.getEndTime());
            dto.setMinParticipants(record.getMinParticipants());
            dto.setDrawn(record.isDrawn());
            dto.setResult(record.getResult());
            dto.setCurrentParticipants(record.getCurrentParticipants());
            dto.setProductId(record.getProductId());

            recordDTOs.add(dto);
        }

        return Result.success(recordDTOs);
    }

    @Transactional
    public Result<List<OneYuanShoppingRecordDTO>> getAllProperRecords() {
        // 获取当前时间
        LocalDateTime now = LocalDateTime.now();

        // 查询符合条件的一元购记录
        List<OneYuanShoppingRecord> records = oneYuanShoppingRecordRepository.findAll().stream()
                .filter(record ->
                        record.getStartTime().isBefore(now) &&
                                record.getEndTime().isAfter(now)
                )
                .collect(Collectors.toList());

        if (records.isEmpty()) {
            return Result.error(404, "未找到正在进行的一元购记录");
        }

        // 构造返回的DTO列表
        List<OneYuanShoppingRecordDTO> recordDTOs = records.stream()
                .map(record -> {
                    OneYuanShoppingRecordDTO dto = new OneYuanShoppingRecordDTO();
                    dto.setRecordId(record.getRecordId());
                    dto.setStartTime(record.getStartTime());
                    dto.setEndTime(record.getEndTime());
                    dto.setMinParticipants(record.getMinParticipants());
                    dto.setDrawn(record.isDrawn());
                    dto.setResult(record.getResult());
                    dto.setCurrentParticipants(record.getCurrentParticipants());
                    dto.setProductId(record.getProductId());
                    return dto;
                })
                .collect(Collectors.toList());

        return Result.success(recordDTOs);
    }


    @Transactional
    public Result<?> participate(String recordId, String accountId) {
        // 1. 检查活动是否存在
        Optional<OneYuanShoppingRecord> recordOpt = oneYuanShoppingRecordRepository.findById(recordId);
        if (recordOpt.isEmpty()) {
            return Result.error(404, "活动不存在");
        }

        OneYuanShoppingRecord record = recordOpt.get();

        // 2. 检查是否已经参与过
        if (recordParticipantRepository.existsByAccountIdAndRecordId(accountId, recordId)) {
            return Result.error(409, "您已参与过该活动");
        }


        // 3. 检查活动状态
        LocalDateTime now = LocalDateTime.now();
        if (now.isBefore(record.getStartTime()) || now.isAfter(record.getEndTime())) {
            return Result.error(409, "活动未开始或已结束");
        }

        if (record.isDrawn()) {
            return Result.error(409, "活动已开奖");
        }

        // 4. 创建参与记录
        RecordParticipant participant = new RecordParticipant();
        participant.setRecordId(recordId);
        participant.setAccountId(accountId);



        // 5. 保存记录
        try {
            record.setCurrentParticipants(record.getCurrentParticipants() + 1);
            oneYuanShoppingRecordRepository.save(record);
            recordParticipantRepository.save(participant);
            return Result.success("参与成功");
        } catch (Exception e) {
            return Result.error(500, "系统错误，请稍后重试");
        }
    }

    @Transactional
    public Result<?> drawWinner(String recordId) {
        // 1. 检查活动是否存在
        Optional<OneYuanShoppingRecord> recordOpt = oneYuanShoppingRecordRepository.findById(recordId);
        if (recordOpt.isEmpty()) {
            return Result.error(404, "活动不存在");
        }

        OneYuanShoppingRecord record = recordOpt.get();

        // 2. 检查活动状态
        if (record.isDrawn()) {
            return Result.error(409, "活动已开奖");
        }

        LocalDateTime now = LocalDateTime.now();
        if (now.isAfter(record.getEndTime())) {
            return Result.error(409, "活动尚未结束，不能开奖");
        }

        // 3. 检查参与人数
        List<RecordParticipant> participants = recordParticipantRepository.findByRecordId(recordId);
        int participantCount = participants.size();

        if (participantCount < record.getMinParticipants()) {
            return Result.error(409, "参与人数未达到最小要求，无法开奖");
        }

        // 4. 随机抽取获奖者
        int winnerIndex = (int) (Math.random() * participantCount);
        String winnerId = participants.get(winnerIndex).getAccountId();

        // 5. 更新活动状态
        try {
            record.setDrawn(true);
            record.setResult(winnerId);
            oneYuanShoppingRecordRepository.save(record);

            return Result.success("开奖成功，获奖者ID: " + winnerId);
        } catch (Exception e) {
            return Result.error(500, "开奖过程中发生错误");
        }
    }

    private String getProductStoreIdFromSubsystem(String productId) {
        String url = baseUrl + "/api/productController/getAccountIdByProductId/" + productId;
            ResponseEntity<Result<String>> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<>() {
                    } );
            logger.debug("商家ID: {}", response.getBody().getData());
            return response.getBody().getData();
    }



    @Transactional
    public Result<List<OneYuanShoppingRecordDTO>> getStoreOneYuanRecords(String storeId) {
        try {
            // 1. 查询所有一元购记录
            List<OneYuanShoppingRecord> allRecords = oneYuanShoppingRecordRepository.findAll();

            // 2. 过滤并收集符合条件的记录
            List<OneYuanShoppingRecordDTO> storeRecords = new ArrayList<>();

            for (OneYuanShoppingRecord record : allRecords) {
                // 通过新方法获取商品的商家ID
                String productStoreIdOptional = getProductStoreIdFromSubsystem(record.getProductId());
//                logger.debug("商家ID: {}", productStoreIdOptional);
                // 检查商家ID是否匹配
                if (productStoreIdOptional != null && storeId.equals(productStoreIdOptional)) {
                    OneYuanShoppingRecordDTO dto = convertToDTO(record);
                    storeRecords.add(dto);
                }
            }

            // 3. 返回结果
            if (storeRecords.isEmpty()) {
                return Result.error(404, "该商家没有一元购记录");
            }

            return Result.success(storeRecords);

        } catch (Exception e) {
            return Result.error(500, "获取商家一元购记录失败：" + e.getMessage());
        }
    }

    // 辅助转换方法
    private OneYuanShoppingRecordDTO convertToDTO(OneYuanShoppingRecord record) {
        OneYuanShoppingRecordDTO dto = new OneYuanShoppingRecordDTO();
        dto.setRecordId(record.getRecordId());
        dto.setStartTime(record.getStartTime());
        dto.setEndTime(record.getEndTime());
        dto.setMinParticipants(record.getMinParticipants());
        dto.setDrawn(record.isDrawn());
        dto.setResult(record.getResult());
        dto.setCurrentParticipants(record.getCurrentParticipants());
        dto.setProductId(record.getProductId());
        return dto;
    }

    @Transactional
    public Result<OneYuanShoppingRecordDTO> getRecordById(String recordId) {
        try {
            // 1. 查询记录是否存在
            Optional<OneYuanShoppingRecord> recordOpt = oneYuanShoppingRecordRepository.findById(recordId);
            if (recordOpt.isEmpty()) {
                return Result.error(404, "未找到该一元购记录");
            }

            // 2. 转换为DTO
            OneYuanShoppingRecord record = recordOpt.get();
            OneYuanShoppingRecordDTO dto = convertToDTO(record);

            return Result.success(dto);

        } catch (Exception e) {
            return Result.error(500, "获取一元购记录失败：" + e.getMessage());
        }
    }

}

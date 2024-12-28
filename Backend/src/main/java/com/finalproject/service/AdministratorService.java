package com.finalproject.service;
import com.finalproject.DTO.AdministratorDTOs.*;
import com.finalproject.DTO.ImageModels.*;
import com.finalproject.model.*;
import com.finalproject.repository.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdministratorService {

    @Autowired
    private SubmitAuthenticationRepository submitAuthenticationRepository;
    @Autowired
    private MarketRepository marketRepository;
    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private SubCategoryRepository subCategoryRepository;
    @Autowired
    private MarketStoreRepository marketStoreRepository;
    @Autowired
    private StoreBusinessDirectionRepository storeBusinessDirectionRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public List<ShowAuthenticationDTO> getAllAuthentication(String adminId) {

        List<SubmitAuthentication> authentications = submitAuthenticationRepository.findByadministratorAccountId(adminId);
        return authentications.stream()
                .map(auth -> new ShowAuthenticationDTO(
                        auth.getStoreAccountId(),
                        auth.getAuthentication(),
                        auth.getStatus(),
                        new AuthImageModel(auth.getStoreAccountId())
                )).collect(Collectors.toList());
    }

    @Transactional
    public String updateStoreAuthentication(USAModel model) {
        // 查找提交的认证
        SubmitAuthentication temp = submitAuthenticationRepository
                .findByStoreAccountId(model.getStoreId())
                .orElse(null);

        // 查找商店
        Store tempStore = storeRepository
                .findByAccountId(model.getStoreId())
                .orElse(null);

        if (temp == null) {
            System.out.println("No authentication found for "+model.getStoreId());
            return "No authentication found for the given store.";
        }

        // 确保商店存在
        if (tempStore == null) {
            System.out.println("No store found for "+model.getStoreId());
            return "Store not found.";
        }

        // 根据结果更新状态
        if (model.isResult()) {
            temp.setStatus("已通过");
            temp.setAdministratorAccountId(model.getAdminId());
            tempStore.setCertification(1);
        } else {
            temp.setStatus("已拒绝");
            temp.setAdministratorAccountId(model.getAdminId());
            tempStore.setCertification(0);
        }

        // 保存更改
        try {
            submitAuthenticationRepository.save(temp);
            storeRepository.save(tempStore);
            return String.format("申请已被处理，处理结果:%s，处理人:%s", temp.getStatus(), model.getAdminId());
        } catch (Exception e) {
            return "An error occurred while updating the database: " + e.getMessage();
        }
    }

    @Transactional
    public String inviteStores(ISModel model) {
        // 根据传入的小类Id找到小类名称
        SubCategory subCategory = subCategoryRepository.findBySubCategoryId(model.getInviteTag())
                .orElse(null);

        if (subCategory == null) {
            return "传入错误的小分类Id";
        }

        // 获取小分类名称
        String searchString = subCategory.getCategoryName() + subCategory.getSubcategoryName();


        // 查找符合条件的商家
        List<StoreBusinessDirection> storeBusinessDirections = storeBusinessDirectionRepository
                .findByBusinessTagContaining(searchString);



        for (StoreBusinessDirection storeBusinessDirection : storeBusinessDirections) {
            String storeId = storeBusinessDirection.getStoreId();
            System.out.println(storeId);


            // 检查商家是否已经被邀请
            // 使用自定义查询方法查找是否已经存在该商家
            MarketStore existingStore = marketStoreRepository
                    .findByMarketIdAndStoreAccountId(model.getMarketId(),storeId)
                    .orElse(null);

            System.out.println(existingStore!=null ? "在":"不在");

            if (existingStore != null) {
                continue; // 如果商家已经存在于市场中，跳过
            }
            System.out.println("准备添加到marketstore");

            // 添加商家到市场
            MarketStore marketStore = new MarketStore();

            // 直接设置主键（marketId）
            marketStore.setMarketId(model.getMarketId());

            // 设置storeAccountId
            marketStore.setStoreAccountId(storeId);

            // 设置其他字段
            marketStore.setInOrNot(false);

            // 打印准备保存的数据
            System.out.println("准备保存的数据:");
            System.out.println("Market ID: " + marketStore.getMarketId());
            System.out.println("Store Account ID: " + marketStore.getStoreAccountId());
            System.out.println("In Or Not: " + marketStore.isInOrNot());

            try {
                System.out.println("保存");
                marketStoreRepository.insertMarketStore(
                        model.getMarketId(),
                        storeId,
                        false
                );
                System.out.println("保存成功");
            } catch (Exception e) {
                e.printStackTrace(); // 打印堆栈跟踪以获取更多信息
                return "An error occurred while updating the database: " + e.getMessage();
            }


        }

        return String.format("邀请商家成功，邀请Tag为%s", searchString);
    }


    public List<Market> getAllMarkets() {
        return marketRepository.findAll();
    }








}
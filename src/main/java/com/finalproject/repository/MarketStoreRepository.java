package com.finalproject.repository;

import com.finalproject.model.MarketStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MarketStoreRepository extends JpaRepository<MarketStore, String> { // 注意这里主键类型是String

    // 使用JPQL查询来替代之前的复合主键查询
//    @Query("SELECT ms FROM MarketStore ms WHERE ms.marketId = :marketId AND ms.storeAccountId = :storeAccountId")
//    Optional<MarketStore> findByStoreAccountIdAndMarketId(
//            @Param("storeAccountId") String storeAccountId,
//            @Param("marketId") String marketId);

    // 或者你可以直接使用Spring Data JPA提供的方法命名规则
     Optional<MarketStore> findByMarketIdAndStoreAccountId(String marketId, String storeAccountId);

    @Modifying
    @Query(value = "INSERT INTO market_store (market_id, store_account_id, in_or_not) VALUES (:marketId, :storeId, :inOrNot)", nativeQuery = true)
    void insertMarketStore(@Param("marketId") String marketId, @Param("storeId") String storeId, @Param("inOrNot") boolean inOrNot);
}

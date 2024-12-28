package com.finalproject.repository;

import com.finalproject.model.StoreBusinessDirection;
import com.finalproject.model.SubCategory;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StoreBusinessDirectionRepository extends JpaRepository<StoreBusinessDirection, String> {
    List<StoreBusinessDirection> findByBusinessTagContaining(String businessTag);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO store_business_direction (store_id, business_tag, link_count) VALUES (:storeId, :businessTag, :linkCount)", nativeQuery = true)
    void insertNewStoreBusinessDirection(@Param("storeId") String storeId, @Param("businessTag") String businessTag,@Param("linkCount")int linkCount);

    @Modifying
    @Transactional
    @Query(value = "UPDATE store_business_direction SET link_count = :linkCount  WHERE store_id = :storeId AND business_tag = :businessTag", nativeQuery = true)
    void updateLinkCount(@Param("linkCount") int link_count ,@Param("storeId") String storeId, @Param("businessTag") String businessTag);

    Optional<StoreBusinessDirection> findByStoreIdAndBusinessTag(String storeId, String businessTag);

}

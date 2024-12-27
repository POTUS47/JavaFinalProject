package com.finalproject.repository;

import com.finalproject.model.StoreBusinessDirection;
import com.finalproject.model.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StoreBusinessDirectionRepository extends JpaRepository<StoreBusinessDirection, String> {
    List<StoreBusinessDirection> findByBusinessTagContaining(String businessTag);

    Optional<StoreBusinessDirection> findByStoreIdAndBusinessTag(String storeId, String businessTag);

}

package com.finalproject.repository;

import com.finalproject.model.StoreBusinessDirection;
import com.finalproject.model.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreBusinessDirectionRepository extends JpaRepository<StoreBusinessDirection, Integer> {
    List<StoreBusinessDirection> findByBusinessTagContaining(String businessTag);
}

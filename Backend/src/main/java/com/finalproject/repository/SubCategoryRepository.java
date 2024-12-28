package com.finalproject.repository;
import com.finalproject.model.Store;
import com.finalproject.model.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, String> {
    Optional<SubCategory> findBySubCategoryId(String subCategoryId);
}

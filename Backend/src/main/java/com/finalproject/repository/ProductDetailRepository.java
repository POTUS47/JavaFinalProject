package com.finalproject.repository;
import com.finalproject.DTO.ProductDTOs.*;
import com.finalproject.model.Buyer;
import com.finalproject.model.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;


@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetail, String> {

    Collection<ProductDetail> findByProductId(String productId);

    void deleteByProductId(String productId);
}

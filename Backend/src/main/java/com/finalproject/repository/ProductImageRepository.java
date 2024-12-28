package com.finalproject.repository;
import com.finalproject.model.Buyer;
import com.finalproject.model.Image;
import com.finalproject.model.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, String> {

    List<ProductImage> findByProductId(String productId);

    Optional<ProductImage> findFirstByProductId(String productId);
}

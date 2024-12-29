package com.finalproject.repository;
import com.finalproject.model.Buyer;
import com.finalproject.model.Product;
import com.finalproject.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    List<Product> findByStoreId(String storeId);
    @Query("SELECT p FROM Product p WHERE p.storeId = :storeId AND " +
            "(p.productName LIKE %:keyword% OR p.productName LIKE %:likePattern%)")
    List<Product> searchProducts(@Param("storeId") String storeId,
                                 @Param("keyword") String keyword,
                                 @Param("likePattern") String likePattern);

    List<Product> findBysubCategory(String subCategory);

    List<Product> findBytag(String name);
}
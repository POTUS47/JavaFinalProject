package com.finalproject.repository;
import com.finalproject.model.BookmarkProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuyerProductBookmarkRepository extends JpaRepository<BookmarkProduct, String> {

    boolean existsByBuyerIdAndProductId(String userId, String productId);

    @Modifying
    @Query(value = "INSERT INTO bookmark_product (buyer_id,product_id) VALUES (:buyerId, :productId)", nativeQuery = true)
    void insertBookmarkProduct(@Param("buyerId") String buyerId, @Param("productId") String productId);

    @Modifying
    @Query(value = "DELETE FROM bookmark_product WHERE buyer_id = :buyerId AND product_id = :productId", nativeQuery = true)
    void deleteBookmarkProduct(@Param("buyerId") String buyerId, @Param("productId") String productId);

    void deleteByBuyerIdAndProductId(String userId, String productId);

    List<BookmarkProduct> findByBuyerId(String userId);
}

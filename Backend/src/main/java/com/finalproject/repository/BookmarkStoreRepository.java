package com.finalproject.repository;
import com.finalproject.model.BookmarkStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BookmarkStoreRepository extends JpaRepository<BookmarkStore, String> {

    boolean existsByBuyerIdAndStoreId(String userId, String storeId);

    void deleteByBuyerIdAndStoreId(String userId, String storeId);

    @Modifying
    @Query(value = "INSERT INTO bookmark_store (buyer_id,store_id) VALUES (:buyerId, :storeId)", nativeQuery = true)
    void insertBookmarkStore(@Param("buyerId") String buyerId, @Param("storeId") String storeId);

    @Modifying
    @Query(value = "DELETE FROM bookmark_store WHERE buyer_id = :buyerId AND store_id = :storeId", nativeQuery = true)
    void deleteBookmarkStore(@Param("buyerId") String buyerId, @Param("storeId") String storeId);

    @Query("SELECT DISTINCT b FROM BookmarkStore b WHERE b.buyerId = :userId")
    List<BookmarkStore> findByBuyerId(@Param("userId") String userId);
}

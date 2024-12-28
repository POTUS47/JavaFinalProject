package com.finalproject.repository;
import com.finalproject.model.Order;
import com.finalproject.model.OrderItem;
import com.finalproject.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, String> {

    List<OrderItem> findByOrderId(String orderId);

    List<OrderItem> findByProductId(String productId);

    @Query("SELECT oi FROM OrderItem oi " +
            "JOIN Order o ON oi.orderId = o.orderId " +
            "WHERE o.buyerId = :userId AND oi.itemId = :itemId")
    Optional<OrderItem> findOrderItemByUserIdAndItemId(@Param("userId") String userId,
                                                       @Param("itemId") String itemId);

    @Query("SELECT oi FROM OrderItem oi " +
            "JOIN Order o ON oi.orderId = o.orderId " +
            "WHERE o.storeId = :userId AND oi.itemId = :itemId")
    Optional<OrderItem> findOrderItemByStoreIdAndItemId(@Param("userId") String userId,
                                                        @Param("itemId") String itemId);

    @Query("SELECT oi FROM OrderItem oi " +
            "JOIN Order o ON oi.orderId = o.orderId " +
            "WHERE o.buyerId = :buyerId AND oi.itemStatus = '售后中'")
    Optional<List<OrderItem>> findAfterSalesOrderItemsByBuyerId(@Param("buyerId") String buyerId);

    @Query("SELECT oi FROM OrderItem oi " +
            "JOIN Order o ON oi.orderId = o.orderId " +
            "WHERE o.buyerId = :buyerId AND oi.itemStatus = '售后结束'")
    Optional<List<OrderItem>>findHistoryAfterSalesOrderItemsByBuyerId(@Param("buyerId") String buyerId);

    @Query("SELECT oi FROM OrderItem oi " +
            "JOIN Order o ON oi.orderId = o.orderId " +
            "WHERE o.storeId = :storeId AND oi.itemStatus = '售后中'")
    Optional<List<OrderItem>> findAfterSalesOrderItemsByStoreId(@Param("storeId") String buyerId);

    @Query("SELECT oi FROM OrderItem oi " +
            "JOIN Order o ON oi.orderId = o.orderId " +
            "WHERE o.storeId = :storeId AND oi.itemStatus = '售后结束'")
    Optional<List<OrderItem>> findHistoryAfterSalesOrderItemsByStoreId(@Param("storeId") String buyerId);


    Optional<OrderItem> findByItemId(String returnId);
}

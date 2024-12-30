package com.finalproject.repository;
import com.finalproject.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Repository
public interface OrderRepository extends JpaRepository<Order, String> {

    List<Order> findByStoreId(String storeId);

    List<Order> findByBuyerId(String buyerId);

    Order findByStoreIdAndBuyerId(String storeId, String userId);

    List<Order> findByStoreIdAndOrderTimeBetween(String storeId, LocalDateTime startDate, LocalDateTime endDate);

    Optional<Order> findByOrderId(String orderId);

    @Query("SELECT COUNT(o) FROM Order o WHERE o.storeId = :storeId AND o.orderStatus = '处理中' AND o.paymentStatus = '已付款'")
    int getWaitingShip(String storeId);

    @Query("SELECT o.orderId FROM Order o WHERE o.storeId = :storeId")
    List<String> findOrderIdsByStoreId(String storeId);

    @Query("SELECT COUNT(o) FROM Order o WHERE DATE(o.orderTime) = CURRENT_DATE")
    int countOrdersByToday();

    @Query("SELECT COUNT(o) FROM Order o WHERE Date(o.orderTime) = :orderDate")
    int countOrdersByDate(LocalDate orderDate);


    @Query("SELECT SUM(o.totalPrice) FROM Order o WHERE DATE(o.orderTime) = CURRENT_DATE")
    BigDecimal getTodayRevenue();


//    Optional<List<Order>> findByBuyerId(String orderId);
}

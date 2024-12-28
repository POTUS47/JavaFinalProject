package com.finalproject.repository;
import com.finalproject.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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


//    Optional<List<Order>> findByBuyerId(String orderId);
}

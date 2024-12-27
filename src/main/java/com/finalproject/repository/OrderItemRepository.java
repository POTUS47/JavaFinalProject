package com.finalproject.repository;
import com.finalproject.model.Order;
import com.finalproject.model.OrderItem;
import com.finalproject.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, String> {

    List<OrderItem> findByOrderId(String orderId);

    List<OrderItem> findByProductId(String productId);
}

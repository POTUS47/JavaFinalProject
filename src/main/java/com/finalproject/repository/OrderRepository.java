package com.finalproject.repository;
import com.finalproject.model.Buyer;
import com.finalproject.model.Order;
import com.finalproject.model.Product;
import com.finalproject.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface OrderRepository extends JpaRepository<Order, String> {

    List<Order> findByStoreId(String storeId);

    Order findByStoreIdAndBuyerId(String storeId, String userId);

    Optional<List<Order>> findByBuyerId(String orderId);
}

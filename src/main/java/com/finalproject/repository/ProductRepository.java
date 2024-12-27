package com.finalproject.repository;
import com.finalproject.model.Buyer;
import com.finalproject.model.Product;
import com.finalproject.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    List<Product> findByStoreId(String storeId);
}
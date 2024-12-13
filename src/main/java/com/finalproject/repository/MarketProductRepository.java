package com.finalproject.repository;
import com.finalproject.model.Buyer;
import com.finalproject.model.MarketProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MarketProductRepository extends JpaRepository<MarketProduct, Integer> {

}

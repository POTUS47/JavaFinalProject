package com.finalproject.repository;
import com.finalproject.model.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface BuyerRepository extends JpaRepository<Buyer, String> {

    Optional<Buyer> findByAccountId(String accountId);
}

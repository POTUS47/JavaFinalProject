package com.finalproject.repository;
import com.finalproject.model.Buyer;
import com.finalproject.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface WalletRepository extends JpaRepository<Wallet, Integer> {

    Optional<Wallet> findByAccountId(String userId);
}

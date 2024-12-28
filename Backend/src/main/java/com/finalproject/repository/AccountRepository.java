package com.finalproject.repository;
import com.finalproject.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>{

    Optional<Account> findByAccountId(String accountId);
    Optional<Account> findByEmail(String email);
}


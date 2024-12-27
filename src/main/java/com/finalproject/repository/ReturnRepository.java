package com.finalproject.repository;
import com.finalproject.model.Account;
import com.finalproject.model.Buyer;
import com.finalproject.model.Return;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ReturnRepository extends JpaRepository<Return, Integer> {
    Optional<Return> findByItemId(String itemId);

}


package com.finalproject.repository;

import com.finalproject.model.Complain;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ComplainRepository extends JpaRepository<Complain, String> {

    Optional<Complain> findByItemId(String orderItemId);
}

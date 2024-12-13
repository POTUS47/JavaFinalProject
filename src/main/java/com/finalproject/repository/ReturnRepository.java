package com.finalproject.repository;
import com.finalproject.model.Buyer;
import com.finalproject.model.Return;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ReturnRepository extends JpaRepository<Return, Integer> {

}


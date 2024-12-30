package com.finalproject.repository;
import com.finalproject.model.Account;
import com.finalproject.model.Buyer;
import com.finalproject.model.Return;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ReturnRepository extends JpaRepository<Return, Integer> {
    Optional<Return> findByItemId(String itemId);

    @Query("SELECT COUNT(r) FROM Return r WHERE r.itemId IN :itemIds AND r.returnStatus = '待审核'")
    int countPendingReturnsByItemIds(List<String> itemIds);

}


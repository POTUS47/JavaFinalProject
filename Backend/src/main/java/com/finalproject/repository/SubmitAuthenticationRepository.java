package com.finalproject.repository;
import com.finalproject.model.Account;
import com.finalproject.model.SubmitAuthentication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubmitAuthenticationRepository extends JpaRepository<SubmitAuthentication, Integer> {

    // 按管理员 ID 查询所有认证
    List<SubmitAuthentication> findByadministratorAccountId(String adminId);

    Optional<SubmitAuthentication> findByStoreAccountId(String storeAccountId);


//
//    // 查询特定认证状态的记录
//    List<SubmitAuthentication> findByStatus(String status);


}


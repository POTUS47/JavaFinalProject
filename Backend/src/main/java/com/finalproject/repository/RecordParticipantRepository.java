package com.finalproject.repository;

import com.finalproject.model.RecordParticipant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


@Repository
public interface RecordParticipantRepository extends JpaRepository<RecordParticipant, String> {
    List<RecordParticipant> findByAccountId(String accountId);
    List<RecordParticipant> findByRecordId(String recordId);
    // 检查用户是否已参与特定记录
    boolean existsByAccountIdAndRecordId(String buyerId, String recordId);
    // 插入新的参与记录
    @Modifying
    @Query(value = "INSERT INTO record_participant (buyer_id, record_id, participate_time) VALUES (:buyerId, :recordId, :participateTime)", nativeQuery = true)
    void insertParticipant(String buyerId, String recordId, LocalDateTime participateTime);
}


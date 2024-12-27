package com.finalproject.repository;

import com.finalproject.model.OneYuanShoppingRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OneYuanShoppingRecordRepository  extends JpaRepository<OneYuanShoppingRecord, String> {
    List<OneYuanShoppingRecord> findByParticipantsContaining(String buyerId);
}

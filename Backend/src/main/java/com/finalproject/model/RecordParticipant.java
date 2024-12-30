package com.finalproject.model;

import jakarta.persistence.*;

import java.io.Serializable;


@Entity
@Table(name = "record_participants",
        uniqueConstraints = @UniqueConstraint(columnNames = {"record_id", "account_id"}))
public class RecordParticipant implements Serializable {

    @Id
    @Column(name = "record_id", nullable = false)
    private String recordId;

    @Column(name = "account_id", nullable = false)
    private String accountId;

    // 构造函数
    public RecordParticipant() {}

    // Getter 和 Setter
    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
}
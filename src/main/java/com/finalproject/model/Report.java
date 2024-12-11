package com.finalproject.model;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "report")
public class Report implements Serializable {

    @Id
    @Column(name = "report_id")
    private String reportId;

    @Column(name = "report_time")
    private LocalDateTime reportTime;

    @Column(name = "report_reason")
    private String reportReason;

    @Column(name = "audit_time")
    private LocalDateTime auditTime;

    @Column(name = "audit_results")
    private String auditResults;

    @Column(name = "administrator_account_id")
    private String administratorAccountId;

    @ManyToOne
    @JoinColumn(name = "administrator_account_id", insertable = false, updatable = false)
    private Administrator administrator;

    // Getters and Setters
    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public LocalDateTime getReportTime() {
        return reportTime;
    }

    public void setReportTime(LocalDateTime reportTime) {
        this.reportTime = reportTime;
    }

    public String getReportReason() {
        return reportReason;
    }

    public void setReportReason(String reportReason) {
        this.reportReason = reportReason;
    }

    public LocalDateTime getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(LocalDateTime auditTime) {
        this.auditTime = auditTime;
    }

    public String getAuditResults() {
        return auditResults;
    }

    public void setAuditResults(String auditResults) {
        this.auditResults = auditResults;
    }

    public String getAdministratorAccountId() {
        return administratorAccountId;
    }

    public void setAdministratorAccountId(String administratorAccountId) {
        this.administratorAccountId = administratorAccountId;
    }

    public Administrator getAdministrator() {
        return administrator;
    }

    public void setAdministrator(Administrator administrator) {
        this.administrator = administrator;
    }
}
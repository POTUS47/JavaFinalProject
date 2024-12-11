package com.finalproject.model;
import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "submit_authentication")
public class SubmitAuthentication implements Serializable {

    @Id
    @Column(name = "store_account_id")
    private String storeAccountId;

    @Column(name = "administrator_account_id")
    private String administratorAccountId;

    @Column(name = "authentication")
    private String authentication;

    @Column(name = "status")
    private String status;

    @Lob
    @Column(name = "photo")
    private byte[] photo;

    @ManyToOne
    @JoinColumn(name = "store_account_id", insertable = false, updatable = false)
    private Store store;

    @ManyToOne
    @JoinColumn(name = "administrator_account_id", insertable = false, updatable = false)
    private Administrator administrator;

    // Getters and Setters

    public String getStoreAccountId() {
        return storeAccountId;
    }

    public void setStoreAccountId(String storeAccountId) {
        this.storeAccountId = storeAccountId;
    }

    public String getAdministratorAccountId() {
        return administratorAccountId;
    }

    public void setAdministratorAccountId(String administratorAccountId) {
        this.administratorAccountId = administratorAccountId;
    }

    public String getAuthentication() {
        return authentication;
    }

    public void setAuthentication(String authentication) {
        this.authentication = authentication;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Administrator getAdministrator() {
        return administrator;
    }

    public void setAdministrator(Administrator administrator) {
        this.administrator = administrator;
    }
}
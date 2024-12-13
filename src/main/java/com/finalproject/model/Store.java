package com.finalproject.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "store")
public class Store extends Account implements Serializable {

    @Column(name = "store_name")
    private String storeName;

    @Column(name = "store_score")
    private BigDecimal storeScore;

    @Column(name = "certification")
    private Integer certification;

    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<StoreBusinessDirection> storeBusinessDirections = new ArrayList<>();

    // Getters and Setters

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public BigDecimal getStoreScore() {
        return storeScore;
    }

    public void setStoreScore(BigDecimal storeScore) {
        this.storeScore = storeScore;
    }

    public Integer isCertification() {
        return certification;
    }

    public void setCertification(Integer certification) {
        this.certification = certification;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<StoreBusinessDirection> getStoreBusinessDirections() {
        return storeBusinessDirections;
    }

    public void setStoreBusinessDirections(List<StoreBusinessDirection> storeBusinessDirections) {
        this.storeBusinessDirections = storeBusinessDirections;
    }

    public void addStoreBusinessDirection(StoreBusinessDirection storeBusinessDirection) {
        storeBusinessDirections.add(storeBusinessDirection);
        storeBusinessDirection.setStore(this);
    }

    public void removeStoreBusinessDirection(StoreBusinessDirection storeBusinessDirection) {
        storeBusinessDirections.remove(storeBusinessDirection);
        storeBusinessDirection.setStore(null);
    }

    @Override
    public String toString() {
        return "Store{" +
                "storeName='" + storeName + '\'' +
                ", storeScore=" + storeScore +
                ", certification=" + certification +
                ", address='" + address + '\'' +
                "} " + super.toString();
    }
}
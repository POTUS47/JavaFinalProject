package com.finalproject.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "store_business_direction")
public class StoreBusinessDirection implements Serializable {

    @Id
    @Column(name = "store_id")
    private String storeId;

    @Column(name = "business_tag")
    private String businessTag;

    @Column(name = "link_count")
    private int linkCount;

    @ManyToOne
    @JoinColumn(name = "store_id", insertable = false, updatable = false)
    private Store store;

    // Getters and Setters

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getBusinessTag() {
        return businessTag;
    }

    public void setBusinessTag(String businessTag) {
        this.businessTag = businessTag;
    }

    public int getLinkCount() {
        return linkCount;
    }

    public void setLinkCount(int linkCount) {
        this.linkCount = linkCount;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}
package com.finalproject.model;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("ADMIN")
public class Administrator extends Account {

    @Column(name = "permission_level")
    private Integer permissionLevel;

    // Getters and Setters
    public Integer getPermissionLevel() {
        return permissionLevel;
    }

    public void setPermissionLevel(Integer permissionLevel) {
        this.permissionLevel = permissionLevel;
    }

    @Override
    public String toString() {
        return "Administrator{" +
                "permissionLevel=" + permissionLevel +
                "} " + super.toString();
    }
}
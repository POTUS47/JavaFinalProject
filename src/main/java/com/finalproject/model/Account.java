package com.finalproject.model;
import java.io.Serializable;
import jakarta.persistence.*;

@Entity
@Table(name = "account") // 数据库表名
@Inheritance(strategy = InheritanceType.JOINED)
//@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.Type)
public class Account implements Serializable {

    @Id
    @Column(name = "account_id", nullable = false)
    private String accountId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "email")
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "description")
    private String description;

    // 新增字段，外键关联到 image 表的 IMAGE_ID
    @Column(name = "photo_id")
    private String photoId;

    @Enumerated(EnumType.STRING)
    @Column(name="type")
    private Type type;

    // 新增字段，用于表示是否已认证
    @Column(name = "has_certified", nullable = false)
    private boolean hasCertified;

    // Getters and Setters
    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhotoId() {
        return photoId;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

    public boolean isHasCertified() {
        return hasCertified;
    }

    public void setHasCertified(boolean hasCertified) {
        this.hasCertified = hasCertified;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId='" + accountId + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='[PROTECTED]'" + // 避免直接显示密码
                ", description='" + description + '\'' +
                ", photoId='" + photoId + '\'' +
                ", hasCertified=" + hasCertified +
                '}';
    }

    public enum Type {
        买家, 商家 ,管理员
    }
}

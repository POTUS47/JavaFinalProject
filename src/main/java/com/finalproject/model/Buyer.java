package com.finalproject.model;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue("BUYER") // 指定区分子类的值
public class Buyer extends Account {

    @Column(name = "gender")
    private String gender;

    @Column(name = "age")
    private Integer age; // 对应 C# 中的 int?，可为空

    @Column(name = "total_credits", nullable = false)
    private int totalCredits;

    @Column(name = "address")
    private String address;

    // Getters and Setters
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public int getTotalCredits() {
        return totalCredits;
    }

    public void setTotalCredits(int totalCredits) {
        this.totalCredits = totalCredits;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Buyer{" +
                "gender='" + gender + '\'' +
                ", age=" + age +
                ", totalCredits=" + totalCredits +
                ", address='" + address + '\'' +
                "} " + super.toString();
    }
}
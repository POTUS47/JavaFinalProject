package com.finalproject.model;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sub_category")
public class SubCategory implements Serializable {

    @Id
    @Column(name = "subcategory_id")
    private String subCategoryId;

    @Transient
    private String categoryName;

    @Column(name = "subcategory_name")
    private String subCategoryName;

    @ManyToOne
    @JoinColumn(name = "category_name", insertable = false, updatable = false)
    private Category category;

    @OneToMany(mappedBy = "subCategory", cascade = CascadeType.ALL)
    private List<Product> products = new ArrayList<>();

    // Getters and Setters

    public String getSubcategoryId() {
        return subCategoryId;
    }

    public void setSubcategoryId(String subcategoryId) {
        this.subCategoryId = subcategoryId;
    }

    public String getCategoryName() {
        if (category != null) {
            return category.getCategoryName();  // 通过关联的 Category 获取 categoryName
        }
        return null;  // 如果没有关联的 Category，返回 null 或者自定义处理逻辑
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getSubcategoryName() {
        return subCategoryName;
    }

    public void setSubcategoryName(String subcategoryName) {
        this.subCategoryName = subcategoryName;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }


    public void removeProduct(Product product) {
        products.remove(product);
        product.setSubCategory(null);
    }
}
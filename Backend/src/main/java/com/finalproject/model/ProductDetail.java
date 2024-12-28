package com.finalproject.model;
import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "product_detail")
public class ProductDetail implements Serializable {

    @Id
    @Column(name = "image_id")
    private String imageId;

    @Column(name = "product_id")
    private String productId;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "image_id", insertable = false, updatable = false)
    private Image image;

    // Getters and Setters

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }


}
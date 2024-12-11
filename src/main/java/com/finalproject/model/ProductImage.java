package com.finalproject.model;
import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "product_image")
public class ProductImage implements Serializable {

    @Id
    @Column(name = "image_id")
    private String imageId;

    @Column(name = "product_id")
    private String productId;

    @Lob
    @Column(name = "image")
    private byte[] image;

    @ManyToOne
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private Product product;

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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
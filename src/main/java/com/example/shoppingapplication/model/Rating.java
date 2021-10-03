package com.example.shoppingapplication.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "ratings")
public class Rating implements Serializable {
    public static enum Rate {
        ONE, TWO, THREE, FOUR, FIVE
    }

    @Id
    @Column(name = "product_id")
    private Long productId;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "rate")
    private Rate rate;

    @Id
    @Column(name = "username")
    private String username;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonManagedReference
    public Product product;

    public Rating() {    }

    public Rating(Long productId, Rate rate, String username) {
        this.productId = productId;
        this.rate = rate;
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rating rating = (Rating) o;
        return Objects.equals(productId, rating.productId);
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId);
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Rate getRate() {
        return rate;
    }

    public void setRate(Rate rate) {
        this.rate = rate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

package com.example.shoppingapplication.model;

import java.util.ArrayList;
import java.util.Objects;

public class Product {

    private Long id;
    private String productName;
    private double price;
    private ArrayList<Rating> rates;
    private ArrayList<Comment> comments;

    public Product() {
    }

    public Product(Long id, String productName, double price) {
        this.id = id;
        this.productName = productName;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ArrayList<Rating> getRates() {
        return rates;
    }

    public void setRates(ArrayList<Rating> rates) {
        this.rates = rates;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", rates=" + rates +
                ", comments=" + comments +
                '}';
    }
}

package com.example.shoppingapplication.model;

public class Comment {
    private String title;
    private Long productId;
    private Product product;

    public Comment() {
    }

    public Comment(String title, Long productId) {
        this.title = title;
        this.productId = productId;
    }

    public Product getProduct() {
        return product;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}

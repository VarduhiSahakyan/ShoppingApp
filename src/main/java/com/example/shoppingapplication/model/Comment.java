package com.example.shoppingapplication.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "username")
    private String username;

    @Column(name = "comment")
    private String comment;

    public Comment() {}

    public Comment(long id, Product product, String username, String message) {
        this.id = id;
        this.product = product;
        this.username = username;
        comment = message;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comments = (Comment) o;
        return id == comments.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @JsonBackReference
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String message) {
        this.comment = message;
    }
}

/*
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue
    private Long productId;

    @Column(name = "title")
    private String title;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "comment")
    private String comment;

    public Comment() {
    }

    public Comment(Long commentId, String title, Long productId) {
        this.commentId = commentId;
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


 */
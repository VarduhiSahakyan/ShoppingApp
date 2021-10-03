package com.example.shoppingapplication.service;


import com.example.shoppingapplication.model.Comment;
import com.example.shoppingapplication.model.Product;
import com.example.shoppingapplication.model.Rating;
import com.example.shoppingapplication.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Service("productService")
public class ProductService {
    private final UserService userService;
    private final ProductRepository productRepository;
    @PersistenceContext
    private final EntityManager entityManager;


    @Autowired
    public ProductService(UserService userService, ProductRepository productRepository, EntityManager entityManager) {
        this.userService = userService;
        this.productRepository = productRepository;

        this.entityManager = entityManager;
    }
    public void createProduct(Product product) {
        productRepository.save(product);
    }

    public void deleteProduct(Long productId) {
        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            productRepository.delete(product);
        }
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public List<Product> getAllAndPriceBetween(Double min, Double max) {
        if (min == null) min = 0.0d;
        if (max == null) max = Double.MAX_VALUE - 1;

        return entityManager.createQuery(
                "SELECT p FROM Product p where p.price > :min_price and p.price < :max_price",
                Product.class)
                .setParameter("min_price", min)
                .setParameter("max_price", max)
                .getResultList();
    }

    public List<Product> getAllByName(String name) {
        return productRepository.findByNameContains(name);
    }

    @Transactional
    public void getProductReting(Long productId, Rating.Rate rating) {
        List<Rating> resultList;
        resultList = entityManager.createQuery(
                "SELECT r FROM Rating r where r.productId = :pId",
                Rating.class)
                .setParameter("pId", productId)
                .getResultList();

        if (resultList.size() == 0) {
            entityManager.createNativeQuery(
                    "insert into rates ( product_id, rate) VALUES (?, ?)")
                    .setParameter(1, productId)
                    .setParameter(2, rating.name())
                    .executeUpdate();
        } else {
            entityManager.createNativeQuery(
                    "update rates r set rate = ? where r.product_id = ?")
                    .setParameter(1, rating.name())
                    .setParameter(2, productId)
                    .executeUpdate();
        }
    }

    @Transactional
    public void deleteRating(String username, Long productId) {
        entityManager.createNativeQuery(
                "DELETE FROM ratings r WHERE r.product_id = ?")
                .setParameter(1, productId)
                .executeUpdate();
    }



    @Transactional
    public void addComment(String username, Long productId, String comment) {
        entityManager.createNativeQuery(
                "insert into comments (username, product_id, comment) values (?, ?, ?)")
                .setParameter(1, username)
                .setParameter(2, productId)
                .setParameter(3, comment)
                .executeUpdate();
    }

    @Transactional
    public void deleteComment(String username, Long commentId) {
        List<Comment> list = entityManager.createQuery(
                "SELECT c FROM Comment c where c.id = : cId", Comment.class)
                .setParameter("cId", commentId)
                .getResultList();

        if (list.size() > 0) {
            String uName = list.get(0).getUsername();
            if (uName.equals(username)) {
                entityManager.createNativeQuery(
                        "DELETE FROM comments c WHERE c.id = ?")
                        .setParameter(1, commentId)
                        .executeUpdate();
            }
        }
    }
}
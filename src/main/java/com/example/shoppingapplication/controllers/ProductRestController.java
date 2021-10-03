package com.example.shoppingapplication.controllers;


import com.example.shoppingapplication.model.Product;
import com.example.shoppingapplication.model.Rating;
import com.example.shoppingapplication.security.UserSecurity;
import com.example.shoppingapplication.service.ProductService;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/products")
public class ProductRestController {

    private final ProductService productService;

    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        List<Product> all = productService.getAll();
        return all;
    }

    @GetMapping("/name")
    public List<Product> getAllByName(@RequestParam("name") String name) {
        return productService.getAllByName(name);
    }

    @GetMapping("/filter")
    public List<Product> getAllPriceBetween(@RequestParam(value = "minPrice", required = false) Double minPrice,
                                            @RequestParam(value = "maxPrice", required = false) Double maxPrice) {
        return productService.getAllAndPriceBetween(minPrice, maxPrice);
    }

    @PostMapping(value = "/add",   consumes = MediaType.APPLICATION_JSON_VALUE)

   // @PreAuthorize("hasAuthority('admin:permission')")
    public void create(@RequestBody Product product) {
        productService.createProduct(product);
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('admin:permission')")
    public void delete(@RequestBody Map<String, String> map) {
        Long productId = Long.parseLong(map.get("productId"));
        productService.deleteProduct(productId);
    }


    @PostMapping("/add-comment")
    @PreAuthorize("isAuthenticated()")
    public void commentProduct(@AuthenticationPrincipal UserSecurity user,
                               @RequestBody Map<String, String> map) {
        Long productId = Long.parseLong(map.get("productId"));
        String comment = map.get("comment");

        productService.addComment(user.getUsername(), productId, comment);
    }

    @DeleteMapping("/del-comment")
    @PreAuthorize("isAuthenticated()")
    public void deleteProductComment(@AuthenticationPrincipal UserSecurity user,
                                     @RequestBody Map<String, String> map) {
        Long commentId = Long.parseLong(map.get("commentId"));
        productService.deleteComment(user.getUsername(), commentId);
    }

    @PostMapping("/rate")
    @PreAuthorize("isAuthenticated()")
    public void rateProduct(@AuthenticationPrincipal UserSecurity user,
                            @RequestBody Map<String, String> map) {
        Rating.Rate r = Rating.Rate.valueOf(map.get("rate"));
        Long productId = Long.parseLong(map.get("productId"));
        productService.getProductReting( productId, r);
    }

    @DeleteMapping("/rate")
    @PreAuthorize("isAuthenticated()")
    public void deleteProductRate(@AuthenticationPrincipal UserSecurity user,
                                  @RequestBody Map<String, String> map) {
        Long productId = Long.parseLong(map.get("productId"));
        productService.deleteRating(user.getUsername(), productId);
    }

}
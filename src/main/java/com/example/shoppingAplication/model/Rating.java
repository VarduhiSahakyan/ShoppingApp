package com.example.shoppingAplication.model;

import java.io.Serializable;
import java.util.Objects;

public class Rating implements Serializable {
    public static enum Rate {
        ONE, TWO, THREE, FOUR, FIVE
    }

    private Long ProductId;
    private Rate rate;

    public Rating() {
    }

    public Rating(Long productId, Rate rate) {
        ProductId = productId;
        this.rate = rate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rating rating = (Rating) o;
        return Objects.equals(ProductId, rating.ProductId) && rate == rating.rate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ProductId, rate);
    }
}

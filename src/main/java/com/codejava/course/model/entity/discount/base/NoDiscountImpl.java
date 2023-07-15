package com.codejava.course.model.entity.discount.base;

import com.codejava.course.model.entity.discount.DiscountStrategy;

import java.math.BigDecimal;

public class NoDiscountImpl implements DiscountStrategy {
    @Override
    public BigDecimal doDiscount(BigDecimal price) {
        return price;
    }
}

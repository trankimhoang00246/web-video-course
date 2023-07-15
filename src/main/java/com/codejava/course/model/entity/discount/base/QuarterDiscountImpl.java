package com.codejava.course.model.entity.discount.base;

import com.codejava.course.model.entity.discount.DiscountStrategy;

import java.math.BigDecimal;

public class QuarterDiscountImpl implements DiscountStrategy {
    @Override
    public BigDecimal doDiscount(BigDecimal price) {
        return price.multiply(new BigDecimal("0.25"));
    }
}

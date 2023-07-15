package com.codejava.course.model.entity.discount;

import java.math.BigDecimal;

public interface DiscountStrategy {
    BigDecimal doDiscount(BigDecimal price);
}

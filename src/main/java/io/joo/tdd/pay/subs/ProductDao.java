package io.joo.tdd.pay.subs;

import io.joo.tdd.pay.subs.Product;

public interface ProductDao {
    Product selectById(String productId);
}

package com.kk119.cq_takeout.service;

import com.kk119.cq_takeout.entity.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {
    void addShoppingCart(ShoppingCart shoppingCart);

    List<ShoppingCart> showShoppingCart();

    void clearShoppingCart();
}

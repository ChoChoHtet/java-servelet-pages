package com.cho.jsp.model.service;

import com.cho.jsp.model.entity.Product;
import com.cho.jsp.model.entity.SaleItem;
import com.cho.jsp.model.service.impl.ShoppingCartImpl;
import java.util.List;

public interface ShoppingCart {
    void add(Product product);

    int itemCount();

    void clear();

    double totalPrice();

    List<SaleItem> getAllSaleItems();

    static ShoppingCart generate() {
        return new ShoppingCartImpl();
    }
}

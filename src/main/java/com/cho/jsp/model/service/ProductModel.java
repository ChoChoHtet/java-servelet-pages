package com.cho.jsp.model.service;

import com.cho.jsp.model.entity.Product;
import java.util.ArrayList;
import java.util.List;

public class ProductModel {
    private final List<Product> productList;

    public ProductModel() {
        productList = new ArrayList<>();
    }

    public void add(Product product) {
        productList.add(product);
        product.setId(productList.size());
    }

    public Product findById(int id) {
        if (id > productList.size()) {
            return null;
        }
        return productList.get(id - 1);
    }

    public List<Product> getList() {
        return productList;
    }
}

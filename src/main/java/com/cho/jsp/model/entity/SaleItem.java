package com.cho.jsp.model.entity;

import com.cho.jsp.model.entity.Product;

public class SaleItem {
    private Product product;
    private int saleCount;

    public SaleItem(Product product) {
        this.product = product;
        this.saleCount = 0;
    }

    public void increaseCount() {
        saleCount++;
    }

    public int getUnitPrice() {
        return product.getPrice();
    }

    public double getTotalPrice() {
        return saleCount * product.getPrice();
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(int saleCount) {
        this.saleCount = saleCount;
    }
}

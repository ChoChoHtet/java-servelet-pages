package com.cho.jsp.model.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Voucher {

    private int id;
    private LocalDateTime saleTime;
    private String customer;
    private List<SaleItem> saleItems;

    public Voucher() {
        saleItems = new ArrayList<>();
    }

    public Voucher(LocalDateTime saleTime, String customer, List<SaleItem> saleItems) {
        this.saleTime = saleTime;
        this.customer = customer;
        this.saleItems = saleItems;
    }

    public int itemCount() {
        return saleItems.stream().map(SaleItem::getSaleCount)
                .reduce(0, Integer::sum);
    }

    public double totalAmount() {
        return saleItems.stream().map(SaleItem::getTotalPrice)
                .reduce(0.0, Double::sum);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getSaleTime() {
        return saleTime;
    }

    public void setSaleTime(LocalDateTime saleTime) {
        this.saleTime = saleTime;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public List<SaleItem> getSaleItems() {
        return saleItems;
    }

    public void setSaleItems(List<SaleItem> saleItems) {
        this.saleItems = saleItems;
    }
}

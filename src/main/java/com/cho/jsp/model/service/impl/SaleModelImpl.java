package com.cho.jsp.model.service.impl;

import com.cho.jsp.model.entity.SaleItem;
import com.cho.jsp.model.entity.Voucher;
import com.cho.jsp.model.service.SaleModel;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SaleModelImpl implements SaleModel {
    private final List<Voucher> vouchers;

    public SaleModelImpl() {
        vouchers = new ArrayList<>();
    }

    @Override
    public int create(String name, List<SaleItem> saleItems) {
        var voucher = new Voucher();
        voucher.setCustomer(name);
        voucher.setSaleItems(saleItems);
        voucher.setSaleTime(LocalDateTime.now());
        voucher.setId(vouchers.size() + 1);
        vouchers.add(voucher);
        return voucher.getId();
    }

    @Override
    public List<Voucher> getSaleHistory() {
        System.out.println("Vouchers size: "+vouchers.size());
        return vouchers;
    }

    @Override
    public Voucher findById(int id) {
        return vouchers.stream().filter(v -> v.getId() == id).findFirst().orElse(null);
    }
}

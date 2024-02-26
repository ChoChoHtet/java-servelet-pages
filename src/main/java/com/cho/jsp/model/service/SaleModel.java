package com.cho.jsp.model.service;

import com.cho.jsp.model.entity.SaleItem;
import com.cho.jsp.model.entity.Voucher;
import com.cho.jsp.model.service.impl.SaleModelImpl;
import java.util.List;

public interface SaleModel {
    int create(String name, List<SaleItem> saleItems);
    List<Voucher> getSaleHistory();
    Voucher findById(int id);
    static SaleModel generate(){
        return new SaleModelImpl();
    }
}

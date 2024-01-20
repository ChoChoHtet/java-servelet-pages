package com.cho.jsp.model.entity;

import java.time.LocalDateTime;
import java.util.List;

public class Voucher {

    private int id;
    private LocalDateTime saleTime;
    private String customer;
    private List<SaleItem> saleItems;


}

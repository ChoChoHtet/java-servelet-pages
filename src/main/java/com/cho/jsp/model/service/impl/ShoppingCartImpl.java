package com.cho.jsp.model.service.impl;

import com.cho.jsp.model.entity.Product;
import com.cho.jsp.model.entity.SaleItem;
import com.cho.jsp.model.service.ShoppingCart;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ShoppingCartImpl implements ShoppingCart {
    private final List<SaleItem> saleItems;

    public ShoppingCartImpl() {
        this.saleItems = new ArrayList<>();
    }


    @Override
    public void add(Product product) {
        var item = findItemById(product.getId());
        if (item == null) {
            SaleItem sale = new SaleItem(product);
            sale.increaseCount();
            saleItems.add(sale);
        } else {
            item.increaseCount();
        }


    }

    @Override
    public int itemCount() {
        return saleItems.stream()
                .map(SaleItem::getSaleCount)
                .reduce(0, Integer::sum);
    }

    @Override
    public void clear() {
        saleItems.clear();
    }

    @Override
    public double totalPrice() {
        return saleItems.stream()
                .map(SaleItem::getTotalPrice)
                .reduce(0.0, Double::sum);
    }

    @Override
    public List<SaleItem> getAllSaleItems() {
        return saleItems;
    }

    @Override
    public void updateCart(int id, boolean isAdd) {
        var item = findItemById(id);
        if (Objects.nonNull(item)) {
            if (isAdd) {
                item.increaseCount();
            } else {
                System.out.println("Sale Count: "+ item.getSaleCount());
                if (item.getSaleCount() == 1) {
                    saleItems.remove(item);
                } else item.decreaseCount();
            }

        }
    }

    public SaleItem findItemById(int productId) {
        Optional<SaleItem> item = saleItems.stream().filter(x -> x.getProduct().getId() == productId).findFirst();
        return item.orElse(null);

    }
}

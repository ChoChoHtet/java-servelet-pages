package com.cho.jsp.listener;

import com.cho.jsp.model.service.SaleModel;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class SaleLoader implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        SaleModel saleModel = SaleModel.generate();
        sce.getServletContext().setAttribute("sale.model", saleModel);
    }
}

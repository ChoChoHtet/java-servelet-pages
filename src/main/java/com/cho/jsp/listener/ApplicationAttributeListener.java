package com.cho.jsp.listener;

import com.cho.jsp.model.ProductModel;
import jakarta.servlet.ServletContextAttributeEvent;
import jakarta.servlet.ServletContextAttributeListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class ApplicationAttributeListener implements ServletContextAttributeListener {
    @Override
    public void attributeAdded(ServletContextAttributeEvent event) {
       var value = event.getValue();
       if (value instanceof ProductModel){
           event.getServletContext().log(String.format("Attribute Name is %s",event.getName()));
           event.getServletContext().log(String.format("Product size is %s",((ProductModel) value).getList().size()));
       }
    }
}

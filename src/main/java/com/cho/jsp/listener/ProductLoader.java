package com.cho.jsp.listener;


import com.cho.jsp.model.entity.Product;
import com.cho.jsp.model.ProductModel;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

//Load Product in Application Scope and listening
//Tomcat will register as listener class and will invoke when lifecycle changes
@WebListener
public class ProductLoader implements ServletContextListener {
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Context Initialized");
        var productModel = new ProductModel();
        String filePath = sce.getServletContext().getRealPath("/WEB-INF/product.txt");
        try (BufferedReader input = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = input.readLine()) != null) {
                var content = line.split(",");
                productModel.add(new Product(content[0], content[1], Integer.parseInt(content[2])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //add product model to application scope
        sce.getServletContext().setAttribute("products", productModel);

    }

    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Context Destroyed");
    }
}

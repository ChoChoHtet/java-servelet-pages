package com.cho.jsp.controller;

import com.cho.jsp.model.entity.Product;
import com.cho.jsp.model.service.ProductModel;
import com.cho.jsp.model.service.ShoppingCart;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({"/cart-add",
        "/cart-show",
        "/cart-clear",
        "/cart-plus",
        "/cart-minus",
})
public class ShoppingCartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getServletPath()) {
            case "/cart-add" -> addToCart(req, resp);
            case "/cart-show" -> showCarts(req, resp);
            case "/cart-plus", "/cart-minus" -> updateCart(req, resp);
            default -> clearCart(req, resp);
        }
    }

    private void updateCart(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        var id = req.getParameter("productId");

        var session = req.getSession();
        var cart = (ShoppingCart) session.getAttribute("cart");
        if (cart != null) {
            var isAdd = "/cart-plus".equals(req.getServletPath());
            cart.updateCart(Integer.parseInt(id), isAdd);
            resp.sendRedirect(req.getContextPath().concat("/my-cart.jsp"));
        }
    }

    private void clearCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var session = req.getSession(true);

        if (session != null) {
            var cart = (ShoppingCart) session.getAttribute("cart");
            cart.clear();
        }
        getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    private void showCarts(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/my-cart.jsp").forward(req, resp);
    }

    private void addToCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var id = req.getParameter("productId");

        ProductModel model = (ProductModel) req.getServletContext().getAttribute("products");

        Product product = model.findById(Integer.parseInt(id));

        // get shopping cart from session scope and if not present , create it
        var session = req.getSession(true);
        var cart = (ShoppingCart) session.getAttribute("cart");

        if (null == cart) {
            cart = ShoppingCart.generate();
            session.setAttribute("cart", cart);
        }
        cart.add(product);


        //forward to index.jsp
        getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}

package com.cho.jsp.controller;

import com.cho.jsp.model.entity.Voucher;
import com.cho.jsp.model.service.SaleModel;
import com.cho.jsp.model.service.ShoppingCart;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

@WebServlet({"/sale-history", "/sale-detail", "/checkout"})
public class SaleServlet extends HttpServlet {

    private static Logger LOG = Logger.getLogger("SaleServlet");
    private SaleModel saleModel;

    @Override
    public void init() {
        var application = getServletContext();
        saleModel = (SaleModel) application.getAttribute("sale.model");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //get customer name
        var customerName = req.getParameter("customer_name");
        //get sale item from shopping cart
        var cart = (ShoppingCart) req.getSession().getAttribute("cart");
        var sales = cart.getAllSaleItems();
        //create voucher
        var voucherId = saleModel.create(customerName, sales);
        //reset shopping card
        req.getSession().removeAttribute("cart");
        //redirect to sale details to prevent resubmit/duplication
        resp.sendRedirect(getServletContext().getContextPath().concat("/sale-detail?voucherId=") + voucherId);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.info("Hello Testing" + req.getServletPath());
        if (req.getServletPath().equals("/sale-history")) {
            showSaleHistory(req, resp);
        } else {
            showSaleDetail(req, resp);
        }
    }

    private void showSaleDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var voucherId = req.getParameter("voucherId");
        Voucher voucher = saleModel.findById(Integer.parseInt(voucherId));

        //add voucher to request scope
        req.setAttribute("voucher", voucher);
        //forward to sale-details.jsp
        getServletContext().getRequestDispatcher("/sale-detail.jsp").forward(req, resp);
    }

    private void showSaleHistory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Voucher> vouchers = saleModel.getSaleHistory();
        //add sale to servlet request scope
        req.setAttribute("vouchers.list", vouchers);
        //forward to sale-history.jsp
        getServletContext().getRequestDispatcher("/sale-history.jsp").forward(req, resp);
    }
}

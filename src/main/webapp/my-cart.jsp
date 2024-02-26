<%@ page import="com.cho.jsp.model.service.ShoppingCart" %>
<%@ page import="com.cho.jsp.model.entity.SaleItem" %>
<%@ page import="com.cho.jsp.model.entity.Product" %><%--
  Created by IntelliJ IDEA.
  User: chochohtet
  Date: 20/1/2024 AD
  Time: 5:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My Cart</title>
    <style>
       th {
           font-size: 18px;
           text-align: left;
       }
        table {
            width: 60%;
        }

        .footer td {
            border: 0 solid black;
            margin-top: 10px;
        }

        .link-with-margin {
            display: inline-block;
            margin: 0 10px;
            font-size: 18px;
            font-weight: bold;
        }
    </style>
</head>
<body>
<%
    ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("cart");
%>
<h2>My Cart</h2>
<hr />
<a href="index.jsp">Home</a>
<hr />
<%
    if (shoppingCart == null) {
%>
<h3> No Purchase Item at the moment. Thank you.</h3>
<%
} else {
%>
<table>
    <tr>
        <th>ID</th>
        <th>Category</th>
        <th>Name</th>
        <th>Price</th>
        <th>Item Count</th>
        <th>Total</th>
    </tr>
    <%
        for (SaleItem item : shoppingCart.getAllSaleItems()) {
            Product product = item.getProduct();
    %>
    <tr>
        <td><%=product.getId()%>
        </td>
        <td><%=product.getCategory()%>
        </td>
        <td><%=product.getName()%>
        </td>
        <td><%=product.getPrice()%>
        </td>
        <td >
            <a href="cart-plus?productId=<%=product.getId()%>" class="link-with-margin"> + </a>
            <%=item.getSaleCount()%>
            <a href="cart-minus?productId=<%=product.getId()%>" class="link-with-margin">-</a>
        </td>
        <td><%=item.getTotalPrice()%>
        </td>
    </tr>

    <%
        }
    %>
    <tr class="footer">
        <td colspan="5">Total</td>
        <td colspan="5"><%=shoppingCart.totalPrice()%>
        </td>
    </tr>
</table>
<%
    }
%>
<hr />
<h4>Checkout</h4>
<hr />
<form action="checkout" method="post">
    <label>
        <input type="text" placeholder="Enter Customer name" name="customer_name">
    </label>
    <button type="submit" >Checkout</button>
</form>
</body>
</html>

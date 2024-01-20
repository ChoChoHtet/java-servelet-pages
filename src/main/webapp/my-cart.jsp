<%@ page import="com.cho.jsp.service.ShoppingCart" %>
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
        table, tr, td, th {
            border: 1px solid black;
            border-collapse: collapse;
            padding: 16px 8px 16px 8px;
        }

        th {
            font-size: 18px;
            text-align: left;
            background-color: #61d8e3;
            color: #f2f2f2;
        }

        table {
            width: 60%;
        }

        .footer td {
            border: 0 solid black;
        }
    </style>
</head>
<body>
<%
    ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("cart");
%>
<h2>My Cart</h2>
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
        <th>Count</th>
        <th>Price</th>
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
        <td><%=item.getSaleCount()%>
        </td>
        <td><%=item.getTotalPrice()%>
        </td>
    </tr>

    <%
        }
    %>
    <tr class="footer">
        <td colspan="4">Total</td>
        <td colspan="5"><%=shoppingCart.totalPrice()%>
        </td>
    </tr>
</table>
<%
    }
%>
<p>
    <a href="index.jsp">Back to Cart</a>
</p>
</body>
</html>

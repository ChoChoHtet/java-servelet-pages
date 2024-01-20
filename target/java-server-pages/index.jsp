<%@ page import="com.cho.jsp.model.ProductModel" %>
<%@ page import="com.cho.jsp.model.entity.Product" %>
<%@ page import="com.cho.jsp.service.ShoppingCart" %>
<html>
<head>
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
    </style>
    <title>
        Cho Marketplace
    </title>

</head>
<body>
<h2>Cho Marketplace</h2>
<p>
    <% ShoppingCart cart = (ShoppingCart) session.getAttribute("cart"); %>
    <a href="cart-show">Show Cart</a> <br>
    Count: <%= cart == null ? 0 : cart.itemCount()%><br>
    Total Amount: <%= cart == null ? 0.0 : cart.totalPrice()%><br>
</p>
<p>
    <%
        if (cart != null && cart.itemCount() > 0) {
    %>
    <a href="cart-clear">Clear My Cart</a>
    <%
        }
    %>

</p>

<table>
    <%
        ProductModel productModel = (ProductModel) application.getAttribute("products");
    %>
    <tr>
        <th>ID</th>
        <th>Category</th>
        <th>Name</th>
        <th>Price</th>
        <th>Action</th>
    </tr>
    <%
        for (Product product : productModel.getList()) {
    %>
    <tr>
        <td><%= product.getId()%>
        </td>
        <td><%= product.getCategory()%>
        </td>
        <td><%=product.getName()%>
        </td>
        <td><%=product.getPrice()%>
        </td>
        <td><a href="cart-add?productId=<%=product.getId()%>"> Add to Cart </a></td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>

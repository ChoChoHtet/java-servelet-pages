<%@ page import="com.cho.jsp.model.entity.Voucher" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="com.cho.jsp.model.entity.SaleItem" %>
<%@ page import="com.cho.jsp.model.entity.Product" %><%--
  Created by IntelliJ IDEA.
  User: chochohtet
  Date: 11/2/2024 AD
  Time: 11:22 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sale Summary</title>
</head>
<body>
<h1>Sale Summary</h1>
<%!
    String formatDateTime(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
%>
<% Voucher v = (Voucher) request.getAttribute("voucher");%>
<table style="width: 60%">
    <tr>
        <td>Voucher ID</td>
        <td><%=v.getId()%>
        </td>
    </tr>
    <tr>
        <td>Customer Name</td>
        <td><%=v.getCustomer()%>
        </td>
    </tr>
    <tr>
        <td>Sale Date</td>
        <td><%=formatDateTime(v.getSaleTime())%>
        </td>
    </tr>
</table>
<h3>Sale Items</h3>
<table style="width: 60%">
    <thead>
    <tr style="margin-bottom: 16px">
        <td>Product Name</td>
        <td>Category</td>
        <td>Unit Price</td>
        <td>Qty</td>
        <td>Total</td>
    </tr>
    </thead>
    <tbody>
    <% for (SaleItem sale : v.getSaleItems()) {
        Product product = sale.getProduct();
    %>
    <tr>
        <td><%=product.getName()%>
        </td>
        <td><%=product.getCategory()%>
        </td>
        <td><%=product.getPrice()%>
        </td>
        <td><%=sale.getSaleCount()%>
        </td>
        <td><%=sale.getTotalPrice()%>
        </td>
    </tr>
    <% } %>
    </tbody>
</table>

<hr/>
<a href="index.jsp">Home</a>

</body>
</html>

<%@ page import="java.util.List" %>
<%@ page import="com.cho.jsp.model.entity.Voucher" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.util.Objects" %><%--
  Created by IntelliJ IDEA.
  User: chochohtet
  Date: 11/2/2024 AD
  Time: 11:22 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Sales History</title>
</head>
<body>
<%!
    String formatDateTime(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
%>
<%
    List<Voucher> voucherList = (List<Voucher>) request.getAttribute("vouchers.list");
%>
<h1>Sales History</h1>
<% if (voucherList == null) {%>
<h4>No Data.</h4>
<%} else {%>
<table style="width: 80%">
    <thead>
    <tr>
        <td>Voucher ID</td>
        <td>Date</td>
        <td>Customer Name</td>
        <td>Item Count</td>
        <td>Sale Total</td>
    </tr>
    </thead>
    <tbody>
    <% for (Voucher voucher : voucherList) {%>
    <tr>
        <td>
            <a href="sale-detail?voucherId=<%=voucher.getId()%>"><%=voucher.getId()%>
            </a>
        </td>
        <td>
            <%=formatDateTime(voucher.getSaleTime())%>
        </td>
        <td>
            <%=voucher.getCustomer()%>
        </td>
        <td>
            <%=voucher.itemCount()%>
        </td>
        <td>
            <%=voucher.totalAmount()%>
        </td>
    </tr>
    <%} %>

    </tbody>
</table>
<% } %>

</body>
</html>

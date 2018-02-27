<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 26.02.2018
  Time: 0:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url var="url" value="css/global.css" />
<link type="text/css" rel="stylesheet" href="${url}" />

<html>
<head>
    <title>Items</title>
</head>
<body>
<%@include file="includes/header.jsp" %>
<table class="features-table">
    <tr>
        <td>N</td>
        <td class="grey">Name</td>
        <td class="grey">Price</td>
        <td class="green">Status</td>
    </tr>
 <%--   <tfoot>
    <tr>
        <td></td>
        <td class="grey" colspan="4">All items</td>
    </tr>
    </tfoot>--%>
    <c:forEach var="elem" items="${item}" varStatus="status">
        <tr>
            <td><c:out value="${ status.count }" /></td>
            <%--<td><c:out value="${ elem }" /></td>--%>
            <td class="grey"><c:out value="${ elem.name }" /></td>
            <td class="grey"><c:out value="${ elem.price }" /></td>
            <td class="green"><c:out value="${ elem.status }" /></td>
        </tr>
    </c:forEach>

</table>
</body>
</html>

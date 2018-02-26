<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 26.02.2018
  Time: 0:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
Items<br>
<table>
    <tr>
        <td>N</td>
        <td>Name</td>
        <td>Price</td>
        <td>Status</td>
        <td>Description</td>
    </tr>
    <c:forEach var="elem" items="${item}" varStatus="status">
        <tr>
            <td><c:out value="${ status.count }" /></td>
            <%--<td><c:out value="${ elem }" /></td>--%>
            <td><c:out value="${ elem.name }" /></td>
            <td><c:out value="${ elem.price }" /></td>
            <td><c:out value="${ elem.status }" /></td>
            <td><c:out value="${ elem.description }" /></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>

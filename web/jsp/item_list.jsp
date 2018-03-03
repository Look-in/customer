<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 26.02.2018
  Time: 0:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="css/global.css" rel="stylesheet" type="text/css">
<link href="css/item.css" rel="stylesheet" type="text/css">
<html>
<head>
    <title>${param.entity}</title>
</head>
<body>
<%@include file="includes/header.jsp" %>
<div class="add-item">
    <c:url var="addurl" value="/pushitemmodify">
        <c:param name="action" value="add"/>
    </c:url>
    <a href="${addurl}">Add item</a>
    <div>
<c:forEach var="elem" items="${item}" varStatus="status">
<div class="img-responsive">
  <%-- <img class="item-image" src="${elem.base64imageFile}">--%>
    <img class="item-image" src="css/images/no-img.png">
    <span class="range-txt position-rage-bottom">
        <c:out value="${ elem.itemStatus }" /><br>
        <c:out value="${ elem.name }" /><br>
        <strong><c:out value="${ elem.price }" />$</strong>
  </span>
      <c:url var="editurl" value="/pushitemmodify">
          <c:param name="action" value="edit"/>
          <c:param name="id" value="${elem.itemId}"/>
      </c:url>
       <a class="item edit" href="${editurl}">Edit</a>
      <c:url var="deleteurl" value="/pushitemmodify">
          <c:param name="action" value="delete"/>
          <c:param name="id" value="${elem.itemId}"/>
      </c:url>
    <a class="item delete" href="${deleteurl}" title="">Delete</a>
</div>
</c:forEach>
</body>
</html>

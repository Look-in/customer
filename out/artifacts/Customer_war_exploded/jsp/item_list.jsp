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
    <title>${param.type}</title>
</head>
<body>
<%@include file="includes/header.jsp" %>
<div>
    <c:url var="selectitem" value="selectitemservlet">
        <c:param name="action" value="list"/>
        <c:param name="type" value="ALL"/>
    </c:url>
    <a href="${selectitem}" title="">ALL</a>
<c:forEach var="typeitem" items="${typeitem}">
    <c:url var="selectitem" value="selectitemservlet">
        <c:param name="action" value="list"/>
        <c:param name="type" value="${typeitem.type}"/>
        <c:param name="typeId" value="${typeitem.id}"/>
    </c:url>
<a href="${selectitem}" title="">${typeitem.type}</a>
</c:forEach>
</div>
<div class="add-item">
    <c:url var="addurl" value="/viewitemmodify">
        <c:param name="action" value="ADD"/>
        <c:param name="type" value="${ param.type }"/>
        <c:param name="typeId" value="${ param.typeId }"/>
    </c:url>
    <a ${param.type == "ALL" ? 'hidden="true"' : ''} href="${addurl}">Add item</a>
    </div>
<c:forEach var="elem" items="${item}" varStatus="status">
<div class="img-responsive">
  <%-- <img class="item-image" src="${elem.base64imageFile}">--%>
    <img class="item-image" src="css/images/no-img.png">
    <span class="range-txt position-rage-bottom">
        <c:out value="${ elem.itemStatus }" /><br>
        <c:out value="${ elem.name }" /><br>
        <strong><c:out value="${ elem.price }" />$</strong>
  </span>
      <c:url var="editurl" value="/viewitemmodify">
          <c:param name="action" value="EDIT"/>
          <c:param name="itemId" value="${elem.itemId}"/>
          <c:param name="type" value="${ param.type }"/>
          <c:param name="typeId" value="${ param.typeId }"/>
      </c:url>
      <a class="item edit" ${param.type == "ALL" ? 'hidden="true"' : ''} href="${editurl}">Edit</a>
      <c:url var="deleteurl" value="/viewitemmodify">
          <c:param name="action" value="DELETE"/>
          <c:param name="itemId" value="${elem.itemId}"/>
      </c:url>
    <a class="item delete" href="${deleteurl}" title="">Delete</a>
</div>
</c:forEach>
</body>
</html>

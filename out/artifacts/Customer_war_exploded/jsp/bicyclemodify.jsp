<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: shankunassv
  Date: 28.02.2018
  Time: 22:01
  Страница ввода/изменения Clothes.
--%>
<link href="css/global.css" rel="stylesheet" type="text/css">
<link href="css/item.css" rel="stylesheet" type="text/css">

<html>
<head>
    <title>${param.entity} ${param.action}</title>
</head>
<%@include file="includes/header.jsp" %>
<%@include file="includes/defaultitemmodify.jsp" %>
<div>
    <label for="ufork">Fork: </label>
    <input type="text" id="ufork" name="fork"
       size="30" value="${ item.fork }">
</div>
<div>
    <label for="ubrakes">Brakes: </label>
 <input type="text" id="ubrakes" name="brakes"
       size="30" value="${ item.brakes }">
</div>
<div>
    <label for="uframe">Frame: </label>
 <input type="text" id="uframe" name="frame"
       size="30" value="${ item.frame }">
</div>
    <input type="submit" name="button" value="${param.action}"/>
    <a href="selectitemservlet" title="">Cancel</a>
</form>
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: shankunassv
  Date: 12.03.2018
  Time: 9:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
    <spring:url value="/resourses/chunck.css" var="chunck" />
    <link href="${chunck}" rel="stylesheet" />
    <title>Spring MVC Tutorial by Crunchify - Hello World Spring MVC
        Example</title>
</head>
<body>${message}

<br>
<br>
<div class="add-item">
    Spring MCV Tutorial by <a href="https://crunchify.com">Crunchify</a>.
    Click <a
        href="https://crunchify.com/category/java-tutorials/"
        target="_blank">here</a> for all Java and <a
        href='https://crunchify.com/category/spring-mvc/' target='_blank'>here</a>
    for all Spring MVC, Web Development examples.<br>

    <h2>Checkout this font color. Loaded from 'crunchify.css' file under '/resources/' folder</h2>

    <div id="crunchifyMessage"></div>
</div>
</body>
</html>
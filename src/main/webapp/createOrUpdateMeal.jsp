<%--
  Created by IntelliJ IDEA.
  User: Johann Stolz
  Date: 02.07.2018
  Time: 14:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="meal" type="ru.javawebinar.topjava.model.Meal" scope="request"/>
<html>
<head>
    <title>CreateOrUpdateMeal</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>Meals</h2>

<form method="post" action="meals?action=add/update">
    <dl>
        <dt>ID: </dt>
        <dd><input type="number" name="id" value="${meal.id}" placeholder="${meal.id}" /></dd>
    </dl>
    <dl>
        <dt>Date </dt>      
        <dd><input type="datetime-local" name="dateTime" pattern="(\d{4})-(\d{2})-(\d{2})(T)(\d{2}):(\d{2})" value="${meal.dateTime}" placeholder="${meal.dateTime}" /></dd>
    </dl>
    <dl>
        <dt>Description </dt>
        <dd><input type="text" name="description" value="${meal.description}" placeholder="${meal.description}" /></dd>
    </dl>
    <dl>
        <dt>Calories </dt>      
        <dd><input type="number" name="calories" value="${meal.calories}" placeholder="${meal.calories}" /></dd>
    </dl>
    <button type="submit">Save</button>
</form>

</body>
</html>

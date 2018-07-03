<%--
  Created by IntelliJ IDEA.
  User: Johann Stolz
  Date: 02.07.2018
  Time: 9:16
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<html>
<head>
    <title>Meals</title>
</head>
<body>

<h3><a href="index.html">Home</a></h3>
<h2>Meals</h2>
<table border="2">
    <jsp:useBean id="MealWithExceedList" type="java.util.List<ru.javawebinar.topjava.model.MealWithExceed>"
                 scope="request"/>
    <thead>
    <tr bgcolor="#F0E68C">
        <th><strong>Date</strong></th>
        <th><strong>Description</strong></th>
        <th><strong>Calories</strong></th>
        <th><strong>Exceed</strong></th>
        <th colspan=2>Action</th>
    </tr>
    </thead>
    <c:forEach items="${MealWithExceedList}" var="mealWithExceed">
        <c:set var="trColor" scope="page">#ADFF2F</c:set>

        <c:if test="${mealWithExceed.exceed}">
            <c:set var="trColor" scope="page">#F08080</c:set>
        </c:if>
        <tr bgcolor=${trColor}>

            <fmt:parseDate value="${mealWithExceed.dateTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDate"
                           type="date"/>
            <fmt:formatDate value="${parsedDate}" type="date" var="correctDate" pattern="dd.MM.yyyy HH:mm"/>

            <td><c:out value="${correctDate}"/></td>
            <td><c:out value="${mealWithExceed.description}"/></td>
            <td><c:out value="${mealWithExceed.calories}"/></td>
            <td><c:out value="${mealWithExceed.exceed}"/></td>
            <td>
                <form action="meals?action=update&id=${mealWithExceed.id}" target="_self" method="post">
                    <p><input type="submit" value="Update"></p>
                </form>
            </td>
            <td>
                <form action="meals?action=delete&id=${mealWithExceed.id}" target="_self" method="post">
                    <p><input type="submit" value="Delete"></p>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
<form action="meals?action=add" target="_self" method="post">
    <p><input type="submit" value="Create new"></p>

</form>

</body>
</html>

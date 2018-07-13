<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>Users</h2>
</body>
Login form
<form action="users" method="post" title="login">
    <input type="hidden" name="action" value="login"/>

    <div>
       enter your e-mail:
        <input type="email" name="email"/>
    </div>
    <div>
       enter your password:
        <input type="password" name="password"/>
    </div>

    <div>
        <input type="submit" value="Sing in"/>
    </div>


</form>
Registration form
<form action="users" method="post" title="registration">
    <input type="hidden" name="action" value="register">

    <div>
       enter your name:
        <input type="text" name="userName"/>
    </div>

    <div>
        enter your e-mail:
        <input type="email" name="email"/>
    </div>

    <div>
        enter your  password:
        <input type="password" name="password"/>
    </div>

    <div>
        enter your  callories per day:
        <input type="number" name="calories"/>
    </div>

    <div>
        <input type="submit" value="Register"/>
    </div>

</form>

</html>
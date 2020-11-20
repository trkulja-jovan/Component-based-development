<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
	<c:if test="${success}">
		Successfully registration.
	</c:if>
	<c:if test="${successLogin == false}">
		Error during login.
	</c:if>
	<form action="/OglasWeb/RegistrationServlet" method="get">
		Insert username: <input type="text" name="username"> <br><br>
		Insert password: <input type="text" name="password"> <br><br>
		<input type="submit" value="Login">
	</form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration</title>
</head>
<body>
	
	
	
	<form action="/OglasWeb/RegistrationServlet" method="post">
		Insert nickname: <input type="text" name="nickname"> <br><br>
		Insert username: <input type="text" name="username"> <br><br>
		Insert password: <input type="text" name="password"> <br><br>
		
		<input type="submit" value="Register">
	</form>
	
	<a href="login.jsp">Already have account? Login</a>
</body>
</html>
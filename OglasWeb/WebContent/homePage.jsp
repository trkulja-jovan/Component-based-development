<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
       <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home page</title>
</head>
<body>
	<c:if test="${successLogin}">
		Successfully login.
	</c:if>
	
	<form action="/OglasWeb/SearchAdvertisementServlet" method="get">
	
		Insert keyword <input type="text" name="keyword"> <br><br>
		<input type="submit" value="Search">
	</form>
	
	<c:if test="${empty list}">
		Cannot find results
	</c:if>
	
	<c:if test="${!empty list}">
		
		<table border="1">
			<tr>
				<td>Advertisement name</td>
				<td>Number of views</td>
				<td>Creator</td>
				<td>Respond to the ad</td>
			</tr>
			
			<c:forEach var="adv" items="${list}">
				<tr>
					<td>${adv.text}</td>
					<td>${adv.brojPregleda}</td>
					<td>${adv.oglaskorisnik.nickname}</td>
					<td><a href="/OglasWeb/RespondToAddServlet?idAdvertisement=${adv.idOglas}">Respond to the add</a>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	
	<c:if test="${successRespond}">
		<script>
			alert("Successfully respond to add");
		</script>
		<c:remove var="successRespond"></c:remove> 
	</c:if>
	<c:if test="${successRespond == false}">
		<script>
			alert("Error");
		</script>
		<c:remove var="successRespond"></c:remove> 
	</c:if>
	
	<br><br><br><br>
	
	Insert new advertisement <br>
	<form action="/OglasWeb/SearchAdvertisementServlet" method="post">
		
		Insert description <input type="text" name="text"> <br><br>
		
		<input type="submit" value="Add advertisement">
	</form>
	
	<c:if test="${successAdd}">
		<script>
			alert("Successfully added!");
		</script>
		<c:remove var="successAdd"></c:remove> 
	</c:if>
	
	<c:if test="${successAdd == false}">
		<script>
			alert("Error");
		</script>
		<c:remove var="successAdd"></c:remove> 
	</c:if>
</body>
</html>
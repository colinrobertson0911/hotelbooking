<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Owner Hotels</title>
</head>
<body>
	<h2>All Owner Hotels</h2>
	<p>
		<a href="AddHotel">Add a new hotel</a>
	</p>
	<div>
		<c:forEach items="${hotels}" var="hotel">
		<div>
		<a href="EditHotel?hotelId=${hotel.hotelId}">${hotel.hotelName}</a>
		</div>
		<div>${hotel.hotelName}</div>
		<div>${hotel.numOfRooms}</div>
		<div>${hotel.city}</div>
		<div>${hotel.starRating}</div>
	</c:forEach>
	</div>
</body>
</html>
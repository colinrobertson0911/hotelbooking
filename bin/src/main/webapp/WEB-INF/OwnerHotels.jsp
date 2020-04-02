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
	<h2>${hotelOwner.name} Hotels</h2>
	<p>
		<a href="AddHotel?hotelOwnerId=${hotelOwner.hotelOwnerId}">Add a new hotel</a>
	</p> 
	<div>
		<c:forEach items="${hotelOwner.hotel}" var="hotel">
			<div>
				<a href="EditHotel?hotelId=${hotel.hotelId}&hotelOwnerId=${hotelOwner.hotelOwnerId}">${hotel.hotelName}</a>
			</div>
			<div>
			<p>Hotel Name: ${hotel.hotelName} </p>
			</div>
			<div>
			<p>Number of rooms: ${hotel.numOfRooms}
			</div>
			<div>
			<p>City: ${hotel.city}</p>
			</div>
			<div>
			<p>Amenities: ${hotel.ammenities}
			</div>			
			<div>
			<p>Star Rating: ${hotel.starRating}</p>
			</div>
			<div>
			<p>--------------------------------------------</p>
			</div>
		</c:forEach>
	</div>
</body>
</html>
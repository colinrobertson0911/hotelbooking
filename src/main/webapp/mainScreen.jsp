<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Main</title>
</head>
<body>
	<h2>Main Page</h2>
	<div>
	<p>${ownerMessage}</p>
	</div>
	<div>
		<a href="LoginAsOwner">Log In as a Hotel Owner</a>
	</div>

	<div>
		<a href="LoginAsAdmin">Log in as an administrator</a>
	</div>

	<form action="SearchByCity" method="post">
		${errorMessage}
		<div>
			<label>Search by city:</label> <input type="city" name="city" />
		</div>
		<button type="submit">Search</button>
	</form>
	
	<form action="SearchByRoomType" method="post">
		${errorRoomTypeMessage}
		<div>
			<label>Search by Room Type: </label> <input type="room" name="roomType" />
		</div>
		<button type="submit">Search</button>	
	</form>


	<c:forEach items="${hotel}" var="hotel">
		<h3>
			<a href="SeeHotel?hotelId=${hotel.hotelId}">${hotel.hotelName}</a>
		</h3>
		<div>
			<p>Number of rooms: ${hotel.numOfRooms}</p>
			<p>City: ${hotel.city}</p>
			<p>Star Rating: ${hotel.starRating}/5</p>
		</div>
		<div>
			<p>-------------------------------------------</p>
		</div>
	</c:forEach>

</body>
</html>
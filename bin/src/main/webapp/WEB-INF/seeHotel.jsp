<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>${hotel.hotelName}</title>
</head>
<body>

<a href="${pageContext.request.contextPath}/Home">Back</a>

<c:forEach items="${hotel}" var="hotel">
		<h3><a href="SeeHotel?hotelId=${hotel.hotelId}">${hotel.hotelName}</a></h3>
		<div>
			<p>Number of rooms: ${hotel.numOfRooms}</p>
			<p>Address:${hotel.address}</p>
			<p>Postcode:${hotel.postcode}</p>
			<p>City: ${hotel.city}</p>
			<p>Ammenities: ${hotel.ammenities}
			<p>Star Rating: ${hotel.starRating}/5</p>
			
		</div>
		<div>
			<p>-------------------------------------------</p>
		</div>
	</c:forEach>

</body>
</html>
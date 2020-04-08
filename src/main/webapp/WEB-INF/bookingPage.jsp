<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Booking Page</title>
</head>
<body>
	<h2>Make a booking</h2>
	<div>
		<f:form method="post" action="BookingSubmit" modelAttribute="bookings">
			<p>Please enter your booking details</p>
			<div>
				<h3><f:label path="hotel">${hotel.hotelName}</f:label></h3>
				<h3><f:label path="roomType">${room.roomType} - </f:label>
				<f:label path="roomPrice">£${room.price} per night</f:label></h3>
			</div>
			
			<div>
				<label>Length of stay</label> <input type="date" name="checkInDate" />
				to <input type="date" name="checkOutDate" />
			</div>
			
			<div>
				
			</div>
			
			<div>
				<button type="Submit">Create booking</button>
			</div>
		</f:form>
	</div>

</body>
</html>
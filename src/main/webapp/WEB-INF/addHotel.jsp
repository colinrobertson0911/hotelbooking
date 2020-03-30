<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Hotel</title>
</head>
<body>
	<h2>Add your hotel</h2>
	<f:form method="post" action="AddHotelSubmit" modelAttribute="hotel">
		<div>
			<f:label path="hotelName">Hotel Name:</f:label>
			<f:input path="hotelName" type="text" required="required" />
		</div>
		<div>
			<f:label path="numOfRooms">Number of Rooms</f:label>
			<f:input path="numOfRooms" type="text" required="required" />
		</div>
		<div>
			<f:label path="city">City</f:label>
			<f:input path="city" type="text" required="required" />
		</div>
		<div>
			<f:label path="starRating">Star Rating</f:label>
			<f:input path="starRating" type="text" required="required" />
		</div>

		<button type="submit">Add Hotel</button>
	</f:form>
</body>
</html>
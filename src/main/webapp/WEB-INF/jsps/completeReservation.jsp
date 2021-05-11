<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@page isELIgnored="false"%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Completar Reservacion</title>
</head>
<body>
  <h2>Complete la Reservacion</h2>
  Airline: ${flight.operatingAirlines} <br/>
  Departure City: ${flight.departureCity} <br/>
  Arrival City: ${flight.arrivalCity} <br/>
  
  <form action="completeReservation" method="post">
   <input type="hidden" name="flightId" value="${flight.id}">
  <pre>
    <h2>Passenger Details</h2>
    First Name: <input type="text" name="passengerFirstName">
    Last Name: <input type="text" name="passengerLastName">
    Email: <input type="text" name="passengerEmail">  
    Phone: <input type="text" name="passengerPhone">
    <h2>Credit Card Details</h2>
    Name on the Card: <input type="text" name="nameOnTheCard">
    Card number: <input type="text" name="cardNumber">
    Expiry Date: <input type="text" name="expirationDate">
    Three Digit Sec. Code: <input type="text" name="secCode">
    
    <input type="submit" value="Confirm">
   </pre> 
  </form> 
</body>
</html>
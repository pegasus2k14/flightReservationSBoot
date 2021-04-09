<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registro de usuarios</title>
</head>
<body>
  <!-- Formulario de registro de usuario. -->
  <form action="registerUser" method="post">
   <pre>
     <h2>Registro de usuario:</h2>
     Primer nombre: <input type="text" name="firstName"/>
     Apellido: <input type="text" name="lastName"/>
     User name: <input type="text" name="email"/>
     Password: <input type="password" name="password"/>
     Confirm Password: <input type="password" name="confirmPassword"/>
     <input type="submit" value="Registrar">
    </pre> 
  </form>
</body>
</html>
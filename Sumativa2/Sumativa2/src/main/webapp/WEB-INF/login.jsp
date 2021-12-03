<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	Inicia sesión
	<form action="/usuario/login" method="POST">
		<label for="username">Correo: </label>
		<input type="text" name="username">
		<br>
		<label for="password">Contrasena: </label>
		<input type="text" name="password">
		<br>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
		<input type="submit" value="Ingresar">
	</form>
	<br>
	<a href="/usuario/registrar" >Crear cuenta</a>
</body>
</html>
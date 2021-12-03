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
	<H3>Registrar cuenta de Usuario</H3>
	<form:form action="/usuario/crear" method="POST" modelAttribute="usuario">
		<form:label path="nombre">Nombre: </form:label>
		<form:input path="nombre" type="text"/>
		<br>
		<form:label path="apellido">Apellido: </form:label>
		<form:input path="apellido" type="text"/>
		<br>
		<form:label path="correo">Correo: </form:label>
		<form:input path="correo" type="email"/>
		<br>
		<form:label path="contrasena">Contraseña: </form:label>
		<form:input path="contrasena" type="password"/>
		<br>
		<input type="button" value="Cancelar" onClick=window.history.back()>
		<input type="submit" value="Registrar">
	</form:form>
</body>
</html>
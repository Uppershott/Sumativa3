<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form:form action="/categoria/editarCategoria" method="POST" modelAttribute="categoria">
		<form:input type="hidden" path="id"/>
		<input type="hidden" name="_method" value="put"/>
		
		<form:label path="nombre">Nombre: </form:label>
		<form:input path="nombre" type="text"/>
		<br>
		<form:label path="descripcion">Descripcion: </form:label>
		<form:input path="descripcion" type="text"/>
		<br>
		<input type="button" onClick="window.history.back()" value="Cancelar">
		<input type="submit" value="Guardar">
	</form:form>
</body>
</html>
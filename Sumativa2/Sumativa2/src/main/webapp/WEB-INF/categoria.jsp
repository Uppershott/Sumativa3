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
	<H3>Categorías</H3>
	<form:form action="/categoria/agregar" method="POST" modelAttribute="categoria">
		<form:label path="nombre">Nombre: </form:label>
		<form:input path="nombre"/>
		<br>
		<form:label path="descripcion">Descripcion: </form:label>
		<form:input path="descripcion" type="text"/>
		<br>
		<input type="submit" value="Guardar">
	</form:form>
	<br>
	<table class="table">
    	<thead>
    		<tr>
    			<th scope="col">#</th>
    			<th scope="col">Nombre</th>
    			<th scope="col">Descripcion</th>
    			<th scope="col"></th>
    			<th scope="col"></th>
    		</tr>
    	</thead>
    	<tbody>
    		<c:forEach items="${listaCategorias}" var="categoria">
    			<tr>
    				<th scope="row">${categoria.getId()}</th>
    				<td>${categoria.getNombre()}</td>
    				<td>${categoria.getDescripcion()}</td>
    				<td>
    					<form action="/categoria/editar" method="POST">
    						<input type="Hidden" name="id" value="${categoria.getId()}">
    						<input type="submit" value="Editar">
    					</form>
    				</td>
    				<td>
    					<form action="/categoria/eliminar" method="POST">
    						<input type="Hidden" name="id" value="${categoria.getId()}">
    						<input type="submit" value="Eliminar">
    					</form>
    				</td>
    			</tr>
    		</c:forEach>
    	</tbody>
    </table>
    <br>
    <form action="/inicio">
		<input type="submit" value="Volver a comprar">
	</form>
</body>
</html>
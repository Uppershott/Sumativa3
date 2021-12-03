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
	<form action="/producto">
		<input type="submit" value="Crear productos">
	</form>
	<form action="/categoria">
		<input type="submit" value="Crear categorias">
	</form>
	<br>
	<H3>Agrega productos al carrito!</H3>
	<form action="/producto/buscarCategoria" method="POST">
		<select name="id">
			<option value="0">
				Todos los libros
			</option>
	   		<c:forEach items="${listaCategorias}" var="categoria">
	   			<option value="${categoria.getId()}">
	   				${categoria.getNombre()}
	   			</option>
    		</c:forEach>
    	</select>
    	<input type="submit" value="Buscar">
	</form>
	<table class="table">
    	<thead>
    		<tr>
    			<th scope="col">#</th>
    			<th scope="col">ISBN</th>
    			<th scope="col">Nombre del Libro</th>
    			<th scope="col">Autor</th>
    			<th scope="col">Precio</th>
    			<th scope="col">Categorías</th>
    			<th scope="col"></th>
    		</tr>
    	</thead>
    	<tbody>
    		<c:forEach items="${listaProductos}" var="producto">
    			<tr>
    				<th scope="row">${producto.getId()}</th>
    				<td>${producto.getIsbn()}</td>
    				<td>${producto.getNombre()}</td>
    				<td>${producto.getAutor()}</td>
    				<td>${producto.getPrecio()}</td>
    				<td>
    					<c:forEach items="${producto.categorias}" var="categoria">
    						${categoria.getNombre()} 	
    					</c:forEach>
    				</td>
    				<td>
    					<form action="/producto/agregarCarrito" method="POST">
    						<input type="Hidden" name="id" value="${producto.getId()}">
    						<input type="submit" value="Agregar">
    					</form>
    				</td>
    			</tr>
    		</c:forEach>
    	</tbody>
    </table>
    <br>
    <form action="/producto/productoUsuario">
		<input type="submit" value="Ver carrito">
	</form>
	<br>
	<form action="/logout">
		<input type="submit" value="Cerrar sesion">
	</form>
</body>
</html>
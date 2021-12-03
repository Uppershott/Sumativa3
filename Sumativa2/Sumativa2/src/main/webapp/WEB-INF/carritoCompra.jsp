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
	<H3>Productos en el carrito!</H3>
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
    		<c:forEach items="${carritoProductos}" var="producto">
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
    					<form action="/producto/quitarProducto" method="POST">
    						<input type="Hidden" name="id" value="${producto.getId()}">
    						<input type="submit" value="Quitar">
    					</form>
    				</td>
    			</tr>
    		</c:forEach>
    	</tbody>
    </table>
    <H4>Costo total: ${costoTotal}</H4>
    <br>
    <form action="/producto/anularCompra" method="POST">
		<input type="submit" value="Anular">
	</form>
	<form action="/inicio">
		<input type="submit" value="Seguir comprando">
	</form>
    <form action="/producto/procederCompra" method="POST">
		<input type="submit" value="Comprar">
	</form>
</body>
</html>
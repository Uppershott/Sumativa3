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
	<H3>Libros</H3>
	<form:form action="/producto/crear" method="POST" modelAttribute="producto">
		<form:label path="isbn">ISBN: </form:label>
		<form:input path="isbn" type="text"/>
		<br>
		<form:label path="nombre">Nombre del Libro: </form:label>
		<form:input path="nombre" type="text"/>
		<br>
		<form:label path="autor">Autor: </form:label>
		<form:input path="autor" type="text"/>
		<br>
		<form:label path="precio">Precio: </form:label>
		<form:input path="precio" type="text"/>
		<br>
		<form:label path="categorias">Categorias: </form:label>
    	<form:select path="categorias" multiple="true" >
			<option selected disabled>Seleccione Categoria</option>
			<c:forEach items="${listaCategorias}" var="categoria">
				<form:option value="${categoria.getId()}">
					${categoria.getNombre()}
				</form:option>
			</c:forEach>
		</form:select>
		<br>
		<input type="submit" value="Guardar">
	</form:form>
	<br>
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
    					<form action="/producto/editar" method="POST">
    						<input type="Hidden" name="id" value="${producto.getId()}">
    						<input type="submit" value="Editar">
    					</form>
    				</td>
    				<td>
    					<form action="/producto/eliminar" method="POST">
    						<input type="Hidden" name="id" value="${producto.getId()}">
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
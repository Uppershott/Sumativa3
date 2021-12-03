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
	<form:form action="/producto/editarProducto" method="POST" modelAttribute="producto">
		<form:input type="hidden" path="id"/>
		<input type="hidden" name="_method" value="put"/>
		
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
		<input type="button" onClick="window.history.back()" value="Cancelar">
		<input type="submit" value="Guardar">
	</form:form>
</body>
</html>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="springform" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>FormularioEditarCategoria</title>
</head>
<body>
<jsp:include page="cabecera.jsp"></jsp:include>

<div>Actualiza los datos de la Categoria</div>

<p class="fs-1">Introduce los nuevos datos de la nueva Categoria:</p>

<springform:form modelAttribute="categoria" action="guardarCambiosCategoria" enctype="multipart/form-data">

<div>

	<div class="mb-3">
		nombre : <springform:input path="nombre" class="form-label"/>
	</div>
	
	<div class="mb-3">
		descripcion: <springform:input path="descripcion" class="form-label"/>
	</div>
	
	<div class="mb-3">
		alta: <springform:checkbox path="alta" class="form-label"/><br>
		<springform:hidden path="id"/>
	</div>
	
	<div class="mb-3">
		<input type="submit" value="Guardar Cambio" class="btn btn-secondary">
	</div>

</div>

</springform:form>
</body>
</html>








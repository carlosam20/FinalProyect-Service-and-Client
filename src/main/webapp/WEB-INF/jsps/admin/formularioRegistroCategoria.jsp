<%@ taglib uri="http://www.springframework.org/tags/form" prefix="springform" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>formularioRegistroCategoria</title>
</head>
<body>
<jsp:include page="cabecera.jsp"></jsp:include>

<springform:form modelAttribute="nuevaCategoria" action="guardarNuevaCategoria" enctype="multipart/form-data">


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
<input type="submit" value="Registrar Categoria" class="btn btn-secondary">
</div>
</div>
</springform:form>
</body>
</html>








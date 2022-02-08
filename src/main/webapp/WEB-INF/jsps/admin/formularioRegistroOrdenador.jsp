<%@ taglib uri="http://www.springframework.org/tags/form" prefix="springform" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>formularioRegistroOrdenador</title>
</head>
<body>
<jsp:include page="cabecera.jsp"></jsp:include>

<springform:form modelAttribute="nuevoOrdenador" action="guardarNuevoOrdenador" enctype="multipart/form-data">

tipo: <springform:input path="tipo"/><br><span style="color:red"><springform:errors path="tipo"/></span><br>
marca: <springform:input path="marca"/><br>
modelo: <springform:input path="modelo"/><br>
categoria: 
	<springform:select path="idCategoria">
		<springform:options items="${categorias}"/>
	</springform:select><br>
procesador: <springform:input path="procesador"/><br>
grafica: <springform:input path="grafica"/><br>
ram: <springform:input path="ram"/><br><span style="color:red"><springform:errors path="ram"/></span><br>
peso: <springform:input path="peso"/><br><span style="color:red"><springform:errors path="peso"/></span><br>
precio: <springform:input path="precio"/><br><span style="color:red"><springform:errors path="precio"/></span><br>
alta: <springform:checkbox path="alta"/><br>
<!-- Imagen que introducimos -->
imagen : <springform:input path="imagen" type="file"/><br>

<input type="submit" value="Registrar Ordenador">



</springform:form>
</body>
</html>








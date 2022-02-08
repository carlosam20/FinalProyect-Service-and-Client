<%@ taglib uri="http://www.springframework.org/tags/form" prefix="springform" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Formulario editar ordenador</title>
</head>
<body>
<jsp:include page="cabecera.jsp"></jsp:include>

<div>Actualiza los datos del ordenador</div>

Introduce los nuevos datos del nuevo ordenador: <br>

<springform:form modelAttribute="ordenador" action="guardarCambiosOrdenador" enctype="multipart/form-data">

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
imagen: <img alt="" src="../subidas/${ordenador.id}.jpg" style="height: 120px"/><br>
<springform:input path="imagen" type="file"/><br>
peso: <springform:input path="peso"/><br><span style="color:red"><springform:errors path="peso"/></span><br>
precio: <springform:input path="precio"/><br><span style="color:red"><springform:errors path="precio"/></span><br>
alta: <springform:checkbox path="alta"/><br>
<springform:hidden path="id"/>

<input type="submit" value="Guardar Cambios" class="btn btn-secondary">

</springform:form>


</body>
</html>
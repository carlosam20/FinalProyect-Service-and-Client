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

<springform:form modelAttribute="nuevoUsuario" action="guardarNuevoUsuario" enctype="multipart/form-data">


nombre: <springform:input path="nombre"/>
pass: <springform:input path="pass"/>
email: <springform:input path="email"/>
alta: <springform:checkbox path="alta"/>
<springform:hidden path="id"/>
<!-- Imagen que introducimos -->

<input type="submit" value="Registrar Usuario">



</springform:form>
</body>
</html>








<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>pedidos</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<link rel="stylesheet" href="../css/style.css">
</head>
<body>

<jsp:include page="cabecera.jsp"></jsp:include>
<a href="registrarPedido">Nuevo pedido</a><br>
<c:forEach var="pedido" items="${info}" >
	<div class="card mx-auto"style="margin: 10px;">
	nombre completo: ${pedido.nombreCompleto}<br>
	direccion: ${pedido.direccion} <br>
	estado: ${pedido.estado} <br>
	<a href="verDetallesPedido?id=${pedido.id}">ver pedido</a>

	</div>
</c:forEach>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ordenadores</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>

<jsp:include page="cabecera.jsp"></jsp:include>
<a class="btn btn-outline-secondary m-3" href="registrarOrdenador">Nuevo ordenador</a><br>


<form action="listarOrdenadores">

<div class="input-group mb-3">
  <span class="input-group-text" id="basic-addon1">Marca:</span>
  <!--  <input type="text"  placeholder="Username" aria-label="Username" aria-describedby="basic-addon1">-->
  <input type="text" name="marca" value="${marca}" class="form-control" placeholder="Username" aria-label="Marca" aria-describedby="basic-addon1" />
  <input type="submit" value="BUSCAR"  class="btn btn-primary" />
</div>

	 
</form>


<div class="mx-auto">
	paginacion: <br>
	total de ordenadores: ${total} <br>
	<c:if test="${ anterior >= 0 }">
		<a href="listarOrdenadores?comienzo=${anterior}&marca=${marca}" class="btn btn-outline-primary">anterior</a>
	</c:if>
	
	
	<c:if test="${siguiente < total}">
		<a href="listarOrdenadores?comienzo=${siguiente}&marca=${marca}" class="btn btn-outline-primary">siguiente</a>
	</c:if>
	
</div>



	
<c:forEach var="ordenador" items="${info}" >

<div class="card mx-auto " style="width: 32rem;" >

  <img style="height: 500px"class="card-img-top" src="../subidas/${ordenador.id}.jpg?t=${fecha_hora_actual}"/><br>
  <div class="card-body">
    <h5 class="card-title">${ordenador.marca}  ${ordenador.modelo }</h5>
    <p class="card-text">Descripcion</p>
  </div>
  
  <ul class="list-group list-group-flush">
    <li class="list-group-item">tipo: ${ordenador.tipo} </li>
    <li class="list-group-item">categoria: ${ordenador.categoria } </li>
    <li class="list-group-item">procesador: ${ordenador.procesador}</li>
    <li class="list-group-item">grafica: ${ordenador.grafica}</li>
    <li class="list-group-item">ram: ${ordenador.ram}</li>
    <li class="list-group-item">precio: ${ordenador.precio}</li>
    <li class="list-group-item">peso: ${ordenador.peso}</li>
    <li class="list-group-item">alta: ${ordenador.alta}</li>
    <li class="list-group-item">id: ${ordenador.id}<br></li>
  </ul>
  
  <div class="card-body">
  <c:if test="${ ordenador.alta == true}">
		 	<a  href="darBajaOrdenador?id=${ordenador.id}"class="card-link">dar de baja</a>
		 </c:if>
		 <c:if test="${ ordenador.alta == false}">
		 	<a href="darAltaOrdenador?id=${ordenador.id}" class="card-link">dar de alta</a>
	</c:if>
	<a href="editarOrdenador?id=${ordenador.id}" class="card-link" >editar</a>
  </div>
</div>
</c:forEach>
	
	<!--
	<a href="borrarOrdenador?id=${ordenador.id}" onclick="return confirm(Estas seguro?)">borrar</a> 
	 -->
	

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>
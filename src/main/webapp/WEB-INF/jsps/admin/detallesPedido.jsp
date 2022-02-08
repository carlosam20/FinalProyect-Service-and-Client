<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="cabecera.jsp"></jsp:include>
<div style="text-align: center;">detalles del pedido</div><br>
nombre: ${pedido.nombreCompleto} <br>
direccion: ${pedido.direccion} <br>
codigo postal: ${pedido.codigoPostal} <br>
pais: ${pedido.pais} <br>
provincia: ${pedido.provincia} <br>
tipo tarjeta: ${pedido.tipoTarjeta} <br>
titular tarjeta: ${pedido.titularTarjeta} <br>
numero tarjeta: ${pedido.numeroTarjeta} <br>
<div style="text-align: center;">productos del pedido</div><br>
<c:forEach var="productoPedido" items="${pedido.productosPedido}">
	<div style="text-align: left;">
	marca: ${productoPedido.ordenador.marca}
	modelo : ${productoPedido.ordenador.modelo}
	precio/unidad: ${productoPedido.ordenador.precio} <br>
	cantidad: ${productoPedido.cantidad} <br>
	</div>
</c:forEach>
<input type="hidden" id="id_pedido" value="${pedido.id}"/>
<br>	
ESTADO DEL PEDIDO:
<select id="select_estado">
	<c:forEach var ="estado" items="${estados}">
		<option 
			<c:if test="${ estado.key == pedido.estado }">
				 selected="selected"
			</c:if>
		  value="${estado.key}">${estado.value}</option>
	</c:forEach>
</select>
<script type="text/javascript" src="../librerias_javascript/jquery.js"></script>
<script type="text/javascript">
$("#select_estado").change(function(e){
	var estado = $("#select_estado").find(":selected").val();
	var idPedido = $("#id_pedido").val();
	$.post("servicioWebPedidos/actualizarEstadoPedido",
			{
				id : idPedido,
				estado: estado
			}
	).done(function(res){
			if(res != "ok"){
				alert(res);
			}
	});//end done
	
});
</script>
</body>
</html>
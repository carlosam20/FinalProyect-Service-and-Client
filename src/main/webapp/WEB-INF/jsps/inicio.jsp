<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>inicio</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<link rel="stylesheet" href="css/style.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
</head>
<body>





<!-- Navbar -->
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
  <div class="container-fluid">
    <a class="navbar-brand">
      <span class="material-icons" width="34" height="38" class="d-inline-block align-text-top">shopping_bag</span>
    </a>
    <a class="navbar-brand">
      ShopCompu	
    </a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavDropdown">
      <ul class="navbar-nav">
        <li class="nav-item">
          <a href="#" id="enlace_listado" class="nav-link"><spring:message code="inicio.ordenadores"/></a> 
        </li>
        <li class="nav-item">
          <a href="#"id="enlace_carrito" class="nav-link"><spring:message code="inicio.carrito"/></a>
        </li>
        <li class="nav-item">
          <a href="#"id="enlace_registrarme" class="nav-link"><spring:message code="inicio.registrarme"/></a>
        </li>
        <li class="nav-item">
          <a href="#" id="enlace_identificarme" class="nav-link"><spring:message code="inicio.identificarme"/></a>
        </li>
        <li class="nav-item">
          <a href="#" id="enlace_mis_pedidos" class="nav-link"><spring:message code="inicio.pedidos"/></a>
        </li>
         <li class="nav-item">
          <a href="#" id="enlace_mis_datos"  class="nav-link"><spring:message code="inicio.datos"/></a>
        </li>
        <li class="nav-item">
          <a href="#" id="enlace_logout"  class="nav-link"><spring:message code="inicio.sesion"/></a>
        </li>
         <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="navbarScrollingDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            <spring:message code="inicio.lenguaje"/>
          </a>
          <ul class="dropdown-menu" aria-labelledby="navbarScrollingDropdown">
            <li><a class="dropdown-item" href="?lang=en"><spring:message code="inicio.ingles"/></a></li>
            <li><a class="dropdown-item" href="?lang=es"><spring:message code="inicio.espanyol"/></a></li> 
          </ul>
        </li>
        <li class="nav-item">
        <p id="mensaje_login" class="nav-link active"></p>
        </li>
      </ul>
    </div>
  </div>
</nav>


<div style="text-align: center;" class="fs-1 m-3 text-primary"><spring:message code="inicio.bienvenida"/></div>



<div id="contenedor"></div>

<script type="text/javascript" src="librerias_javascript/jquery.js"></script>
<script type="text/javascript" src="librerias_javascript/mustache.js"></script>
<script type="text/javascript" src="librerias_javascript/js.cookie.min.js"></script>
<script type="text/javascript" src="js/carga_plantilla.js"></script>
<script type="text/javascript" src="js/validaciones.js"></script>
<script type="text/javascript">


//codigo del idioma
const idioma = '<spring:message code="codigo.idioma"/>';


var nombre_login="";


//parte de carga de las plantillas en variables:
var plantillaListarOrdenadores = "";
var plantillaCarrito = "";
var plantillaRegistrarUsuario = "";
var plantillaIdentificarUsuario = "";
var plantillaDetallesOrdenador = "";
var plantillaMisDatos = "";
var plantillaMisPedidos = "";
var checkout_1="";
var checkout_2 = "";
var checkout_3 = "";

		

		cargar_plantillas_del_servidor(idioma);
		//funciones ajax

	function obtener_listado() {
		alert("comunicarnos con ServicioLibros para que nos de en json"
				+ "los ordenadores que hay en la tienda");
		//Usando jquery podemos hacerlo usando la funcion $.ajax
		$.ajax("servicioWebOrdenadores/obtenerOrdenadores", {
			//este es un objeto que configura la llamada por ajax a ServicioLibros
			success : function(data) {
				//esto volcaria directamente el json recibido
				//$("#contenedor").html(data);
				alert("recibido: "+data);
				var productos = JSON.parse(data);
				for(i in productos){
					productos[i].fecha_hora_actual = new Date();
					productos[i].precio= productos[i].precio.toString().replace(".",",");
					productos[i].peso= productos[i].peso.toString().replace(".",",");
				}
				var texto_html = "";
				texto_html = Mustache.render(plantillaListarOrdenadores,
						productos);
				$("#contenedor").html(texto_html);
				//click en un producto
				$(".enlace_detalles").click(function(e){
					var id = $(this).attr("id_producto");
					alert("pedir al servidor todos los detalles "+
							"del producto de id: " + id + 
							" para mostrarselos al usuario");
					$.ajax("servicioWebOrdenadores/obtenerDetallesOrdenador?id="+id,{
						success:function(res){
							alert("recibido del server: " + res);
							var objeto_ordenador_recibido = JSON.parse(res);
							var texto_html = Mustache.render(plantillaDetallesOrdenador,objeto_ordenador_recibido);
							$("#contenedor").html(texto_html);
							$("#enlace_comprar").click(
								function(e) {
									comprar_producto($(this).attr("id_producto"));
								}
							);
						}
					});
				});//end click enlace_detalles			
			}//---end success---
		});//--end ajax--

	}//-end obtener_listado-

	//$ en javaScript suele ser jquery
		
	function comprar_producto(id){
			var cantidad = $('#campo_cantidad').val();
			$.ajax("identificado/servicioWebCarrito/agregarOrdenador?id="+id+"&cantidad="+cantidad,{
				success:function(res){
					if(res == "te has colado."){
						alert("tienes que identificarte para accder a tu carrito");
					}else{
						alert(res);
					}
				}
			});	
		}//end comprar_producto

	function obtenerProductosCarrito() {
			$.ajax("identificado/servicioWebCarrito/obtenerProductosCarrito",{
				success:function(res){
					if(res == "te has colado."){
						alert("tienes que identificarte para acceder a tu carrito");
					}else{
						var productos_carrito = JSON.parse(res);
						var html = Mustache.render(plantillaCarrito,productos_carrito);
						$("#contenedor").html(html);
						$(".enlace_borrar_producto").click(function(){
							var id = $(this).attr("id-producto");
							alert("indicar al servicio web que quite del carrito el producto de "+
									"id:"+id);
							$.post("identificado/servicioWebCarrito/borrarProductoCarrito",
									{
										idProducto : id+""
									}).done(function(res){
										if( res!= "ok" ){
											alert(res);
										}else{
											obtenerProductosCarrito();	
										}
									});
						});
						$(".input_cantidad").change(function(){
							var cantidad = $(this).val();
							var id = $(this).attr("id-producto");
							alert("indicar al servicio web la nueva cantidad: "+ cantidad +
									" sobre el producto de id: "+id);
							$.post("identificado/servicioWebCarrito/actualizarCantidadProductoCarrito",
									{
										cantidad: cantidad,
										id_producto: id
									}).done(function(res){
										alert(res);
									});
						});
						$("#realizar_pedido").click(function(){
							checkout_paso_0();
						});
					}	
				}
			});
		}
		
		
	function checkout_paso_0(){
		$("#contenedor").html(checkout_1);	
		$("#aceptar_paso_1").click(function(){
			checkout_paso_1_aceptar();
		});
	}

	function checkout_paso_1_aceptar(){
		//recoger los valores introducidos y mandarlos al servidor
		var nombre = $("#campo_nombre").val();
		var direccion = $("#campo_direccion").val();
		var provincia = $("#campo_provincia").find(":selected").val();
		var pais = $("#campo_pais").val();
		var codigoPostal = $("#campo_codigo_postal").val();
		
		//ahora lo suyo seria validar los valores recogidos
		//...
		
		//mandar los valores al servicio web 
		$.post("identificado/servicioWebPedidos/paso1",
				{
					nombre : nombre ,
					direccion : direccion ,
					provincia :	provincia ,
					pais : pais ,
					codigoPostal: codigoPostal
				}
		).done(function(res){
			if(res=="ok"){
				//mostrar checkout_2.html
				$("#contenedor").html(checkout_2);
				$("#checkout2_aceptar").click(checkout_paso_2_aceptar);
			}else{
				alert(res);
			}
		});
	}//end checkout_paso_1_aceptar

	function checkout_paso_2_aceptar(){
		var tipo_tarjeta = $("#tipo_tarjeta").find(":selected").val();
		var numero_tarjeta = $("#numero_tarjeta").val();
		var titular_tarjeta = $("#titular_tarjeta").val();
		$.post("identificado/servicioWebPedidos/paso2",{
			tarjeta : tipo_tarjeta,
			numero : numero_tarjeta,
			titular : titular_tarjeta
		}).done(function(res){
			if(res.substring(0,2)=="ok"){
				//mostrar el paso 3
				var json = JSON.parse(res.substring(3,res.length));
				
				var html = Mustache.render(checkout_3,json);
				
				$("#contenedor").html(html);
				$("#boton_confirmar_pedido").click(checkout_paso_3_confirmar);
			}
		});
	}//end checkout_paso_2_aceptar

	function checkout_paso_3_confirmar(){
		//lamar al servicio web de pedidos para que confirme el pedido:
		//+cambiar el estado del pedido
		//+registrar las informaciones de ProductoCarrito a ProductoPedido - por crear...
		$.ajax("identificado/servicioWebPedidos/paso3",{
			success : function(res){
				alert("respuesta del servicio web: " + res);
				obtener_listado();
			}
		})
		
	}
		

		//funciones que simplemente cargan plantilla

function mostrarRegistroUsuario(){
	$("#contenedor").html(plantillaRegistrarUsuario);
	$("#form_registro_usuario").submit(function(e){
		var nombre = $("#nombre").val();
		var email = $("#email").val();
		var pass = $("#pass").val();
		if( validarNombre(nombre) && validarEmail(email) && 
			validarPass(pass)){
			alert("todo ok, mandando informacion al servicio web...");
			//vamos a usar FormData para mandar el form al servicio web
			var formulario = document.forms[0];
			var formData = new FormData(formulario);
			$.ajax("servicioWebUsuarios/registrarUsuario",{
				type: "POST",
				data: formData,
				cache: false,
				contentType: false,
				processData: false,
				success: function(res){
					if(res == "ok"){
						alert("registrado correctamente, ya puedes identificarte");
						mostrarIdentificacionUsuario();
					}else{
						alert(res);
						alert("Usuario no valido");
					}
				}
			});
				
		}//end if validaciones
		e.preventDefault();
	});
}
var email = "";
var pass = "";	
function mostrarIdentificacionUsuario(){
	$("#contenedor").html(plantillaIdentificarUsuario);
	if( typeof(Cookies.get("email")) != "undefined" ){
		$("#email").val(Cookies.get("email"));
	}
	if( typeof(Cookies.get("pass")) != "undefined" ){
		$("#pass").val(Cookies.get("pass"));
	}
	$("#form_login").submit(function(e){
		email = $("#email").val();
		pass = $("#pass").val();
		$.ajax("servicioWebUsuarios/identificarUsuario",{
			data: "email="+email+"&pass="+pass,
			success: function(res){
				if (res.split(",")[0] == "ok"){
					nombre_login = res.split(",")[1];
					alert("identificado correctamente, ya puedes comprar productos " + nombre_login);
					if( $("#recordar_datos").prop('checked') ){
						alert("guardar datos en cookie");
						Cookies.set('email', email, { expires: 100 });
						Cookies.set('pass', pass, { expires: 100 });	
					}
					$("#mensaje_login").html("( identificado como: " + nombre_login + " )");
					$("#contenedor").html("login ok");
				}else{
					alert(res);
				}
			}			
		});//end.ajax
		e.preventDefault();				
	});
	
	
}

	function logout(){
		$.ajax("servicioWebUsuarios/logout",{
			success:function(res){
				if(res == "ok"){
					$("#contenedor").html("hasta pronto " + nombre_login);
					$("#mensaje_login").html("( no estas identificado )");
				}
			}
		});	
	}
	
	function mis_pedidos(){
		$.ajax("identificado/servicioWebPedidos/obtenerPedidoPorId",{
			success:function(data){
				alert("recibido: "+data);
				var pedidos = JSON.parse(data);
				var texto_html = "";
				texto_html = Mustache.render(plantillaMisPedidos,pedidos);
				$("#contenedor").html(texto_html);
			}// end success
		});// end ajax
	} // end obtener_listado
	
	function mis_datos(){
		$.ajax("identificado/servicioWebUsuarios/obtenerUsuarioPorId",{
			success:function(data){
				alert("recibido: "+data);
				var info = JSON.parse(data);	
				var texto_html = "";
				texto_html = Mustache.render(plantillaMisDatos,info);
				$("#contenedor").html(texto_html);
			}//end success
		});	//end ajax
	}//end mis_datos

	// eventos de los enlaces:
	$("#enlace_listado").click(obtener_listado);
	$("#enlace_carrito").click(obtenerProductosCarrito);
	$("#enlace_registrarme").click(mostrarRegistroUsuario);
	$("#enlace_identificarme").click(mostrarIdentificacionUsuario);
	$("#enlace_logout").click(logout);
	$("#enlace_mis_pedidos").click(mis_pedidos);
	$("#enlace_mis_datos").click(mis_datos);

	
	/*
	if(idioma == "es"){
		$("#contenedor").html("hola desde jquery");
	}else{
		$("#contenedor").html("hi from jquery");
	}
	
	// es como una prueba esta linea
	$("#contenedor").html("hola desde jquery");
	*/
	
	
	/*
	// interacci�n visual
	$("a").mouseover(function() {
		this.style.backgroundColor = "MediumPurple";
	});
	$("a").mouseout(function() {
		this.style.backgroundColor = "white";
	});
	*/
	
	//comprobar si el usuario actual sigue idenficado
	$.ajax("servicioWebUsuarios/comprobarIdentificacion",{
		success:function(res){
			if(res.split(",")[0]=="ok"){
				nombre_login = res.split(",")[1];
				$("#mensaje_login").html(" Perfil: " + nombre_login + " ");
			}else{
				$("#mensaje_login").html("( no estas identificado )");
			}
		}
	});
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>
package serviciosWEB.identificado;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;

import datos.servicios.ResumenPedido;
import modelo.Usuario;
import servicios.ServicioPedidos;

@Controller
@RequestMapping("identificado/servicioWebPedidos/")
public class ServicioWebPedidos {
	
	@Autowired
	private ServicioPedidos servicioPedidos;
	
	@RequestMapping("paso1")
	public ResponseEntity<String> paso1(String nombre, String direccion, String provincia, String pais, String codigoPostal,
			HttpServletRequest request){
		
		String respuesta = "";
		Usuario u = (Usuario)request.getSession().getAttribute("usuario");
		servicioPedidos.procesarPaso1(nombre, direccion, provincia,pais,codigoPostal, u.getId());
		respuesta = "ok";
		
		return new ResponseEntity<String>(
				respuesta,HttpStatus.OK);
	}
	
	@RequestMapping("paso2")
	public ResponseEntity<String> paso2 (String tarjeta, String numero, String titular, 
			HttpServletRequest request){
		String respuesta = "";
		Usuario u = (Usuario)request.getSession().getAttribute("usuario");
		servicioPedidos.procesarPaso2(titular, numero, tarjeta, u.getId());
		ResumenPedido resumen = servicioPedidos.obtenerResumenDelPedido(u.getId());
		String json = new Gson().toJson(resumen);
		respuesta = "ok:"+json;
		return new ResponseEntity<String>(
				respuesta,HttpStatus.OK);		
	}
	@RequestMapping("paso3")
	public ResponseEntity<String> paso2 (HttpServletRequest request){
		String respuesta = "";
		Usuario u = (Usuario) request.getSession().getAttribute("usuario");
		servicioPedidos.confirmarPedido(u.getId());
		respuesta = "pedido completado";
		
		return new ResponseEntity<String>(
				respuesta,HttpStatus.OK);	
	}//end paso3
	
	@RequestMapping("obtenerPedidoPorId")
	public ResponseEntity<String> obtenerPedidoPorId(HttpServletRequest request){
		Usuario u = (Usuario)request.getSession().getAttribute("usuario");
		System.out.println(servicioPedidos.obtenerPedidosParaListadoSegunIdUsuario(u.getId()));
		String json = new Gson().toJson(servicioPedidos.obtenerPedidosParaListadoSegunIdUsuario(u.getId()));
		System.out.println(json);
		return new ResponseEntity<String>(
				json,HttpStatus.OK);	
	}

}

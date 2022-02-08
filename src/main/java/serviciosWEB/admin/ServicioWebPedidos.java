package serviciosWEB.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import servicios.ServicioPedidos;

@Controller("servicioWebPedidosAdmin")//tengo que darle un nombre la bean 
//que autogenera spring, porque sino al tener otra clase que se llama igual
//que esta, tendria dos nombres autogenerados iguales, lo cual daria error
@RequestMapping("admin/servicioWebPedidos/")
public class ServicioWebPedidos {

	@Autowired
	private ServicioPedidos servicioPedidos;
	
	
	@RequestMapping("actualizarEstadoPedido")
	public ResponseEntity<String> actualizarEstadoPedido(String id, String estado){
		String respuesta = "";
		System.out.println("id:" + id);
		System.out.println("estado:" + estado);
		
		servicioPedidos.actualizarEstadoPedido(Integer.parseInt(id), estado);
		respuesta = "ok";
		return new ResponseEntity<String>(
				respuesta,HttpStatus.OK);
	}
	
}








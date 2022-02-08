package serviciosWEB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;

import servicios.ServicioOrdenadores;



@Controller
@RequestMapping("servicioWebOrdenadores/")
public class ServicioWebOrdenadores {

	@Autowired
	private ServicioOrdenadores servicioOrdenadores;
	
	@RequestMapping("obtenerOrdenadores")
	public ResponseEntity<String> obtenerOrdenadores(){
		String json = new Gson().toJson(servicioOrdenadores.obtenerOrdenadoresParaListado());
		return new ResponseEntity<String>(
				json,HttpStatus.OK);	
	}
	@RequestMapping("obtenerDetallesOrdenador")
	public ResponseEntity<String> obtenerDetallesLibro(String id){
		String json = new Gson().toJson(servicioOrdenadores.obtenerDetallesOrdenador(Integer.parseInt(id)));
		return new ResponseEntity<String>(
				json,HttpStatus.OK);
	}
	

}

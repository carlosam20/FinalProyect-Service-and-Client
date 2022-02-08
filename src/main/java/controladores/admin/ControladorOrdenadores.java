package controladores.admin;

import java.util.Date;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import modelo.Ordenador;
import servicios.ServicioCategorias;
import servicios.ServicioOrdenadores;
import utilidadesArchivos.GestorArchivos;

@Controller
@RequestMapping("admin/")
public class ControladorOrdenadores {

	@Autowired
	private ServicioOrdenadores servicioOrdenadores;

	
	@Autowired
	private ServicioCategorias servicioCategorias;
	
	@RequestMapping("listarOrdenadores")
	public String listarOrdenadores(@RequestParam(defaultValue = "")String marca, Integer comienzo, Model model) {
		
		int comienzo_int = 0;
		if (comienzo != null) {
			comienzo_int = comienzo.intValue();
		}
		
		model.addAttribute("info", servicioOrdenadores.obtenerOrdenadores(marca, comienzo_int));
		model.addAttribute("fecha_hora_actual", new Date().getTime());
		
		model.addAttribute("siguiente", comienzo_int+10);
		model.addAttribute("anterior", comienzo_int-10);
		model.addAttribute("total", servicioOrdenadores.obtenerTotalDeOrdenadores(marca));
		model.addAttribute("marca", marca);
		
		return "admin/ordenadores";
	}

	@RequestMapping("guardarNuevoOrdenador")
	public String guardarNuevoOrdenador(@ModelAttribute("nuevoOrdenador") @Valid Ordenador nuevoOrdenador, BindingResult br,  Model model,
			HttpServletRequest request) {
		if (!br.hasErrors()) {
			servicioOrdenadores.registrarOrdenador(nuevoOrdenador);
			System.out.println("archivo de imagen" + nuevoOrdenador.getImagen());
			String rutaRealDelProyecto =
				request.getServletContext().getRealPath("");
			GestorArchivos.guardarImagenOrdenador(nuevoOrdenador, rutaRealDelProyecto);
			return "admin/registroOrdenadorOk";
			
		} else {
			
			Map<String, String> mapCategorias = servicioCategorias.obtenerCategoriasparaDesplegable();
			model.addAttribute("categorias", mapCategorias);
			
			model.addAttribute("nuevoOrdenador", nuevoOrdenador);
			return "admin/formularioRegistroOrdenador";
		}
		
	}

	@RequestMapping("registrarOrdenador")
	public String registrarOrdenador(Model model) {
		Ordenador nuevo = new Ordenador();
		model.addAttribute("nuevoOrdenador", nuevo);
		
		Map<String, String> mapCategorias = servicioCategorias.obtenerCategoriasparaDesplegable();
		
		model.addAttribute("categorias", mapCategorias);
		return "admin/formularioRegistroOrdenador";
	}

	@RequestMapping("borrarOrdenador")
	public String borrarOrdenador(String id, Model model) {
		servicioOrdenadores.borrarOrdenador(Integer.parseInt(id));
		return listarOrdenadores("",0,model);
	}

	@RequestMapping("editarOrdenador")
	public String editarOrdenador(String id, Model model) {
		Ordenador o = servicioOrdenadores.obtenerOrdenadoresPorId(Integer.parseInt(id));
		Map<String, String> mapCategorias = servicioCategorias.obtenerCategoriasparaDesplegable();
		model.addAttribute("categorias", mapCategorias);
		o.setIdCategoria(o.getCategoria().getId()); 
		model.addAttribute("ordenador", o);
		return "admin/formularioEditarOrdenador";
		
	}

	@RequestMapping("guardarCambiosOrdenador")
	public String guardarCambiosOrdenador(@ModelAttribute("ordenador") @Valid Ordenador ordenador, BindingResult br,  Model model,
			HttpServletRequest request) {
		servicioOrdenadores.guardarCambiosOrdenador(ordenador);
		
		if(!br.hasErrors()) {
			
			System.out.println("datos del ordenador a actualizar: " + ordenador);
			servicioOrdenadores.guardarCambiosOrdenador(ordenador);
			System.out.println("archivo de portada: " + ordenador.getImagen());
			String rutaRealDelProyecto = 
					request.getServletContext().getRealPath("");
			GestorArchivos.guardarImagenOrdenador(ordenador, rutaRealDelProyecto);
			return listarOrdenadores("",0,model);
		}else {
			model.addAttribute("ordenador",ordenador);
			model.addAttribute("categorias",servicioCategorias.obtenerCategoriasparaDesplegable());
			return "admin/formularioEditarOrdenador";
		}		
		
	}
	@RequestMapping("darBajaOrdenador")
	public String darBajaOrdenador(String id, Model model) {
		servicioOrdenadores.darBajaOrdenador(Integer.parseInt(id));
		return listarOrdenadores("",0,model);
	}
	
	@RequestMapping("darAltaOrdenador")
	public String darAltaOrdenador(String id, Model model) {
		servicioOrdenadores.darAltaOrdenador(Integer.parseInt(id));
		return listarOrdenadores("",0,model);
	}

}// end class

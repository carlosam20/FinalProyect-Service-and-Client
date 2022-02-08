package controladores.admin;

import java.util.Date;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import modelo.Usuario;
import servicios.ServicioUsuarios;


@Controller
@RequestMapping("admin/")
public class ControladoresUsuarios {

	@Autowired
	private ServicioUsuarios servicioUsuarios;
	
	@RequestMapping("listarUsuarios")
	public String listarUsuarios(@RequestParam(defaultValue = "")String nombre, Integer comienzo, Model model) {
		
		int comienzo_int = 0;
		if (comienzo != null) {
			comienzo_int = comienzo.intValue();
		}
		
		model.addAttribute("info", servicioUsuarios.obtenerUsuarios(nombre, comienzo_int));
		model.addAttribute("fecha_hora_actual", new Date().getTime());
		
		model.addAttribute("siguiente", comienzo_int+10);
		model.addAttribute("anterior", comienzo_int-10);
		model.addAttribute("total", servicioUsuarios.obtenerTotalDeUsuarios(nombre));
		model.addAttribute("nombre", nombre);
		
		
		return "admin/usuarios";
	}
	@RequestMapping("verMasDatosUsuario")
	public String listarUsuarios(String id, Model model) {
		//Usuario u = servicioUsuarios
		return null;
		
	}
	@RequestMapping("registrarUsuario")
	public String registrarUsuario(Model model) {
		Usuario nuevo = new Usuario();
		model.addAttribute("nuevoUsuario", nuevo);
		
		return "admin/formularioRegistroUsuarioOk";
	}
	@RequestMapping("guardarNuevoUsuario")
	public String guardarNuevoUsuario(@ModelAttribute("nuevoUsuario") @Valid Usuario nuevoUsuario, BindingResult br, Model model,
			HttpServletRequest request) {
		if (!br.hasErrors()) {		
			servicioUsuarios.registrarUsuario(nuevoUsuario);
		
			return "admin/registroUsuarioOk";
			
		} else {
			
			model.addAttribute("nuevoUsuario", nuevoUsuario);
			return "admin/formularioRegistroUsuario";
		}
		
	}
	@RequestMapping("guardarCambiosUsuario")
	public String guardarCambiosUsuario(@ModelAttribute("usuario") @Valid Usuario usuario, BindingResult br,  Model model,
			HttpServletRequest request) {
		servicioUsuarios.guardarCambiosUsuario(usuario);
		
		if(!br.hasErrors()) {
			servicioUsuarios.registrarUsuario(usuario);
			return listarUsuarios("",0,model);
		}else {
			model.addAttribute("usuario",usuario);
			return "admin/formularioEditarUsuario";
		}		
		
	}
	@RequestMapping("editarUsuario")
	public String editarUsuario(String id, Model model) {
		Usuario u = servicioUsuarios.obtenerUsuarioPorId(Integer.parseInt(id));
		model.addAttribute("usuario",u);
		return "admin/formularioEditarUsuario";
		
	}
	@RequestMapping("darBajaUsuario")
	public String darBajaUsuario(String id, Model model) {
		servicioUsuarios.darBajaUsuario(Integer.parseInt(id));
		return listarUsuarios("",0,model);
	}
	@RequestMapping("darAltaUsuario")
	public String darAltaUsuario(String id, Model model) {
		servicioUsuarios.darAltaUsuario(Integer.parseInt(id));
		return listarUsuarios("",0,model);
	}
	
}

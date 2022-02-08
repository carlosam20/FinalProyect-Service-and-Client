package controladores.admin;






import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import modelo.Categoria;
import servicios.ServicioCategorias;


@Controller
@RequestMapping("admin/")
public class ControladorCategoria {

	@Autowired
	private ServicioCategorias servicioCategorias;
	//Mapeo del jsp
	@RequestMapping("listarCategorias")
	public String listarCategorias(@RequestParam(defaultValue = "")String marca, Integer comienzo, Model model) {
		model.addAttribute("info",servicioCategorias.obtenerCategorias());
		
		return "admin/categorias";
	}
	
	@RequestMapping("registrarCategoria")
	public String registrarCategoria(Model model) {
		 Categoria nueva = new Categoria();
		model.addAttribute("nuevaCategoria", nueva);
		
		return "admin/formularioRegistroCategoria";
	}
	@RequestMapping("guardarNuevaCategoria")
	public String guardarNuevaCategoria(@ModelAttribute("nuevaCategoria") @Valid Categoria nuevaCategoria, BindingResult br,  Model model,
			HttpServletRequest request) {
		if (!br.hasErrors()) {
			servicioCategorias.registrarCategoria(nuevaCategoria);
			
			return "admin/registroCategoriaOk";
			
		} else {
				
			model.addAttribute("nuevaCategoria", nuevaCategoria);
			return "admin/formularioRegistroCategoria";
		}
		
	}
	
	@RequestMapping("editarCategoria")
	public String editarCategoria(String id, Model model) {
		Categoria c = servicioCategorias.obtenerCategoriasPorId(Long.parseLong(id)); 
		model.addAttribute("categoria", c);
		return "admin/formularioEditarCategoria";
		
	}
	
	@RequestMapping("guardarCambiosCategoria")
	public String guardarCambiosCategoria(@ModelAttribute("categoria") @Valid Categoria categoria, BindingResult br,  Model model,
			HttpServletRequest request) {
			servicioCategorias.guardarCambiosCategoria(categoria);
			
		if(!br.hasErrors()) {
			servicioCategorias.guardarCambiosCategoria(categoria);
			return listarCategorias("",0,model);
		}else {
			model.addAttribute("categoria",categoria);
			return "admin/formularioEditarCategoria";
		}		
		
	}
	
	@RequestMapping("darBajaCategoria")
	public String darBajaOrdenador(String id, Model model) {
		servicioCategorias.darBajaCategoria(Long.parseLong(id));
		return listarCategorias("",0,model);
	}
	
	@RequestMapping("darAltaCategoria")
	public String darAltaOrdenador(String id, Model model) {
		servicioCategorias.darAltaCategoria(Long.parseLong(id));
		return listarCategorias("",0,model);
	}
	
}

package servicios;

import java.util.List;

import java.util.Map;

import modelo.Categoria;

public interface ServicioCategorias {
	
	Map<String, String> obtenerCategoriasparaDesplegable();
	List<Categoria>obtenerCategorias();
	Categoria obtenerCategoriasPorId(long id);
	void registrarCategoria(Categoria c);
	void darBajaCategoria(long idCategoria);
	void darAltaCategoria(long idCategoria);
	 void guardarCambiosCategoria(Categoria c);
	
}

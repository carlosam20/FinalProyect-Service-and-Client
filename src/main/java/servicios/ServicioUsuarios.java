package servicios;

import java.util.List;
import java.util.Map;

import modelo.Usuario;

public interface ServicioUsuarios {

List<Map<String, Object>> obtenerUsuariosParaListado();
boolean comprobarEmail(String email);
void registrarUsuario(Usuario u);
Usuario obtenerUsuarioPorEmailYpass(String email, String pass);
Usuario obtenerUsuarioPorId(int id);
Map<String, Object> obtenerUsuarioParaPedido(int id);
List<Usuario> obtenerUsuarios(String nombre, int comienzo);
int obtenerTotalDeUsuarios(String nombre);
void darBajaUsuario(int idUsuario);
void darAltaUsuario(int idUsuario);
void guardarCambiosUsuario(Usuario l);
	
}

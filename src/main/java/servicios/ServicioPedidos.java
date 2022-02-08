package servicios;

import java.util.List;
import java.util.Map;
import datos.servicios.ResumenPedido;
import modelo.Pedido;

public interface ServicioPedidos {

	List<Pedido>obtenerPedidos();
	Pedido obtenerPedidoPorId(int id);
	void procesarPaso1(String nombre, String direccion, String provincia, String pais, String codigoPostal, int idUsuario);
	void procesarPaso2(String tipo_tarjeta, String numero_tarjeta, String titular_tarjeta, int idUsuario);
	ResumenPedido obtenerResumenDelPedido(int idUsuario);
	void confirmarPedido(int idUsuario);
	void registrarPedido(Pedido p);
	//void borrarOrdenador(int id);
	void guardarCambiosPedido(Pedido p);
	List <Map<String,Object>> obtenerPedidosParaListado();
	void actualizarEstadoPedido(int id, String estado);
	
	List <Map<String,Object>> obtenerPedidosParaListadoSegunIdUsuario(int id);
}

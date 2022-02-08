package constantesSQL;

public class ConstantesSQL {

	public static final String OBTENER_USUARIOS_PARA_LISTADO="SELECT u.id, u.email, u.pass, u.carrito_id, u.alta "
			+ "from usuario as u";	
	public static final String OBTENER_TOTAL_ORDENADORES = "select count(id) from ordenador where marca like :marca ";
	public static final String OBTENER_TOTAL_USUARIOS = "select count(id) from usuario where nombre like :nombre ";
	public final static String SQL_BORRAR_ORDENADOR="delete from ordenador where id = :id";
	public final static String SQL_OBTENER_CATEGORIAS_PARA_DESPLEGABLE = "select id, nombre from categoria order by id asc";
	public final static String SQL_OBTENER_ORDENADORES_PARA_LISTADO="select o.id,o.tipo, o.marca, o.modelo, o.peso, o.precio, o.procesador, o.grafica, o.ram, c.nombre as nombre_categoria "
			+ "from ordenador as o, categoria as c "
			+ "where o.categoria_id = c.id and o.alta = 1 "
			+ "order by o.id desc";
	public final static String SQL_OBTENER_DATOS_USUARIO="SELECT u.id, u.nombre, u.email, u.pass, u.carrito_id "
			+ "from usuario as u "
			+ "where u.id = :id ";
			
	public final static String SQL_OBTENER_PEDIDOS_PARA_LISTADO="SELECT p.id, p.direccion, p.estado, p.nombreCompleto, p.numeroTarjeta, p.provincia, p.tipoTarjeta, p.titularTarjeta, p.usuario_id, p.codigoPostal, p.pais"
			+ "from pedidos as p"
			+"order by p.id desc";
	public final static String SQL_OBTENER_PEDIDOS_PARA_LISTADO_USUARIO="SELECT p.id, p.direccion, p.estado, p.nombreCompleto, p.numeroTarjeta, p.provincia, p.tipoTarjeta, p.titularTarjeta, p.usuario_id, p.codigoPostal, p.pais "
			+ "from pedido as p "
			+ "where p.usuario_id= :usuario_id " 
			+"order by p.id desc";
	public static final String OBTENER_ORDENADOR_POR_ID = 
			"select * from ordenador where id = :id";
	public static final String OBTENER_PEDIDO_POR_ID = 
			"select * from pedido where id = :id";
	public static final String OBTENER_PRODUCTOS_CARRITO = "SELECT o.id as ordenador_id, o.tipo, o.marca, o.modelo, o.precio, o.grafica, o.procesador, o.ram, pc.cantidad FROM ordenador as o,productocarrito as pc WHERE pc.ordenador_id = o.id and pc.carrito_id = :carrito_id order by pc.id ASC";
	public static final String BORRAR_PRODUCTO_CARRITO = "delete from productocarrito where carrito_id = :id_carrito and "
			+ " ordenador_id = :id_ordenador ";
	public static final String BORRAR_PRODUCTOS_CARRITO =
			"delete from productocarrito where carrito_id = :carrito_id";
	
	

}

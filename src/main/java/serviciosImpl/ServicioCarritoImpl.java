package serviciosImpl;

import java.util.List;

import java.util.Map;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import constantesSQL.ConstantesSQL;
import modelo.Carrito;
import modelo.Ordenador;
import modelo.ProductoCarrito;
import modelo.Usuario;
import servicios.ServicioCarrito;

@Service
@Transactional
public class ServicioCarritoImpl implements ServicioCarrito{

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void agregarProducto(Usuario u, int idProducto, int cantidad) {
		
		//cantidad no puede ser 0 
				Usuario uBaseDeDatos = 
						(Usuario)sessionFactory.getCurrentSession().get(Usuario.class, u.getId());
				Carrito c = uBaseDeDatos.getCarrito();
				if(c==null) {
					c = new Carrito();
					c.setUsuario(uBaseDeDatos);
					uBaseDeDatos.setCarrito(c);
					sessionFactory.getCurrentSession().save(c);
				}
				
				
				boolean producto_en_carrito = false;
				for (ProductoCarrito pc_en_carrito  :c.getProductosCarrito()) {
					if(pc_en_carrito.getOrdenador().getId()==idProducto) {
						producto_en_carrito = true;
						pc_en_carrito.setCantidad(pc_en_carrito.getCantidad()+cantidad);
						sessionFactory.getCurrentSession().merge(pc_en_carrito);
					}
				}
				
				
				if(! producto_en_carrito) {
					ProductoCarrito pc = new ProductoCarrito();
					pc.setCarrito(c);
					pc.setOrdenador((Ordenador) sessionFactory.getCurrentSession().get(Ordenador.class, idProducto));
					pc.setCantidad(cantidad);
					sessionFactory.getCurrentSession().save(pc);
				}
		
//		//el usuairo u a pesar de tener la misma informacion que en la base de datos
//		//no podemos usarlo para operar directamente con hibernate
//		Usuario uBaseDatos = 
//				(Usuario)sessionFactory.getCurrentSession().get(Usuario.class, u.getId());
//		Carrito c = uBaseDatos.getCarrito();
//		if(c==null) {
//			//si el usuario no tenia asociado un carrito, le creo uno
//			c = new Carrito();
//			c.setUsuario(uBaseDatos);
//			uBaseDatos.setCarrito(c);
//			sessionFactory.getCurrentSession().save(c);
//		}
//		Ordenador o = (Ordenador)sessionFactory.getCurrentSession().get(Ordenador.class, idProducto);
//		c.getOrdenadores().add(o);
//		sessionFactory.getCurrentSession().save(c);
		
	}

	
	public List<Map<String, Object>> obtenerProductosCarrito(Usuario u) {
		Usuario uBaseDatos = (Usuario)sessionFactory.getCurrentSession().get(Usuario.class, u.getId());
		Carrito c = uBaseDatos.getCarrito();
		System.out.println("---ObtenerProductosCarritoImpl---");
		List<Map<String, Object>> res = null;
		if(c != null) {
			SQLQuery query = sessionFactory.getCurrentSession().
					createSQLQuery(ConstantesSQL.OBTENER_PRODUCTOS_CARRITO);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			query.setParameter("carrito_id", c.getId());
			res = query.list();
		}
		return res;
	}
	
	@Override
	public void actualizarProductoCarrito(int idUsuario, int idProducto, int cantidad) {
		Usuario uBaseDatos = 
				(Usuario)sessionFactory.getCurrentSession().get(Usuario.class, idUsuario);		
		Carrito c = uBaseDatos.getCarrito();
		if( c != null) {
			List<ProductoCarrito> pcs = c.getProductosCarrito();
			for (ProductoCarrito productoCarrito : pcs) {
				if (productoCarrito.getOrdenador().getId() == idProducto) {
					productoCarrito.setCantidad(cantidad);
					sessionFactory.getCurrentSession().update(productoCarrito);
				}
			}
		}
	}//end actualizarProductoCarrito

	@Override
	public void borrarProductoCarrito(int idUsuario, int idProducto) {
		Usuario uBaseDatos = (Usuario)
				sessionFactory.getCurrentSession().get(Usuario.class,idUsuario);
		Carrito c = uBaseDatos.getCarrito();
		if(c!=null) {
			//uso sql para borrar el registro
			SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(
					ConstantesSQL.BORRAR_PRODUCTO_CARRITO);
			query.setParameter("id_carrito", c.getId());
			query.setParameter("id_ordenador", idProducto);
			query.executeUpdate();			
		}
	}//end borrarProductoCarrito
	
}






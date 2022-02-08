package serviciosImpl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import constantesSQL.ConstantesSQL;
import modelo.Ordenador;
import modelo.Pedido;
import modelo.Usuario;
import servicios.ServicioUsuarios;

@Service
@Transactional
public class ServicioUsuariosImpl implements ServicioUsuarios{

	//@Resource asigna la bean de id indicada, en este caso fff
	//a la variable a continuacion definida
	@Resource(name = "fff")
	private SessionFactory sessionFactory;
	
	@Override
	public void registrarUsuario(Usuario u) {
		try {
			sessionFactory.getCurrentSession().save(u);
		}
		catch (Exception e) {
			
		}
		
	}

	@Override
	public boolean comprobarEmail(String email) {
		Criteria c = sessionFactory.getCurrentSession().createCriteria(Usuario.class);
		c.add(Restrictions.eq("email", email));
		if (c.uniqueResult() == null) {
			return false;
		}else {
			return true;
		}
	}
	
	@Override
	public int obtenerTotalDeUsuarios(String nombre) {
		SQLQuery query = sessionFactory.getCurrentSession().
				createSQLQuery(ConstantesSQL.OBTENER_TOTAL_USUARIOS);
		query.setParameter("nombre","%"+nombre+"%");
		return Integer.parseInt(query.list().get(0).toString());
	}
	

	@Override
	public Usuario obtenerUsuarioPorEmailYpass(String email, String pass) {
		Criteria c = sessionFactory.getCurrentSession().createCriteria(Usuario.class);
		c.add(Restrictions.eq("email", email));
		c.add(Restrictions.eq("pass",pass));		
		return (Usuario)c.uniqueResult();
	}

	@Override
	public List<Usuario> obtenerUsuarios(String nombre, int comienzo) {
		
		Criteria c = sessionFactory.getCurrentSession().createCriteria(Usuario.class);
		c.add(Restrictions.like("nombre", "%"+nombre+"%"));
		c.addOrder(Order.desc("id"));
		c.setFirstResult(comienzo);
		c.setMaxResults(10);
		return c.list();
	}

	@Override
	public List<Map<String, Object>> obtenerUsuariosParaListado() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario obtenerUsuarioPorId(int id) {
		// TODO Auto-generated method stub
		return  (Usuario) sessionFactory.getCurrentSession().get(Usuario.class, id);
	}

	@Override
	public void darBajaUsuario(int idUsuario) {
		Usuario u = (Usuario) sessionFactory.getCurrentSession().get(Usuario.class, idUsuario);
		u.setAlta(false);
		sessionFactory.getCurrentSession().update(u);
		
	}

	@Override
	public void darAltaUsuario(int idUsuario) {
		Usuario u = (Usuario) sessionFactory.getCurrentSession().get(Usuario.class, idUsuario);
		u.setAlta(true);
		sessionFactory.getCurrentSession().update(u);
		
	}

	@Override
	public void guardarCambiosUsuario(Usuario l) {
		
		sessionFactory.getCurrentSession().merge(l);
	}

	@Override
	public Map<String, Object> obtenerUsuarioParaPedido(int id) {
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(ConstantesSQL.SQL_OBTENER_DATOS_USUARIO);
		query.setParameter("id", id);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		return (Map<String, Object>)query.uniqueResult();
		
		
	}

	
	
	

}











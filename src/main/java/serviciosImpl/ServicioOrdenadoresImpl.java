package serviciosImpl;

import java.util.List;


import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import constantesSQL.ConstantesSQL;
import modelo.Categoria;
import modelo.Ordenador;
import servicios.ServicioOrdenadores;
@Service
@Transactional
public class ServicioOrdenadoresImpl implements ServicioOrdenadores{

	@Autowired
	private SessionFactory sessionFactory; // bean del hibernate-context
	
	public List<Map<String, Object>> obtenerOrdenadoresParaListado() {
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(ConstantesSQL.SQL_OBTENER_ORDENADORES_PARA_LISTADO);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> res = query.list();
		return res;
	}
	
	@Override
	public int obtenerTotalDeOrdenadores(String marca) {
		SQLQuery query = sessionFactory.getCurrentSession().
				createSQLQuery(ConstantesSQL.OBTENER_TOTAL_ORDENADORES);
		query.setParameter("marca","%"+marca+"%");
		return Integer.parseInt(query.list().get(0).toString());
	}
	
	@Override
	public List<Ordenador> obtenerOrdenadores(String marca, int comienzo) {
		
		Criteria c = sessionFactory.getCurrentSession().createCriteria(Ordenador.class);
		c.add(Restrictions.like("marca", "%"+marca+"%"));
		c.addOrder(Order.desc("id"));
		c.setFirstResult(comienzo);
		c.setMaxResults(10);
		return c.list();
	}

	@Override
	public void registrarOrdenador(Ordenador o) {
			Categoria c = (Categoria) sessionFactory.getCurrentSession().get(Categoria.class,o.getIdCategoria());
			o.setCategoria(c);
			sessionFactory.getCurrentSession().save(o);
	}

	@Override
	public Ordenador obtenerOrdenadoresPorId(int id) {
		return (Ordenador) sessionFactory.getCurrentSession().get(Ordenador.class, id);
	}

	@Override
	public void borrarOrdenador(int id) {
		System.out.println("Borrar Ordenador");
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(ConstantesSQL.SQL_BORRAR_ORDENADOR);
		query.setParameter("id", id);
		query.executeUpdate();
		
	}

	@Override
	public void guardarCambiosOrdenador(Ordenador l) {
		Categoria c = 
				(Categoria)
				sessionFactory.getCurrentSession().get(Categoria.class, l.getIdCategoria());
		l.setCategoria(c);
		sessionFactory.getCurrentSession().merge(l);
		
	}


	@Override
	public Map<String, Object> obtenerDetallesOrdenador(int id) {
		SQLQuery query = 
				sessionFactory.getCurrentSession().
					createSQLQuery(ConstantesSQL.OBTENER_ORDENADOR_POR_ID);
		query.setParameter("id", id);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		return (Map<String, Object>)query.uniqueResult();
	}
	@Override
	public void darBajaOrdenador(int idOrdenador) {
		Ordenador o = (Ordenador)sessionFactory.getCurrentSession().get(Ordenador.class, idOrdenador);
		o.setAlta(false);
		sessionFactory.getCurrentSession().update(o);		
	}

	@Override
	public void darAltaOrdenador(int idOrdenador) {
		Ordenador o = (Ordenador)sessionFactory.getCurrentSession().get(Ordenador.class, idOrdenador);
		o.setAlta(true);
		sessionFactory.getCurrentSession().update(o);	
	}





	

	
	
	
	

}

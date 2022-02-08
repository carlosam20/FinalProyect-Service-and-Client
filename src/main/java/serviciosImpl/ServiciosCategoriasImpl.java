package serviciosImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import constantesSQL.ConstantesSQL;
import modelo.Categoria;
import modelo.Ordenador;
import servicios.ServicioCategorias;

@Service
@Transactional
public class ServiciosCategoriasImpl implements ServicioCategorias{

	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public List<Categoria> obtenerCategorias() {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createCriteria(Categoria.class).list();
	}

	@Override
	public Map<String, String> obtenerCategoriasparaDesplegable() {
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(
				ConstantesSQL.SQL_OBTENER_CATEGORIAS_PARA_DESPLEGABLE);
		//Lo siguiente es para que me de como resultado elementos tipo Map
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		
		List <Map<String, Object>> res =query.list();
		Map<String, String> valoresDesplegable = new HashMap<String, String>();
		System.out.println("Obtenido de la base de datos:");
		for (Map<String, Object> map : res) {
			System.out.println("id: "+map.get("id") + " nombre" +map.get("nombre"));
			valoresDesplegable.put(map.get("id").toString(), map.get("nombre").toString());
		}
		return valoresDesplegable;
	}
	
	@Override
	public void guardarCambiosCategoria(Categoria c) {
		sessionFactory.getCurrentSession().merge(c);
		
	}

	@Override
	public void registrarCategoria(Categoria c) {
		sessionFactory.getCurrentSession().save(c);
	}
	
	@Override
	public void darBajaCategoria(long idCategoria) {
		Categoria c = (Categoria)sessionFactory.getCurrentSession().get(Categoria.class, idCategoria);
		c.setAlta(false);
		sessionFactory.getCurrentSession().update(c);		
	}

	@Override
	public void darAltaCategoria(long idCategoria) {
		Categoria c = (Categoria)sessionFactory.getCurrentSession().get(Categoria.class, idCategoria);
		c.setAlta(true);
		sessionFactory.getCurrentSession().update(c);	
	}

	@Override
	public Categoria obtenerCategoriasPorId(long id) {
		return (Categoria) sessionFactory.getCurrentSession().get(Categoria.class, id);
	}
	
	
}

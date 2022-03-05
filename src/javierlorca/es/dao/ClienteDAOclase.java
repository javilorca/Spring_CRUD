package javierlorca.es.dao;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javierlorca.es.controlador.entity.Cliente;

@Repository
public class ClienteDAOclase implements ClienteDAO {

	@Override
	@Transactional
	public List<Cliente> getClientes() {
		// TODO Auto-generated method stub
		
		//Obtener la session
		Session miSession=sessionFactory.getCurrentSession();
		
		//Crear consulta Query
		Query<Cliente> miQuery=miSession.createQuery("from Cliente", Cliente.class);
		
		//Ejecutar la Query y devolver resutados
		List<Cliente> clientes=miQuery.getResultList();
		
		return clientes;
	}

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public void insertarCliente(Cliente elCliente) {
		// TODO Auto-generated method stub
		
		//Obtener la session
		Session miSession=sessionFactory.getCurrentSession();
		
		//Insertar el cliente
		//miSession.save(elCliente);
		miSession.saveOrUpdate(elCliente);
		
	}

	@Override
	@Transactional
	public Cliente getCliente(int id) {
		// TODO Auto-generated method stub
		
		//Obtener la sesion
		Session miSession=sessionFactory.getCurrentSession();
		
		//Obtener la información del cliente seleccionado
		Cliente elCliente=miSession.get(Cliente.class, id);
		
		
		return elCliente;
	}

	@Override
	@Transactional
	public void eliminarCliente(int id) {
		// TODO Auto-generated method stub
		
		//Obtener la sesion
		Session miSession=sessionFactory.getCurrentSession();
		
		//Borrar el cliente de la BBDD utilizando como criterio su id
		
		Query consulta=miSession.createQuery("delete from Cliente where id=:IdDelCliente");
		
		consulta.setParameter("IdDelCliente", id);
		
		consulta.executeUpdate();
		
	}
}

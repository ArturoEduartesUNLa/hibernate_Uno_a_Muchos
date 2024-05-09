package dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import datos.Cliente;

public class ClienteDao {
	private static Session session;
	private static Transaction tx;

	private static void iniciaOperacion() throws HibernateException {
		session = HibernateUtil.getSessionFactory().openSession();
		tx = session.beginTransaction();
	}

	private void manejaExcepcion(HibernateException he) throws HibernateException {
		tx.rollback();
		throw new HibernateException("ERROR en la capa de acceso a datos " + he);
	}

	public long agregar(Cliente c) {
		long id = 0;
		try {
			iniciaOperacion();
			id = (long) session.save(c);
			tx.commit();

		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
		return id;

	}

	public void actualizar(Cliente c) {
		try {
			iniciaOperacion();
			session.update(c);
			tx.commit();

		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}

	}

	public void eliminar(Cliente c) {
		try {
			iniciaOperacion();
			session.delete(c);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
		} finally {
			session.close();
		}
	}

	public Cliente traer(long idCliente) {
		Cliente cliente = null;
		try {
			iniciaOperacion();
			cliente = session.get(Cliente.class, idCliente);

		} finally {
			session.close();
		}
		return cliente;
	}

	public Cliente traer(int dni) {

		Cliente cliente = null;
		try {
			iniciaOperacion();
			cliente = session.createQuery("from Cliente c where c.dni = :dni", Cliente.class).setParameter("dni", dni)
					.uniqueResult();
		} finally {
			session.close();
		}
		return cliente;

	}

	public List<Cliente> traer() {
		List<Cliente> lista = new ArrayList<Cliente>();
		try {
			iniciaOperacion();
			lista = session.createQuery("from Cliente c order by c.idCliente asc", Cliente.class).list();

			// forma alternativa lista.forEach(t-> Hibernate.initialize(t.getPrestamos()));

			for (Cliente c : lista) {
				Hibernate.initialize(c.getPrestamos());
			}

		} finally {
			session.close();
		}
		return lista;
	}

	public Cliente traerClienteYPrestamos(long idCliente) {
		Cliente c = null;
		try {
			iniciaOperacion();
			String hql = "from Cliente c left join fetch c.prestamos p where c.idCliente = :IdCliente";
			c = session.createQuery(hql, Cliente.class).setParameter("IdCliente", idCliente).uniqueResult();
			// Hibernate.initialize(c.getPrestamos());

		} finally {
			session.close();
		}
		return c;
	}
}
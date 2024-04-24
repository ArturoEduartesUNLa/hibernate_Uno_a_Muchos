package dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import datos.Cliente;
import datos.Prestamo;

/* COMPLETED
 * 
 */
public class PrestamoDao {
	private static Session session;
	private Transaction tx;

	private void iniciaOperacion() throws HibernateException {
		session = HibernateUtil.getSessionFactory().openSession();
		tx = session.beginTransaction();
	}

	private void manejaExcepcion(HibernateException he) throws HibernateException {
		tx.rollback();
		throw new HibernateException("ERROR en la capa de acceso a datos " + he);
	}

	public long agregar(Prestamo prestamo) {
		long id = 0;
		try {
			iniciaOperacion();
			id = (long) session.save(prestamo);
			tx.commit();

		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		}

		finally {
			session.close();
		}
		return id;
	}

	public void actualizar(Prestamo prestamo) {
		try {
			iniciaOperacion();
			session.update(prestamo);
			tx.commit();

		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
	}

	public void eliminar(Prestamo p) {
		try {
			
			iniciaOperacion();
			session.delete(p);
			tx.commit();
			
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		}
		finally {
			session.close();
		}
		
	}
	public Prestamo traerSinCliente(long idPrestamo) {
		Prestamo prestamo = null;
		try {
			iniciaOperacion();
			prestamo = session.get(Prestamo.class, idPrestamo);
		} finally {
			session.close();
		}
		return prestamo;
	}

	public Prestamo traer(long idPrestamo) {
		Prestamo prestamo = null;
		try {
			iniciaOperacion();
			String hql = "from Prestamo p inner join fetch p.cliente c where " + "p.idPrestamo = " + idPrestamo;
			prestamo = session.createQuery(hql, Prestamo.class).uniqueResult();
		} finally {
			session.close();
		}
		return prestamo;

	}

	public List<Prestamo> traer(Cliente c) {
		List<Prestamo> lista = null;
		try {
			String hql = "from Prestamo p inner join fetch p.cliente c where " + "c.IdCliente = " + c.getIdCliente();
			lista = session.createQuery(hql, Prestamo.class).list();
		} finally {
			session.close();
		}
		return lista;
	}

}
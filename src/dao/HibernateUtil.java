package dao;

/* COMPLETE
 * 
 */

import org.hibernate.HibernateError;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
	private static SessionFactory sessionFactory;

	public static SessionFactory getSessionFactory() {
		try {
			if (sessionFactory == null) {
				StandardServiceRegistry standardServiceRegistry = new StandardServiceRegistryBuilder().configure()
						.build();
				sessionFactory = new MetadataSources().buildMetadata(standardServiceRegistry).buildSessionFactory();
			}

		} catch (HibernateError he) {
			System.err.println("error en la inicilizacion de SessionFactory " + he);
			throw new ExceptionInInitializerError(he);
		}
		return sessionFactory;
	}
}

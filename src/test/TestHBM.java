package test;

import org.hibernate.Session;

import dao.HibernateUtil;

public class TestHBM {

	public static void main(String[] args) {
		
/* COMMENT la siguiente instruccion es un ejemplo de como ver
 *  las propiedades de la conexion en tiempo de ejecucion 
 */
		System.out.println(HibernateUtil.getSessionFactory().getProperties().get("hibernate.connection.url"));
		
		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();
		session.close();

		System.out.println("Ok connection");

	}

}

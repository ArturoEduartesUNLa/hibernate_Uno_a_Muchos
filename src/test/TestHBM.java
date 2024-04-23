package test;

import org.hibernate.Session;

import dao.HibernateUtil;

public class TestHBM {

	public static void main(String[] args) {

		System.out.println(HibernateUtil.getSessionFactory().getProperties().get("hibernate.connection.url"));
		
		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();
		session.close();

		System.out.println("Ok connection");

	}

}

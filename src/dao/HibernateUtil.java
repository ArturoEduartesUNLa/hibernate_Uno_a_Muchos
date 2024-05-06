package dao;

import java.util.EnumSet;

/* COMPLETE
 * 
 */

import org.hibernate.HibernateError;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.schema.TargetType;

public class HibernateUtil {
	private static SessionFactory sessionFactory;

	private void HibernateUtil() {
	}

	public static SessionFactory getSessionFactory() {
		try {
			if (sessionFactory == null) {
				StandardServiceRegistry standardServiceRegistry = new StandardServiceRegistryBuilder().configure()
						.build();
				sessionFactory = new MetadataSources().buildMetadata(standardServiceRegistry).buildSessionFactory();
			}

		} catch (HibernateError he) {
			System.err.println("error en la inicializacion de SessionFactory " + he);
			throw new ExceptionInInitializerError(he);
		}
		return sessionFactory;
	}

	public static void exportaFileSchemaSQL() {
		try {
			// si queremos crear la base de datos o un archivo SQL con el esquema de las
			// tablas a utilizar
			MetadataSources metadata = new MetadataSources(new StandardServiceRegistryBuilder().configure().build());
			SchemaExport schemaExport = new SchemaExport();
			schemaExport.setHaltOnError(true);
			schemaExport.setFormat(true);
			schemaExport.setDelimiter(";");
			schemaExport.setOutputFile("db-schema.sql");
			// schemaExport.create(EnumSet.of(TargetType.DATABASE),
			// metadata.buildMetadata());
			schemaExport.execute(EnumSet.of(TargetType.STDOUT), SchemaExport.Action.CREATE, metadata.buildMetadata());

		} catch (HibernateError he) {
			System.err.println("error en la generacion de Scheme of database " + he);
			throw new ExceptionInInitializerError(he);
		}
	}
}

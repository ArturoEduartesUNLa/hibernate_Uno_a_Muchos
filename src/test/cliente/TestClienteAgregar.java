package test.cliente;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import datos.Cliente;
import datos.Prestamo;
import negocio.ClienteABM;

public class TestClienteAgregar {

	/*
	 * COMPLETE
	 * 
	 */
	public static void main(String[] args) {
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);

		boolean printTest = printTest();

		long id;
		/* sin Prestamos */
		try {
			System.out.println("UC 1_1 - agregar cliente sin Prestamo");

			id = ClienteABM.getInstance().agregar("Cliente 1", "Mark", 123456, LocalDate.now(), false, null);
			System.out.println(ClienteABM.getInstance().traer(id));

		} catch (Exception e) {
			e.printStackTrace();

		}

		/* con Prestamos */
		Set<Prestamo> setP = new HashSet<Prestamo>();
		crearPrestamosPrueba(setP);

		Set<Prestamo> setP1 = new HashSet<Prestamo>();
		crearPrestamosPrueba(setP1);

		Set<Prestamo> setP2 = new HashSet<Prestamo>();
		crearPrestamosPrueba(setP2);

		Set<Prestamo> setP3 = new HashSet<Prestamo>();
		crearPrestamosPrueba(setP3);

		try {
			System.out.println("UC 1_2 - agregar cliente con Prestamo");

			id = ClienteABM.getInstance().agregar("Cliente 2", "Mark", 1234564, LocalDate.now(), false, setP);
			Cliente clie = ClienteABM.getInstance().traer(id);
			System.out.println(clie + " " + clie.getPrestamos()); // show output with associated prestamos

		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			System.out.println(ClienteABM.getInstance().traer(
					ClienteABM.getInstance().agregar("Cliente 3", "Mark", 12345648, LocalDate.now(), false, setP1)));
			System.out.println(ClienteABM.getInstance().traer(
					ClienteABM.getInstance().agregar("Cliente 4", "Mark", 12345649, LocalDate.now(), false, setP2)));
			System.out.println(ClienteABM.getInstance().traer(
					ClienteABM.getInstance().agregar("Cliente 5", "Mark", 12345658, LocalDate.now(), false, setP3)));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/* ------ Area de Auxiliary functions ------ */
	private static void crearPrestamosPrueba(Set<Prestamo> sPrestamos) {
		Prestamo p = new Prestamo(LocalDate.now(), 125.00, 25.00, 1, null);
		sPrestamos.add(p);
		sPrestamos.add(new Prestamo(LocalDate.now(), 125.00, 25.00, 1, null));
		sPrestamos.add(new Prestamo(LocalDate.now(), 125.00, 25.00, 1, null));
		sPrestamos.add(new Prestamo(LocalDate.now(), 125.00, 25.00, 1, null));
	}

	private static boolean printTest() {
		boolean result = false;
		try {
			result = TestCIienteInt.class.getDeclaredField("printTest") != null;
		} catch (NoSuchFieldException e) {
		} catch (SecurityException e) {
		}
		return result;
	}
}

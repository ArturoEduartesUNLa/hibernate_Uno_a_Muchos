package test.cliente;

import java.util.logging.Level;

import datos.Cliente;
import negocio.ClienteABM;

public class TestClienteEliminar {

	/*
	 * COMPLETE
	 * 
	 */
	public static void main(String[] args) {
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);

		boolean printTest = printTest();

		// Eliminar Cliente sin prestamos
		System.out.println("UC 3_1 - eliminar Cliente sin Prestamos");

		Cliente modif = ClienteABM.getInstance().traer(1L);
		System.out.println(modif + " " + modif.getPrestamos());
		try {
			ClienteABM.getInstance().eliminar(1L);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Eliminar Cliente con prestamos
		System.out.println("UC 3_2 - eliminar asociacion Cliente con prestamos (set cliente with null in prestamos)");
		modif = ClienteABM.getInstance().traer(3L);
		System.out.println(modif + " " + modif.getPrestamos());
		try {
			modif.getPrestamos().forEach(p -> p.setCliente(null));
			ClienteABM.getInstance().modificar(modif);
			ClienteABM.getInstance().eliminar(3L);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (!printTest) {
			System.out.println("Print all loan");
			ClienteABM.getInstance().traer().forEach(t -> System.out.println(t + " " + t.getPrestamos()));
		}

		// Eliminar prestamos asociados al cliente
		System.out.println("UC 3_3 - eliminar prestamos asociados a Cliente");
		modif = ClienteABM.getInstance().traer(2L);
		System.out.println(modif + " " + modif.getPrestamos());
		try {
			ClienteABM.getInstance().eliminarPrestamosAsociados(2L);
			modif = ClienteABM.getInstance().traer(2L);
			System.out.println(modif + " " + modif.getPrestamos());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Eliminar Cliente Y prestamos asociados
		System.out.println("UC 3_4 - eliminar Cliente junto con sus prestamos");

		modif = ClienteABM.getInstance().traer(3L);

		if (!printTest)
			System.out.println(modif + " " + modif.getPrestamos());
		try {
			boolean resultado = ClienteABM.getInstance().eliminarClienteYPrestamos(3L);
			if (resultado)
				System.out.println("se eliminó: " + modif + " " + modif.getPrestamos());

		} catch (Exception e) {
			e.printStackTrace();
		}

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

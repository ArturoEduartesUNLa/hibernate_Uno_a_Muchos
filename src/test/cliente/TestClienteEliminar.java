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
		Cliente modif = null;

		// Eliminar Cliente sin prestamos
		System.out.println("UC 3_1 - eliminar Cliente sin Prestamos");

		try {
			modif = ClienteABM.getInstance().traer(1L);
			System.out.println(modif);
			ClienteABM.getInstance().eliminar(1L);
		} catch (Exception e) {
			System.err.println("Ejecutar TestClienteInt para previamente crear registros en DB");
			e.printStackTrace();
		}

		/*
		 * Eliminar Cliente con prestamos lanza excepcion por restriccion not-null =
		 * true en mapeo de Prestamo para evitar el error poner en false o eliminar
		 * modificador not-null
		 */
		System.out.println("UC 3_2 - eliminar asociacion Cliente con prestamos (set cliente with null in prestamos)");
		modif = ClienteABM.getInstance().traerClienteYPrestamo(3L);
		System.out.println(modif + " " + modif.getPrestamos());
		try {
			modif.getPrestamos().forEach(p -> p.setCliente(null));
			ClienteABM.getInstance().modificar(modif);
			ClienteABM.getInstance().eliminar(modif.getIdCliente());
		} catch (Exception e) {
			e.printStackTrace();
		}

		/*
		 * Eliminar Cliente y prestamos de forma directa al eliminar un cliente
		 * modificador on-delete=cascade en mapeo del atributo del tipo SET en Cliente
		 */
		System.out.println("UC 3_3 - eliminar Cliente y prestamos de forma directa al eliminar un cliente\n"
				+ "		   modificador en mapeo cliente con on-delete=cascade");
		try {
			System.out.println(modif);
			ClienteABM.getInstance().eliminar(modif.getIdCliente());
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (!printTest) {
			System.out.println("Print all loan");
			ClienteABM.getInstance().traer().forEach(t -> System.out.println(t + " " + t.getPrestamos()));
		}

		// Eliminar prestamos asociados al cliente
		System.out.println("UC 3_4 - eliminar prestamos asociados a Cliente");
		modif = ClienteABM.getInstance().traerClienteYPrestamo(6L);
		System.out.println(modif + " " + modif.getPrestamos());
		try {
			ClienteABM.getInstance().eliminarPrestamosAsociados(6L);
			modif = ClienteABM.getInstance().traerClienteYPrestamo(6L);
			System.out.println(modif + " " + modif.getPrestamos());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Eliminar Cliente Y prestamos asociados
		System.out.println("UC 3_5 - eliminar Cliente junto con sus prestamos");

		modif = ClienteABM.getInstance().traerClienteYPrestamo(4L);

		if (!printTest)
			System.out.println(modif + " " + modif.getPrestamos());
		try {
			boolean resultado = ClienteABM.getInstance().eliminarClienteYPrestamos(modif.getIdCliente());
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

package test.cliente;

import java.util.Set;
import java.util.logging.Level;

import datos.Cliente;
import datos.Prestamo;
import negocio.ClienteABM;
import negocio.PrestamoABM;

public class TestClienteModificar {

	/*
	 * COMPLETE
	 * 
	 */
	public static void main(String[] args) {
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);

		boolean printTest = printTest();
		Cliente modif = null;

		System.out.println("UC 2_1 -modificar apellido Cliente");

		System.out.println(ClienteABM.getInstance().traer(2L)); // mostrar clientes

		try {
			modif = ClienteABM.getInstance().traer(2L);
			if (modif != null) {
				modif.setApellido("Lewis Carroll");
			}
			ClienteABM.getInstance().modificar(modif);
			System.out.println(ClienteABM.getInstance().traer(2L));
		} catch (Exception e) {
			System.err.println("Ejecutar TestClienteInt para previamente crear registros en DB");
			e.printStackTrace();
		}

		// modificar un prestamo
		System.out.println("UC 2_2 - modificar Prestamo si existe en Cliente");
		System.out.println(ClienteABM.getInstance().traerClienteYPrestamo(modif.getIdCliente()).getPrestamos());

		// consultar si este cliente contiene este prestamo
		Prestamo p = PrestamoABM.getInstance().traer(1L);
		Set<Prestamo> s = ClienteABM.getInstance().traerClienteYPrestamo(modif.getIdCliente()).getPrestamos();

		if (s.contains(p)) // si existe prestamo en cliente
		{
			s.remove(p);
			p.setCantCuotas(10); // modificar cuotas
			System.out.println("Existe prestamo, se modifican cuotas: " + p);
			s.add(p);
			modif.setPrestamos(s);
		}

		// modificar en BD
		try {
			ClienteABM.getInstance().modificar(modif);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (!printTest)
			ClienteABM.getInstance().traer().forEach(t -> System.out.println(t + " " + t.getPrestamos()));
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

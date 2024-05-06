package test.cliente;

import java.util.Set;
import java.util.logging.Level;

import datos.Cliente;
import datos.Prestamo;
import negocio.ClienteABM;
import negocio.PrestamoABM;

public class TestClienteModificar {

	public static void main(String[] args) {
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);

		System.out.println(" UC 2 -modificar datos Cliente");

		System.out.println(ClienteABM.getInstance().traer()); // mostrar clientes

		Cliente modif = ClienteABM.getInstance().traer(2L);

		if (modif != null) {
			modif.setApellido("Carlton");
		}

		try {
			ClienteABM.getInstance().modificar(modif);
			System.out.println(ClienteABM.getInstance().traer());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// modificar un prestamo
		System.out.println(ClienteABM.getInstance().traer(modif.getIdCliente()).getPrestamos());

		// consultar si este cliente contiene este prestamo
		Prestamo p = PrestamoABM.getInstance().traer(1L);
		Set<Prestamo> s = ClienteABM.getInstance().traer(modif.getIdCliente()).getPrestamos();
		
		if (s.contains(p)) // si existe prestamo en cliente
		{
			s.remove(p);
			p.setCantCuotas(10); // modificar cuotas
			s.add(p);
			modif.setPrestamos(s);
		}

		// modificar en BD
		try {
			ClienteABM.getInstance().modificar(modif);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ClienteABM.getInstance().traer().forEach(t -> System.out.println(t + " " + t.getPrestamos()));
	}
}

package test.prestamo;

import datos.Cliente;
import datos.Prestamo;
import negocio.ClienteABM;
import negocio.PrestamoABM;
import test.cliente.TestCIienteInt;

public class TestPrestamoList {

	public static void main(String[] args) {
		// para ejecutar los test necesitamos tener prestamos en la BD
		TestCIienteInt.main(args);

		System.out.println("UC 1_1 listar todos los prestamos");
		System.out.println(PrestamoABM.getInstance().traer());

		System.out.println("UC 1_2 traer un prestamo por id");
		System.out.println(PrestamoABM.getInstance().traer(1));

		System.out.println("UC 1_2 traer un prestamo y mostrar el cliente asociado - bidireccional");
		Prestamo prestamo = PrestamoABM.getInstance().traer(1);
		System.out.println("prestamo: " + prestamo + " pertenece a: " + prestamo.getCliente());

		System.out.println("UC 1_3 traer todos los prestamos de un cliente ");
		Cliente clie = ClienteABM.getInstance().traer(5L);
		System.out.println(clie + " posee los siguientes prestamos:\n " + PrestamoABM.getInstance().traer(clie));

		System.out.println("UC 1_4 traer todos los prestamos y cliente asociado - bidireccional");
		PrestamoABM.getInstance().traer().forEach(p -> System.out.println(p + " " + p.getCliente()));

	}

}

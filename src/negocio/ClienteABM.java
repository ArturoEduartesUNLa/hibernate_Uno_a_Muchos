package negocio;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import dao.ClienteDao;
import datos.Cliente;
import datos.Prestamo;

public class ClienteABM {
	private static ClienteABM clienteABM;
	private static ClienteDao dao;

	private ClienteABM() {

	}

	public static ClienteABM getInstance() {
		getInstanceDao();
		if (clienteABM == null) {
			clienteABM = new ClienteABM();
		}
		return clienteABM;
	}

	private static void getInstanceDao() {
		if (dao == null)
			dao = new ClienteDao();
	}

	public Cliente traer(int dni) {
		return dao.traer(dni);
	}

	public Cliente traer(long idCliente) {
		return dao.traer(idCliente);
	}

	public List<Cliente> traer() {
		return dao.traer();
	}

	public long agregar(String apellido, String nombre, int dni, LocalDate fechaDeNacimiento, boolean baja,
			Set<Prestamo> prestamos) throws Exception {

		if (traer(dni) != null) {
			throw new Exception("Existe DNI: " + dni);
		}

		return dao.agregar(new Cliente(apellido, nombre, dni, fechaDeNacimiento, baja, prestamos));
	}

	public void eliminar(long idCliente) throws Exception {
		Cliente c = null;
		if ((c = traer(idCliente)) == null) {
			throw new Exception("Id no existe en BD: " + idCliente);
		}
		dao.eliminar(c);
	}

	public void eliminarPrestamosAsociados(long idCliente) throws Exception {
		Cliente c = traer(idCliente);
		if (c == null) {
			throw new Exception("Id no existe en BD: " + idCliente);
		}

		// si existen eliminar los prestamos asociados
		if (c.getPrestamos() != null) // poner en null cliente en prestamos
			c.getPrestamos().forEach(t -> {
				t.setCliente(null);
				try {
					PrestamoABM.getInstance().eliminar(t.getIdPrestamo());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		c.setPrestamos(null);
		dao.actualizar(c);
	}

	public boolean eliminarClienteYPrestamos(long idCliente) throws Exception {
		boolean resultado = false;
		Cliente c = traer(idCliente);
		if (c == null) {
			throw new Exception("Id no existe en BD: " + idCliente);
		}

		// si existen eliminar los prestamos asociados
		c.getPrestamos().clear();
		eliminar(idCliente);

		if (traer(idCliente) == null)
			resultado = true;

		return resultado;
	}

	public void modificar(Cliente c) throws Exception {
		if (c == null)
			throw new Exception("Cliente = null");

		// si se modifica DNI verificar que no exista otro en la base de datos
		if (traer(c.getDni()) != null && traer(c.getIdCliente()).getDni() != c.getDni()) {
			throw new Exception("existe DNI; " + c.getDni());
		}
		dao.actualizar(c);
	}
}

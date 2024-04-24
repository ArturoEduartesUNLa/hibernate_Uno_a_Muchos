package negocio;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import dao.ClienteDao;
import datos.Cliente;
import datos.Prestamo;

public class ClienteABM {
	ClienteDao dao = new ClienteDao();

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

	public void modificar(Cliente c) throws Exception {
		// si se modifica DNI verificar que no exista otro en la base de datos
		if (traer(c.getDni()) != null && traer(c.getIdCliente()).getDni() != c.getDni()) {
			throw new Exception("existe DNI; " + c.getDni());
		}
		dao.actualizar(c);
	}
}

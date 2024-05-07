package negocio;

import java.util.List;

import dao.PrestamoDao;
import datos.Cliente;
import datos.Prestamo;

public class PrestamoABM {

	private static PrestamoDao dao;
	private static PrestamoABM prestamoABM;

	private PrestamoABM() {

	}

	public static PrestamoABM getInstance() {

		getDaoInstance();
		if (prestamoABM == null) {
			prestamoABM = new PrestamoABM();
		}
		return prestamoABM;

	}

	private static void getDaoInstance() {
		if (dao == null)
			dao = new PrestamoDao();
	}

	public Prestamo traer(long idPrestamo) {
		return dao.traer(idPrestamo);
	}

	public List<Prestamo> traer(Cliente c) {
		return dao.traer(c);
	}

	public void eliminar(long idPrestamo) throws Exception {
		Prestamo p = null;
		if ((p = traer(idPrestamo)) == null) {
			throw new Exception("Id no existe en BD: " + idPrestamo);
		}

		dao.eliminar(p);
	}
}

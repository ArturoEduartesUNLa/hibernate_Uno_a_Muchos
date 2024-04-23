package datos;

import java.time.LocalDate;
import java.util.Set;

public class Cliente {
	private long idCliente;
	private String aspellido;
	private String nombre;
	private long dni;
	private LocalDate fechaDeNacimiento;
	private boolean baja;
	private Set<Prestamo> prestamos;
}

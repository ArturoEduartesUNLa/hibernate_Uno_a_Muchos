package datos;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

public class Cliente {
	private long idCliente;
	private String apellido;
	private String nombre;
	private int dni;
	private LocalDate fechaDeNacimiento;
	private boolean baja;

	private Set<Prestamo> prestamos;

	public Cliente(String apellido, String nombre, int dni, LocalDate fechaDeNacimiento, boolean baja,
			Set<Prestamo> prestamos) {
		super();
		this.apellido = apellido;
		this.nombre = nombre;
		this.dni = dni;
		this.fechaDeNacimiento = fechaDeNacimiento;
		this.baja = baja;

		this.prestamos = prestamos;
	}

	public Cliente() {
		super();
	}

	public long getIdCliente() {
		return idCliente;
	}

	private void setIdCliente(long idCliente) {
		this.idCliente = idCliente;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public LocalDate getFechaDeNacimiento() {
		return fechaDeNacimiento;
	}

	public void setFechaDeNacimiento(LocalDate fechaDeNacimiento) {
		this.fechaDeNacimiento = fechaDeNacimiento;
	}

	public boolean isBaja() {
		return baja;
	}

	public void setBaja(boolean baja) {
		this.baja = baja;
	}

	public Set<Prestamo> getPrestamos() {
		return prestamos;
	}

	public void setPrestamos(Set<Prestamo> prestamos) {
		// forma alternativa prestamos.forEach(prestamo -> prestamo.setCliente(this));
		this.prestamos = prestamos;
	}

	@Override
	public int hashCode() {
		return Objects.hash(apellido, baja, dni, fechaDeNacimiento, idCliente, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Cliente))
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(apellido, other.apellido) && baja == other.baja && dni == other.dni
				&& Objects.equals(fechaDeNacimiento, other.fechaDeNacimiento) && idCliente == other.idCliente
				&& Objects.equals(nombre, other.nombre);
	}

	@Override
	public String toString() {
		return "Cliente [idCliente=" + idCliente + ", apellido=" + apellido + ", nombre=" + nombre + ", dni=" + dni
				+ ", fechaDeNacimiento=" + fechaDeNacimiento + ", baja=" + baja + "]\n"; // "\n prestamos=" + prestamos
																							// + "]\n";
	}

}

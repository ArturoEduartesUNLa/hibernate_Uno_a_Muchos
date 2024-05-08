package datos;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class Prestamo {
	private long idPrestamo;
	private LocalDate fecha;
	private double monto;
	private double interes;
	private int cantCuotas;
	private Cliente cliente;
	UUID uuid;

	public Prestamo(LocalDate fecha, double monto, double interes, int cantCuotas, Cliente cliente) {
		super();
		this.fecha = fecha;
		this.monto = monto;
		this.interes = interes;
		this.cantCuotas = cantCuotas;
		this.cliente = cliente;
		uuid = UUID.randomUUID();
	}

	public Prestamo() {
		super();
	}

	public long getIdPrestamo() {
		return idPrestamo;
	}

	protected void setIdPrestamo(long idPrestamo) {
		this.idPrestamo = idPrestamo;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public double getInteres() {
		return interes;
	}

	public void setInteres(double interes) {
		this.interes = interes;
	}

	public int getCantCuotas() {
		return cantCuotas;
	}

	public void setCantCuotas(int cantCuotas) {
		this.cantCuotas = cantCuotas;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cantCuotas, fecha, uuid, idPrestamo, interes, monto);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Prestamo))
			return false;
		Prestamo other = (Prestamo) obj;
		return cantCuotas == other.cantCuotas && Objects.equals(cliente, other.cliente)
				&& Objects.equals(fecha, other.fecha) && idPrestamo == other.idPrestamo
				&& Double.doubleToLongBits(interes) == Double.doubleToLongBits(other.interes)
				&& Double.doubleToLongBits(monto) == Double.doubleToLongBits(other.monto);
	}

	@Override
	public String toString() {
		return "Prestamo [idPrestamo=" + idPrestamo + ", fecha=" + fecha + ", monto=" + monto + ", interes=" + interes
				+ ", cantCuotas=" + cantCuotas + "]\n";
	}

}

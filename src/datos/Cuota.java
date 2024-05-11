package datos;

import java.time.LocalDate;
import java.util.Objects;

public class Cuota {
	private int idCuota;
	private int nroCuota;
	private LocalDate fechaVencimiento;
	private double saldoPendiente;
	private double amortizacion;
	private double interesCuota;
	private double cuota;
	private double deuda;
	private boolean cancelada;
	private LocalDate fechaDePago;
	private double punitorios;
	
	
	// constructors --------------------
	public Cuota() {
		super();
	}

	
	
	public Cuota(int nroCuota, LocalDate fechaVencimiento, double saldoPendiente, double amortizacion,
			double interesCuota, double cuota, double deuda, boolean cancelada, LocalDate fechaDePago,
			double punitorios) {
		super();
		this.nroCuota = nroCuota;
		this.fechaVencimiento = fechaVencimiento;
		this.saldoPendiente = saldoPendiente;
		this.amortizacion = amortizacion;
		this.interesCuota = interesCuota;
		this.cuota = cuota;
		this.deuda = deuda;
		this.cancelada = cancelada;
		this.fechaDePago = fechaDePago;
		this.punitorios = punitorios;
	}



	// gettesrs and setters ----------------
	public int getIdCuota() {
		return idCuota;
	}
	public void setIdCuota(int idCuota) {
		this.idCuota = idCuota;
	}
	public int getNroCuota() {
		return nroCuota;
	}
	public void setNroCuota(int nroCuota) {
		this.nroCuota = nroCuota;
	}
	public LocalDate getFechaVencimiento() {
		return fechaVencimiento;
	}
	public void setFechaVencimiento(LocalDate fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	public double getSaldoPendiente() {
		return saldoPendiente;
	}
	public void setSaldoPendiente(double saldoPendiente) {
		this.saldoPendiente = saldoPendiente;
	}
	public double getAmortizacion() {
		return amortizacion;
	}
	public void setAmortizacion(double amortizacion) {
		this.amortizacion = amortizacion;
	}
	public double getInteresCuota() {
		return interesCuota;
	}
	public void setInteresCuota(double interesCuota) {
		this.interesCuota = interesCuota;
	}
	public double getCuota() {
		return cuota;
	}
	public void setCuota(double cuota) {
		this.cuota = cuota;
	}
	public double getDeuda() {
		return deuda;
	}
	public void setDeuda(double deuda) {
		this.deuda = deuda;
	}
	public boolean isCancelada() {
		return cancelada;
	}
	public void setCancelada(boolean cancelada) {
		this.cancelada = cancelada;
	}
	public LocalDate getFechaDePago() {
		return fechaDePago;
	}
	public void setFechaDePago(LocalDate fechaDePago) {
		this.fechaDePago = fechaDePago;
	}
	public double getPunitorios() {
		return punitorios;
	}
	public void setPunitorios(double punitorios) {
		this.punitorios = punitorios;
	}



	@Override
	public int hashCode() {
		return Objects.hash(amortizacion, cancelada, cuota, deuda, fechaDePago, fechaVencimiento, interesCuota,
				nroCuota, punitorios, saldoPendiente);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Cuota))
			return false;
		Cuota other = (Cuota) obj;
		return Double.doubleToLongBits(amortizacion) == Double.doubleToLongBits(other.amortizacion)
				&& cancelada == other.cancelada
				&& Double.doubleToLongBits(cuota) == Double.doubleToLongBits(other.cuota)
				&& Double.doubleToLongBits(deuda) == Double.doubleToLongBits(other.deuda)
				&& Objects.equals(fechaDePago, other.fechaDePago)
				&& Objects.equals(fechaVencimiento, other.fechaVencimiento)
				&& Double.doubleToLongBits(interesCuota) == Double.doubleToLongBits(other.interesCuota)
				&& nroCuota == other.nroCuota
				&& Double.doubleToLongBits(punitorios) == Double.doubleToLongBits(other.punitorios)
				&& Double.doubleToLongBits(saldoPendiente) == Double.doubleToLongBits(other.saldoPendiente);
	}



	@Override
	public String toString() {
		return "Cuota [nroCuota=" + nroCuota + ", fechaVencimiento=" + fechaVencimiento + ", saldoPendiente="
				+ saldoPendiente + ", amortizacion=" + amortizacion + ", interesCuota=" + interesCuota + ", cuota="
				+ cuota + ", deuda=" + deuda + ", cancelada=" + cancelada + ", fechaDePago=" + fechaDePago
				+ ", punitorios=" + punitorios + "]\n";
	}
	
	
	
}

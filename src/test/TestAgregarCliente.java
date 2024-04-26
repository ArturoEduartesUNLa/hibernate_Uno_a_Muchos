package test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import datos.Prestamo;
import negocio.ClienteABM;

public class TestAgregarCliente {

	public static void main(String[] args) {
         System.out.println("UC 1 - agregar cliente");
         
         // sin Prestamos
         try {
        	//long id;// = new ClienteABM().traer(1L).getIdCliente();
        	
        	//id = new ClienteABM().agregar("Mark","Zuckerber",123456,LocalDate.now(), false, null);
        	 
        	 //System.out.println("Cliente id: " + id);
			System.out.println(new ClienteABM().traer(new ClienteABM().agregar("Mark","Zuckerber",123456,LocalDate.now(), false, null)));
		} catch (Exception e) {
			e.printStackTrace();
			
		}
         // con Prestamos
         Set<Prestamo> setP = new HashSet<Prestamo>();
         Prestamo p = new Prestamo(LocalDate.now(), 125.00,25.00,3, null);
         setP.add(p);
         
         try {
			System.out.println(new ClienteABM().traer(new ClienteABM().agregar("Mark","Zuckerber",1234564 + LocalTime.now().getSecond(),LocalDate.now(), false, setP)));
		} catch (Exception e) {
			e.printStackTrace();
		}
         
	}
}

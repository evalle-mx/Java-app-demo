package netto.app.progfuncional.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import netto.app.common.dto.Factura;

public class ProcesaFactura {

	public static void main(String[] args) {
		Factura f = new Factura("ordenador", 1000);
		Factura f2 = new Factura("movil", 300);
		Factura f3 = new Factura("impresora", 200);
		Factura f4 = new Factura("imac", 1500);
		List<Factura> lista = Arrays.asList(f,f2,f3,f4 );
		
		/* 1) Stream
		Factura facturaFiltro = lista.stream()
					.filter( elem -> elem.getImporte() > 300)
					.findFirst()
					.get();		
		System.out.println(facturaFiltro.getImporte());//*/
		
		/* : busca el primer elemento cuyo importe supere los 300 y retórnalo. 
		 *  Una vez encontrado no es necesario recorrer el resto de la lista (es un error hacerlo). 
		 *  Los streams simplemente ejecutan el flujo de trabajo para el 1er elemento y como este cumple los requisitos el flujo termina , */
		
		//* 2) Stream + Predicate
		Predicate<Factura> predicado = new Predicate<Factura>() {
			@Override
			public boolean test(Factura f) {
				System.out.println(" iteracion ");
				return f.getImporte() > 300;
			}
		};
		
		Factura facturaFiltro = lista.stream()
				.filter( predicado )
				.findFirst()
				.get();		
		System.out.println("UNICA: " + facturaFiltro.getImporte()); //*/
	}
}

package netto.app.progfuncional;

import java.util.ArrayList;

public class CalculaGastos {

static ArrayList<Gasto> lista;
	
	public static void main(String[] args) {
		calculaGasto();
	}
	
	protected static void calculaGasto() {
		double totalPago = 0;
		lista = new ArrayList<CalculaGastos.Gasto>();
		lista.add( new Gasto("A", 80) );
		lista.add( new Gasto("B", 50) );
		lista.add( new Gasto("C", 70) );
		lista.add( new Gasto("D", 95) );
		
		/*/Imperativa
		for(Gasto g: lista ) {
			if(g.getCantidad()*1.21 <= 100) {
				totalPago += g.getCantidad()*1.21;
			}
		} //*/
		
		//Declarativa
		totalPago = lista.stream()
				.mapToDouble( g -> g.getCantidad()*1.21 )
				.filter( g -> g<100 )
				.sum();
		
		System.out.println("Total pago: "+ totalPago );
	}
	
	
	static class Gasto {
		String concepto;
		int cantidad;
		
		public Gasto() {
		}
		
		public Gasto(String concepto, int cantidad) {
			this.concepto = concepto;
			this.cantidad = cantidad;
		}
		
		public String getConcepto() {
			return concepto;
		}
		public void setConcepto(String concepto) {
			this.concepto = concepto;
		}
		public int getCantidad() {
			return cantidad;
		}
		public void setCantidad(int cantidad) {
			this.cantidad = cantidad;
		}
		
	}
}

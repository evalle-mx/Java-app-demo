package netto.app.progfuncional.lambda;
/* https://www.arquitecturajava.com/java-8-lambda-expressions/ */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import netto.app.common.dto.Persona;

public class OrderLists {
	
	public static void main(String[] args) {
//		orderPersona();
		orderPersonaLambda();
	}

	public static void orderPersona() {
		ArrayList<Persona> milista= new ArrayList<Persona>();
		 milista.add(new Persona("Miguel"));
		 milista.add(new Persona("Saul"));
		 milista.add(new Persona("Alicia"));
		 
		 Collections.sort(milista,
				 new Comparator<Persona>() {//Usando un Comparator
			 public int compare(Persona p1,Persona p2) {
				 return p1.getNombre().compareTo(p2.getNombre());
			  }
			 });
		 for (Persona p: milista) {
			 System.out.println(p.getNombre());
		  }
	}
	
	public static void orderPersonaLambda() {
		ArrayList<Persona> milista= new ArrayList<Persona>();
		 milista.add(new Persona("Miguel"));
		 milista.add(new Persona("Saul"));
		 milista.add(new Persona("Alicia"));
		 Collections.sort(milista,
				 (Persona p1,Persona p2)-> p1.getNombre().compareTo(p2.getNombre())
				 );
		 for (Persona p: milista) {
			 System.out.println(p.getNombre());
		  }
	}
	
}

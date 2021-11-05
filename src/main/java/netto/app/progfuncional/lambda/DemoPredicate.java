package netto.app.progfuncional.lambda;
/* https://www.arquitecturajava.com/utilizando-java-8-predicate/
 * https://www.arquitecturajava.com/java-predicate-interface-y-sus-metodos/ 
 * */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

import netto.app.common.dto.Persona;

public class DemoPredicate {

	static List<Persona> lista;
	
	public static void main(String[] args) {
		setPersonas();
//		traverList();
		filterList();
	}
	
	
	private static void setPersonas() {
		lista= new ArrayList<Persona>();
        
        Persona p1= new Persona ("pepe","perez",20);
        Persona p2= new Persona ("ana","perez",30);
        Persona p3= new Persona ("gema","sanchez",18);
        Persona p4= new Persona ("pedro","gomez",70);
        Persona p5= new Persona ("pepe","blanco",15);
        Persona p6= new Persona ("Diana","Fabila",29);
         
//        lista.add(p1);
//        lista.add(p2);
//        lista.add(p3);
//        lista.add(p4);
        lista = Arrays.asList(p1,p2,p3,p4,p5,p6  );
	}
	
	/**
	 * Simple demo about Predicate using to traverse a List
	 */
	protected static void traverList() {
		
		//Using stream+forEach+Consumer
		lista.stream()
		.forEach(
				new Consumer<Persona>() {
					public void accept(Persona p) {
						System.out.println(p.getNombre());
					};
				});
		
		
		//Creating Predicate for filter by Pepe
		Predicate<Persona> predNombre = new Predicate<Persona>() {
			@Override
			public boolean test(Persona p) {
				return p.getNombre().equals("pepe");
			}
		};
		
		lista.stream()
		.filter( predNombre )
		.forEach( p-> System.out.println(p.getNombre()+" "+p.getApellidos()) );
		
		//THe same using Lambda expression
		lista.stream()
		 .filter( p -> p.getNombre().equals("pepe") )
		 .forEach( p -> System.out.println(p.getNombre()+" "+p.getApellidos()) ); //.forEach( p -> {System.out.println(p.getNombre()+" "+p.getApellidos());} );
		
		
	}
	
	
	/* Complex/Combinated Predicates */
	static Predicate<Persona> predOver18 = p -> p.getEdad()>18;
	static Predicate<Persona> predLess30 = p -> p.getEdad()<30;
	static Predicate<Persona> predJunior = predOver18.and(predLess30); //Combines two (AND)
	
	static Predicate<Persona> predSenior = p -> p.getEdad() > 65;
	static Predicate<Persona> predJuniorOrSenior = predJunior.or(predSenior); //Meets one of two conditions (OR)
	
	static Predicate<Persona> predRest = predJunior.or(predSenior).negate();
	
	protected static void filterList() {
		// Print only Junior (18+ -> -30)
		System.out.println(" Junior: ");
		lista.stream()
			.filter(predJunior )
			.forEach(
					p -> System.out.println(p.getNombre()+" "+p.getApellidos() + ", " + p.getEdad() )
					);
		
		// Print only Junior (18+ -> -30) and Senior (65+)
		System.out.println("\n Junior and Senior: ");
		lista.stream()
			.filter( predJuniorOrSenior )
			.forEach( p-> System.out.println(p.getNombre()+" "+p.getApellidos() + ", " + p.getEdad() ) );
		
		System.out.println("\n Rest: ");
		lista.stream()
			.filter( predJuniorOrSenior.negate()) // predRest | predJuniorOrSenior.negate()
			.forEach( p-> System.out.println(p.getNombre()+" "+p.getApellidos() + ", " + p.getEdad() ) );
		
	}
	
	
	
	
}

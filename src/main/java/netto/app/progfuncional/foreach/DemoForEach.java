package netto.app.progfuncional.foreach;
/* https://www.arquitecturajava.com/java-stream-foreach-y-colecciones/ */
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import netto.app.common.dto.Persona;

public class DemoForEach {
	
	static Persona p1 = new Persona("juan", "sanchez", 20);
	static Persona p2 = new Persona("ana", "gomez", 12);
	static Persona p3 = new Persona("pedro", "gutierrez", 40);
	
	public static void main(String[] args) {
		printList();
//		printMap();
	}
	
	protected static void printMap() {
		Map < String, Persona > mapa = new HashMap < String, Persona > ();
		mapa.put(p1.getNombre(), p1);
		mapa.put(p2.getNombre(), p2);
		mapa.put(p3.getNombre(), p3);
		
		//Clasico
		for (String nombre: mapa.keySet()) {
			Persona pm=mapa.get(nombre);

//			System.out.println(pm.getNombre()); System.out.println(pm.getApellidos()); System.out.println(pm.getEdad());
			System.out.println(pm.getNombre() + " " + pm.getApellidos() + ", "+ pm.getEdad() + " anios ");
		}
		
		//For each + lambda
		mapa.forEach((k,v)->{
			//System.out.println(v.getNombre()); System.out.println(v.getApellidos()); System.out.println(v.getEdad());
			System.out.println(v.getNombre() + " " + v.getApellidos() + ", "+ v.getEdad() + " anios ");
			});
		
	}
	
	protected static void printList() {
		List<Persona> lista = Arrays.asList(p1,p2,p3);
		
		//Clasico
		for(Persona p : lista) {
			System.out.println(p.getNombre() + " " + p.getApellidos() + ", "+ p.getEdad() + " anios ");
			//System.out.println(p.getNombre()); System.out.println(p.getApellidos()); System.out.println(p.getEdad());
		}
		
		//Stream + Lambda
		lista.stream().forEach((p) -> {
			System.out.println(p.getNombre() + " " + p.getApellidos() + ", "+ p.getEdad() + " anios ");
		});
		
		//FOrEach + Consumer
		lista.forEach( new Consumer <Persona> () {

			@Override
			public void accept(final Persona p) {
				// TODO Auto-generated method stub
				System.out.println(p.getNombre() + " " + p.getApellidos() + ", "+ p.getEdad() + " anios ");
			}
			
		}  );
		//ForEach + Consumer + lambda
		lista.forEach((final Persona p) -> { System.out.println(p.getNombre() + " " + p.getApellidos() + ", "+ p.getEdad() + " anios "); } );
		
	}
	
	
	

}

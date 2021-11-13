package netto.app.progfuncional;
	
import java.util.ArrayList;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.StringJoiner;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import netto.app.common.dto.Persona;

public class ProcesaPersonas {

	static List<Persona> lista;
	
	public static void main(String[] args) {
		setLista();
//		setListaRandom();
		
		demoCollect();
//		reduceDemo();
	}
	
	/**
	 * Muestra el funcionamiento de Collect para procesar, ordenar o agrupar 
	 */
	protected static void demoCollect() {
		//Filtrado por nombre con P inicial
		Set<Persona> pNamed = lista.stream()
				.filter( p -> p.getNombre().toLowerCase().startsWith("p") )
				.collect(Collectors.toSet());
		pNamed.stream().forEach( Persona::displayInfo );
		
		//agrupa personas por edad [Collect]
//		Function<Persona, > ageFn = null; 
		Map<Integer, List<Persona>> peopleByAge = lista.stream()
				.collect( Collectors.groupingBy( p -> p.getEdad()) ) ;
				
		peopleByAge.forEach( (edad, listElem) -> System.out.format("con %s: %s personas \n", edad, listElem.size() ) );
		
		//transform the stream elements into a map
		Map<Integer, String> map = lista.stream()
				.collect(Collectors.toMap(
					p -> p.getEdad(),  //key
					p -> p.getNombre(), //value
					(name1, name2) -> name1 + "&" + name2));

		System.out.println(map);
		
		// calcula promedio de edad
		Double averageAge = lista.stream()
				.collect(
						Collectors.averagingInt(p -> p.getEdad())
						);
		System.out.println("edad promedio: " + String.format("%.2f", averageAge) );
		
		//Estadisticas (Usando objecto de estadisticas)
		IntSummaryStatistics ageSummary =
			    lista.stream()
			        .collect(
			        		Collectors.summarizingInt(p -> p.getEdad()));		
			//System.out.println( ageSummary.get ); //getCount(), getSum(), getMin(), getAverage(), getMax()
		System.out.println(ageSummary ); // IntSummaryStatistics{count=?, sum=??, min=?, average=?.?, max=?}
		
		
		// Filtra por predicado (rango de edad) obtiene el nombre y concatena elementos
		Predicate<Persona> predRangoEdad = p -> (p.getEdad() > 29 && p.getEdad() < 41);
		String frase = lista.stream()
			    .filter(predRangoEdad )
			    .map(p -> p.getNombreCompleto())
			    .collect(Collectors.joining(
			    		", ",  //Separador (entre elementos) 
			    		"In Germany ", //Prefijo (antes)
			    		" are Middle age.")); //Subfijo (despues)

		System.out.println(frase); // In Germany Max and Peter and Pamela are of legal age.
		
		/* Collector personalizado: Tenemos que pasar 4 ingredientes: proveedor, acumulador, combinador y finalizador. 
		 * Since strings in Java are immutable, we need a helper class like StringJoiner to let the collector construct our string. 
		 * The supplier initially constructs such a StringJoiner with the appropriate delimiter. 
		 * The accumulator is used to add each persons upper-cased name to the StringJoiner. 
		 * The combiner knows how to merge two StringJoiners into one.
		 * In the last step the finisher constructs the desired String from the StringJoiner.*/
		Collector<Persona, StringJoiner, String> personNameCollector =
			    Collector.of(
			        () -> new StringJoiner(" | ", "Los nombres ", " integran esta lista"),          // supplier
			        (j, p) -> j.add(p.getNombre().toUpperCase()),  // accumulator
			        (j1, j2) -> j1.merge(j2),               // combiner
			        StringJoiner::toString);                // finisher
		
		String nombres = lista.stream()
			    .collect(personNameCollector);
		System.out.println(nombres);  // MAX | PETER | PAMELA | DAVID
		
	}
	
	
	protected static void reduceDemo() {
		
		/* Obtiene la persona con mayor edad */
		lista.stream()
	    	.reduce((p1, p2) -> p1.getEdad() > p2.getEdad() ? p1 : p2)
	    	.ifPresent(System.out::println);    // Pamela
		/* Obtiene la persona con menor edad */
		lista.stream()
    	.reduce((p1, p2) -> p1.getEdad() < p2.getEdad() ? p1 : p2)
    	.ifPresent(System.out::println);    // Pamela
		
		
		/* Genera un objeto persona con el acumulado de cada atributo en las personas de la lista */
		Persona result =
			    lista.stream()
			        .reduce(new Persona("","", 0), (p1, p2) -> {
			            p1.setEdad( p1.getEdad() + p2.getEdad() )  ;	//p1.age += p2.age;
			            p1.setNombre( p1.getNombre() + (p1.getNombre().isEmpty()?"":"+")+ p2.getNombre() );	//p1.name += p2.name;
			            return p1;
			        });

		System.out.format("names= %s; TotalAge= %s \n", result.getNombre(), result.getEdad());
			// name=MaxPeterPamelaDavid; age=76
		
		/* Suma las edades de los integrantes de la lista */
		Integer ageSum = lista.stream()
			    .reduce(0, (sum, p) -> 
			    		sum += p.getEdad(), 
			    		(sum1, sum2) 
			    			-> sum1 + sum2);
		
		//Explicado
//		Integer ageSum = lista.stream()
//			    .reduce(0,
//			        (sum, p) -> {
//			            System.out.format("accumulator: sum=%s; person=%s\n", sum, p);
//			            return sum += p.getEdad();
//			        },
//			        (sum1, sum2) -> {
//			            System.out.format("combiner: sum1=%s; sum2=%s\n", sum1, sum2);
//			            return sum1 + sum2;
//			        });
		System.out.println("ageSum: " + ageSum);  // 176
		
		
	}
	
	private static void setLista() {
		Persona p1 = new Persona( "Luis", "Suarez", 35 );
		Persona p2 = new Persona( "Pedro", "Rodriguez", 45 );
		Persona p3 = new Persona( "Samantha", "Smith", 35 );
		Persona p4 = new Persona( "Laura", "Garcia", 45 );
		Persona p5 = new Persona( "Javier", "Alvarez", 25 );
		Persona p6 = new Persona( "Ana", "Layevska", 40 );
		Persona p7 = new Persona( "Pamela","Cerdeira",40);
		lista = Arrays.asList( p1,p2,p3,p4,p5,p6,p7 );
	}
	
	private static void setListaRandom() {
		String names[] = "Luis,Pamela,Pedro,Miguel,Nestor,Victor".split("," ); //6
		List<String> lastNames = Arrays.asList("Suarez", "Perez", "Galvan", "Herrera","Gomez", "Salazar","Smith", "Anderson"); //8
		
		lista = new ArrayList<Persona>();
		Random rdm = new Random();
//		for (int i = 0; i< 5;i++) {
//			lista.add( new Persona( names[rdm.nextInt(names.length)], lastNames.get( rdm.nextInt(lastNames.size())), (rdm.nextInt(20)+10) ) );
//		}
		
		//Usando Stream para reemplazar el loop 
		IntStream 
	    	.range(1, 5)
	    	.forEach(i -> lista.add(
	    			new Persona( names[rdm.nextInt(names.length)], lastNames.get( rdm.nextInt(lastNames.size())), (rdm.nextInt(20)+10) ) )
	    			);
	}
	
	
	
}

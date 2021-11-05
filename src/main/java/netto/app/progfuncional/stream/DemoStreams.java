package netto.app.progfuncional.stream;
/* https://winterbe.com/posts/2014/07/31/java8-stream-tutorial-examples/ 
 * https://www.geeksforgeeks.org/stream-in-java/ */
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.*;

public class DemoStreams {

	
	public static void main(String[] args) {
//		streamOperations();
				
//		demoMap_Fn();
//		demoFilterSort_Pred();
		demoMap_SetForEachReduce();
		
//		optimizingByOrder();
//		scopeStreams();
		
	}
	
	/**
	 * Muestra las operaciones elementales de Stream [filter, map, sorded, forEach, findFirst, ifPresent] y de
	 * IntStream [range, average, mapToInt, max, mapToObj] 
	 */
	protected static void streamOperations() {
		
		//a partir de una lista definida
		List<String> myList = Arrays.asList("ana","c4x","alberto","berenice","claudia","carlos");		
		myList.stream()
			.filter( s -> s.startsWith("c")) //filtra los que empiezan con c
			.map( String:: toUpperCase)  //Aplica la funcion Upercase del objeto tipo cadena
			.sorted()  //Ordena alfabeticamente
			.forEach(System.out::println); //Llama la funcion println de System.out para cada elemento
		
		
		//creando instancia de lista
		Arrays.asList("ax", "ab", "a1").stream()
	    .findFirst()
	    .ifPresent(System.out::println);  // ax
		
		//Sin crear una coleccion
		Stream.of("ax", "ab", "a1")
		.findFirst().ifPresent(System.out::println);  // ax
		
		//Imprimiendo un rango de numeros
		IntStream.range(1, 5).forEach( num -> System.out.print(num+" ") ); //System.out::println);
		System.out.println();
		
		//realiza operaciones sobre un arreglo de enteros e imprime el promedio
		Arrays.stream( new int[] {1,2,3} )
			.map(n-> 2*n +1 )  //3,5,7
			.average()  // 3+5+7 / 3
			.ifPresent(System.out::println); //5.0
		
		//Determina el subfijo numerico mas alto
		Stream.of( "a1", "a2", "a3" )
			.map( st -> st.substring(1)) // ?x
			.mapToInt( Integer::parseInt )// x => n
			.max() //1,2,3 
			.ifPresent(System.out::println); // 3
		
		//Concatena subfijo numerico a cadena
		IntStream.range(1, 4)
			.mapToObj(i -> "a"+i)
			.forEach(System.out::println);
		
		//Combinado, de un stream de dobles, se mapea a entero y luego se concatena
		Stream.of(1.0, 2.0, 3.0)
			.mapToInt(Double::intValue)
			.mapToObj(i ->  "x"+i)
			.forEach(System.out::println);
	}
	
	protected static void scopeStreams() {
		Stream<String> stream =
			    Stream.of("d2", "a2", "b1", "b3", "c")
			        .filter(s -> s.startsWith("a"));

		//streams cannot be reused. As soon as you call any terminal operation the stream is closed:
		stream.anyMatch(s -> true);    // ok
//		stream.noneMatch(s -> true);   // exception
		
		
		/* To overcome this limitation we have to to create a new stream chain for every terminal operation we want to execute, 
		 * e.g. we could create a stream supplier to construct a new stream with all intermediate operations already set up:*/
		Supplier<Stream<String>> streamSupplier =
			    () -> Stream.of("d2", "a2", "b1", "b3", "c")
			            .filter(s -> s.startsWith("a"));

		streamSupplier.get().anyMatch(s -> true);   // ok
		streamSupplier.get().noneMatch(s -> true);  // ok
	}
	
	/**
	 * Muestra diferencia de performance dependiendo del orden de las operaciones
	 */
	protected static void optimizingByOrder() {
//		Stream.of("d2", "a2", "b1", "b3", "c")
//	    .filter(s -> {
//	        System.out.println("filter: " + s);
//	        return true;
//	    })
//	    .forEach(s -> System.out.println("forEach: " + s));
		/*  ************* 1  ************* */
		// map, then filter, then loop
//		Stream.of("d2", "a2", "b1", "b3", "c")
//	    .map(s -> {
//	        System.out.println("map: " + s);
//	        return s.toUpperCase();
//	    })
//	    .filter(s -> {
//	        System.out.println("filter: " + s);
//	        return s.startsWith("A");
//	    })
//	    .forEach(s -> System.out.println("forEach: " + s));
		/*  both map & filter called: 5*length, forEach called once. Total 11 calls */
		
		// filter, then map, then loop
//		Stream.of("d2", "a2", "b1", "b3", "c")
//	    .filter(s -> {
//	        System.out.println("filter: " + s);
//	        return s.startsWith("a");
//	    })
//	    .map(s -> {
//	        System.out.println("map: " + s);
//	        return s.toUpperCase();
//	    })
//	    .forEach(s -> System.out.println("forEach: " + s));
		/*  map & forEach called once, filter 5*length , Total: 7 calls*/
		
		/*  ************* 2  ************* */
		// sorted, filter, map, forEach: 15 calls
//		Stream.of("d2", "a2", "b1", "b3", "c")
//	    .sorted((s1, s2) -> {
//	        System.out.printf("sort: %s; %s\n", s1, s2);
//	        return s1.compareTo(s2);
//	    })
//	    .filter(s -> {
//	        System.out.println("filter: " + s);
//	        return s.startsWith("a");
//	    })
//	    .map(s -> {
//	        System.out.println("map: " + s);
//	        return s.toUpperCase();
//	    })
//	    .forEach(s -> System.out.println("forEach: " + s));
		
		//filter, sorted, map, forEach: 7 calls (sorted is not called since 'a' has only 1 element, then map and forEach only once
		Stream.of("d2", "a2", "b1", "b3", "c")
	    .filter(s -> {
	        System.out.println("filter: " + s);
	        return s.startsWith("a");
	    })
	    .sorted((s1, s2) -> {
	        System.out.printf("sort: %s; %s\n", s1, s2);
	        return s1.compareTo(s2);
	    })
	    .map(s -> {
	        System.out.println("map: " + s);
	        return s.toUpperCase();
	    })
	    .forEach(s -> System.out.println("forEach: " + s));
		
	}
	
	/**
	 * Muestra mapeo de funciones por elemento y ejemplo de Function
	 */
	protected static void demoMap_Fn() {
		// create a list of integers
	    List<Integer> number = Arrays.asList(2,3,4,5);
	    //This fuction object multiplies the number
	    Function<Integer, Integer > squareFn = x -> x*x;
	  
	    // demonstration of map method
	    List<Integer> square = number.stream()
	    		.map(
	    				squareFn
	    				//x -> x*x
	    				)
	    		.collect(Collectors.toList());
	    System.out.println(square);
	}
	
	/**
	 * Demuestra funcionamiento de Filter y Sort. Ejemplo de Predicate
	 */
	protected static void demoFilterSort_Pred() {
		// create a list of String
	    List<String> names =
	                Arrays.asList("Reflection","Collection","Stream","List","String");
	  
	    System.out.println("names: " + names );
	    // demonstration of filter method
	    Predicate<String> startWithS_Pred = s->s.startsWith("S");
	    List<String> sNames = names.stream()
	    			.filter(
	    					startWithS_Pred //s->s.startsWith("S")
	    					)
	                .collect(Collectors.toList());
	    System.out.println("sNames: " + sNames);
	  
	    // demonstration of sorted method (default Ascendent)
	    List<String> sortedNames =  names.stream()
	    		.sorted()
	    		.collect(Collectors.toList());
	    System.out.println("sortedNames: " + sortedNames);
	    
	    //  Comparator.reverseOrder()
	    List<String> sortedInvNames =  names.stream()
	    		.sorted( Comparator.reverseOrder() )
	    		.collect(Collectors.toList());
	    System.out.println("sortedInvNames: " + sortedInvNames);
	}
	
	protected static void demoMap_SetForEachReduce() {
		 // create a list of integers
	    List<Integer> numbers = Arrays.asList(2,3,4,5,2,3,8);  //2,3 are duplicated
	    Function<Integer, Integer> squareFn = x->x*x; 
	    
	    List<Integer> squareList = numbers.stream()
	    		.map( squareFn )
	    		.collect( Collectors.toList());
	    System.out.println(squareList);
	    // collect method returns a Set of squared values (No duplicates)
	    Set<Integer> squareSet = numbers.stream()
	         .map(
	        		 squareFn //x->x*x
	        		 )
	         .collect(Collectors.toSet());
	    System.out.println(squareSet);
	    //ReOrder Set
	   TreeSet<Integer> treeSet = new TreeSet<Integer>();
	   treeSet.addAll( squareSet );
	   System.out.println(treeSet);
	    
	    
	    // demonstration of forEach method
	   //numbers.stream().map(x->x*x).forEach(y->System.out.println(y));
	   StringBuilder sb = new StringBuilder("Numbers: ");
	   numbers.stream().forEach(n -> sb.append(n).append(" "));
	   sb.append("\n").append("Squared: ");
	   numbers.stream().map(x->x*x).forEach( y -> sb.append(y).append("/"));
	   System.out.println(sb);
	  
	    System.out.println(" ----");
	    // demonstration of reduce method
	    int even = numbers.stream()
	    		.filter(
	    				x->x%2==0 //evaluates if is even
	    		).reduce(
	    				0,(ans, num)-> ans+num  //adds to sum
	    				);
	  
	    System.out.println("Sum of all even: " + even); //2+4+2+8
	    
	}
	
}

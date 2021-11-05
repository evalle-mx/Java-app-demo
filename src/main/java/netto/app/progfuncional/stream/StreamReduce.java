package netto.app.progfuncional.stream;
/* https://www.arquitecturajava.com/java-stream-reduce-eliminando-bucles/ */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StreamReduce {

	static List<Integer> gastos;
	static int total = 0;
	
	public static void main(String[] args) {
//		simpleSum();
//		demoMathOperations();
		
//		getTotal_1();
//		getTotal_Reduce1();
//		getTotal_Reduce2();
		
//		concatStringsList();
		concatStringArray();
	}
	
	/* Sumar todos los enteros de un array */
	protected static void simpleSum() {
		int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		//Imperative
		int suma = 0;
//		for (int i = 0; i < nums.length; i++) {
//			suma += nums[i];
//		} //*/
		
		/*/ Declarative with Stream
		suma = Arrays.stream(nums)
				.reduce(
						0, 
						(a,b) -> a+b
				); //*/
		// Declarative with Stream + Method reference
		suma = Arrays.stream(nums).reduce(0, Integer::sum);
		
		System.out.println("suma: "+ suma );//55
	}
	
	protected static void demoMathOperations() {
		int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		
		int suma = Arrays.stream(numbers).reduce(0, Integer::sum);	System.out.println("suma "+ suma );
		int suma2 = Arrays.stream(numbers).reduce(0, (a,b)-> a+b );	System.out.println("suma2 "+ suma2 );
		
		int resta = Arrays.stream(numbers).reduce(0, (a,b) -> a-b); System.out.println("resta: " + resta );
		int multip = Arrays.stream(numbers).reduce(0, (a,b) -> a*b);	System.out.println("multip: " + multip ); // 0, initial is 0, 0 * whatever = 0
		int multip2 = Arrays.stream(numbers).reduce(1, (a,b) -> a*b);	System.out.println("multip2: " + multip2 );
		int div = Arrays.stream( numbers ).reduce(0, (a,b) -> a/b);	System.out.println("div: " + div ); // 0, initial is 0,
		int div2 = Arrays.stream( numbers ).reduce(1, (a,b) -> a/b);	System.out.println("div2: " + div2 ); 
		
		int maxOf = Arrays.stream(numbers).reduce(0, (a,b) -> a>b?a:b ); System.out.println("maxOf: " + maxOf );
		int maxOf2 = Arrays.stream(numbers).reduce(0, Integer::max ); System.out.println("maxOf2: " + maxOf2 );
		int minOf = Arrays.stream(numbers).reduce(1, (a,b) -> a<b?a:b ); System.out.println("minOf: " + minOf ); // if initial is 0, whatever = 0
		int minOf2 = Arrays.stream(numbers).reduce(1, Integer::min ); System.out.println("minOf2: " + minOf2 ); // if initial is 0, whatever = 0
		
	}
	
	protected static void getTotal_1() {
		setGastos();		
		
		for(int gasto: gastos) {
			total += gasto;
		}
		
		System.out.println(total);
	}
	
	/* Gracias a la programación funcional podemos realizar estas operaciones de una forma muy diferente 
	 * nos podemos apoyar en el método reduce que recibe 2 parámetros un acumulador como 1 y un elemento como 2 */
	protected static void getTotal_Reduce1() {
		setGastos();
		
		gastos.stream()
			.reduce(
					(acumulador, numero) -> {
						return acumulador+numero;
					}
			).ifPresent( System.out :: println );
	}
	
	protected static void getTotal_Reduce2() {
		setGastos();
		gastos.stream()
		 .reduce(
				 Integer::sum	//Podemos delegar en la clase Integer y en su reference method  de sum que realiza la misma operación.
		).ifPresent(System.out::println);
	}
	
	protected static void concatStringsList() {
		List<String> nombres = new ArrayList<String>();
		nombres.add("Dianita ");
		nombres.add("Breneli ");
		nombres.add("Rebeca ");
		nombres.add("Violeta ");
		
		nombres.stream()
			.reduce(
					String::concat
			)
			.ifPresent(System.out::println);
		
	}
	
	protected static void concatStringArray() {
		String[] strings = {"a", "b", "c", "d", "e"};
		
		String sumStrings = Arrays.stream(strings).reduce("", (s1,s2) -> s1+","+s2 ); // ,a,b,c,d,e
		System.out.println("sumStrings: " + sumStrings );
		
		sumStrings = Arrays.stream( strings ).reduce(
				"", (s1, s2) ->{
					if("".equals(s1)) {
						return s2;
					}
					else {
						return s1+","+s2;
					}
				}
				);		
		System.out.println("sumStrings: " + sumStrings );
		
		 // a|b|c|d|e , better uses the Java 8 String.join :)
		sumStrings = String.join("|", strings);
		System.out.println("sumStrings(join): " + sumStrings );
		
	}
	
	private static void setGastos(){
		gastos= new ArrayList<Integer>();
	    gastos.add(100);
	    gastos.add(200);
	    gastos.add(300);
	    
	    total = 0;
	}
}

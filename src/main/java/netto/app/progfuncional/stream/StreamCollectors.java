package netto.app.progfuncional.stream;
/* https://www.arquitecturajava.com/java-stream-collectors-y-su-uso/ */
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import netto.app.common.dto.Libro;

public class StreamCollectors {

	public static void main(String[] args) {
		Libro l1= new Libro ("El señor de los anillos", 1000);
		Libro l2= new Libro ("La fundacion", 500);
		Libro l3= new Libro ("El caliz de fuego", 600);
		
		
		
		//Se crea una instancia stream
		Stream<Libro> st = Stream.of(l1, l2, l3);
		//Recorrido para imprimir en pantalla
		st.forEach(
				l -> System.out.println(l.getTitulo())
				);
		
		//Convertir a Array  e imprimir en pantalla
		//Libro[] arrayLibro= st.toArray(Libro[]::new); //Arroja Excepction porque ya se ha cerrado el stream en el ejemplo anterior
		Supplier<Stream<Libro>> streamSupplier =
			    () -> Stream.of(l1, l2, l3);
		Libro[] arrayLibro =  streamSupplier.get().toArray(Libro[]::new);
		
//		for(int i=0;i<arrayLibro.length;i++) {
//			System.out.println(arrayLibro[i].getPaginas());
//		}
		for(Libro lb : arrayLibro) {
			System.out.println(lb.getPaginas());
		}
		
		/* USANDO los Java Stream Collectors, se puede convertir el stream a Lista o Conjunto(set), utilizando la clase Collectors y métodos toList() o toSet() */
		List<Libro> lista= streamSupplier.get().collect(Collectors.toList());	//st.collect(Collectors.toList());
		Set<Libro> conjunto = streamSupplier.get().collect(Collectors.toSet());
		for(Libro llb: lista) {
			System.out.println(llb.getTitulo());
		}
		for(Libro slb: conjunto) {
			System.out.println(slb.getTitulo());
		}
		
		//Concatenar todos los titulos
		String todosLosTitulos = streamSupplier.get()
				.map( (l) -> l.getTitulo() )
				.collect(Collectors.joining(", "));
		System.out.println("Titulos: " + todosLosTitulos);
		
		//Suma el total de las paginas entre todos los libros (1000+500+600=2100)
		Optional<Integer> totalPaginas = 
				streamSupplier.get().map((l) -> l.getPaginas())
				.collect(Collectors.reducing(Integer::sum));
		System.out.println(totalPaginas.get());
	}
}

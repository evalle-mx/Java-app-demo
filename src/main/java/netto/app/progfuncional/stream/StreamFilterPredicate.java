package netto.app.progfuncional.stream;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import netto.app.common.dto.Libro;

/* https://www.arquitecturajava.com/java-stream-filter-y-predicates/ */

public class StreamFilterPredicate {
	
	private List <Libro> lista;
	
	public static void main(String[] args) {
		StreamFilterPredicate clase = new StreamFilterPredicate();
//		clase.filterBigBooks(1000);
//		clase.filterByCategory("ciencia ficcion");
		clase.filterByComplexCustomized();
	}
	
	private void setLibros(){
		Libro l = new Libro("El señor de los anillos", "fantasia", 1100);
        Libro l2 = new Libro("El Juego de Ender", "ciencia ficcion", 500);
        Libro l3 = new Libro("La fundacion", "ciencia ficcion", 500);
        Libro l4 = new Libro("Los pilares de la tierra", "historica", 1200);
        
        lista = Arrays.asList(l, l2, l3, l4);
	}
	
	/* **************** */
	protected void filterBigBooks(int nPaginas) {
		setLibros();
		System.out.println("Libros con mas de "+nPaginas);
        lista.stream()
        	.filter( 
        			libro -> libro.getPaginas() > nPaginas
        			)
        	.map( libro -> libro.getTitulo() )
        	.forEach( System.out :: println  );        
	}
	
	/* Predicado simple que utiliza como parametro categoria */
	public static Predicate<Libro> fCategoria(String categoria) {
	    return (Libro l) -> {
	      return l.getCategoria().equals(categoria);
	    };
	  }
	
	protected void filterByCategory(String category) {
		setLibros();
		lista.stream()
    	.filter( 
    			fCategoria(category)
    			)
    	.map( libro -> libro.getTitulo() )
    	.forEach( System.out :: println  ); 
	}
	
	
	/* expresiones funcionales complejas ligadas a través de métodos de referencia: 
	 * (CienciaFiccion o Fantasia) Y mas de 1000 pp */
	
	public static boolean buenosLibros(Libro libro) {

        Predicate < Libro > p1 = (Libro l) -> l.getCategoria().equals("ciencia ficcion");
        Predicate < Libro > p2 = (Libro l) -> l.getCategoria().equals("fantasia");
        Predicate < Libro > p3 = (Libro l) -> l.getPaginas() > 1000;
        Predicate < Libro > ptotal = p1.or(p2).and(p3);

        return ptotal.test(libro);
    }
	
	protected void filterByComplexCustomized() {
		setLibros();
		lista.stream()
    	.filter( libro ->
    			buenosLibros(libro)
    			)
    	.map( Libro::getTitulo )
    	.forEach( System.out :: println  ); 
	}
}

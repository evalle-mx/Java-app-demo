package netto.app.collections;
/* https://howtodoinjava.com/java/sort/collections-sort/ */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import netto.app.common.dto.Post;
/**
 * Contiene ejemplos de Uso de SET, Stream, IntStream, Ordenamiento, eliminar duplicados, Random
 * @author evalle
 *
 */
public class OrderByComparator {

	private static String stNames = "Moises,Juan Manuel,Sergio Agustin,Matías,Yanel,Alberto,Rafael,Ivan Marino,rigoberto,Rogerio,lidia,Victor Manuel,Juan Antonio,Ricardo,MARTIN ALEJANDRO,José Israel,José Alejandro,Victor Hugo,Jorge Ricardo,David,RAFAEL,MIGUEL ANGEL,ANTONIO,ROSALIA,Rafael Eduardo,Salvador Antonio,Alberto,ARTURO,César,Teodoro,Eduardo,Pablo,Jose Max,EDUARDO,LETICIA,José Ricardo,Ricardo Othón,José Luis,anibal anselmo,Pascual,Jaime,Cuauhtemoc,RAFAEL,Miguel Ángel,Gerardo,Rafael,Luis Antonio,Luis Gerardo,JOSE ANTONIO,Gerardo,Oliver Omar,MARIA VERONICA,Javier Martin,Ricardo,Jorge,VICTOR HUGO,POLICARPO,Aniceto Enrique,Manuel,Leodegario,Jose,Julio Alejandro,Edgar Omar,Mauro Jesús,JORGE,LUIS ALEJANDRO,Alvaro,Alberto,VÍCTOR MANUEL,Jaime,Norma Carolina,fabian,gilberto,Eduardo,SERGIO,Juan Carlos,Rafael Alberto,Victor,Pedro,Alfonso M,Maria,Erik Hans,Fernando,AMADO JESUS,Oscar Gabriel,Juan Carlos,Jonathan,GUILLERMO,Juan Carlos,Olivia,Julio Cesar,Marco,Cecilia Azucena,MARIA GUADALUPE,Manuel Alberto,Hugo Felix,Pedro Sergio,Edgar Martin,ANAHI DE JESUS,YANET,Manuel Bernardo,Manuel,Ana Karen";
	private static String stSurNames = "Delgado,Arce,Correzzola,de Juan,Cegueda,Arévalo,Xelhuantzi,De La Huerta,perez,Bassetto,mendez,Galicia,Tellez,Hernandez,QUIROZ,Pereda,García,Mendoza,Morales,González,OGAZON,VEGA,LONA,RENDON,Vera,Sleiman,Molina,RESENDIZ,Castro,Galvez,Sanchez,Briones,Luevano,ANAYA,HURTADO,Briseño,Otal,Alba,heredia,Espinosa,Vega,Guerrero,TAPIA,Robles,Buendia,Rojas,Sanchez,Rendón,MONREAL,Ibarra,Salinas,DE LILLE,Burgos,Hernandez,Martinez,MARTINEZ,Martinez,Paredes,Lopez,Avila,Santoyo,Franco,Sánchez,Mejía,JARDINEZ,MONJARAS,Cadahia,Olmos,SOLÍS,Serra,Fuentes,rodriguez,hernandez,Betanzo,LOPEZ,Soto,Barraza,Flores,Castro,Espinosa de los Monteros,Duran,Solorzano,Hidalgo,VAZQUEZ,Martínez,Rivera,Garcia,ANDRADE,Moreno,Olvera,Solis,Jurado,Seijo,MARTINEZ,Villanueva,Mejia,Palencia,Pérez,MENESES,VAZQUEZ,Morales,Uriarte,Fernandez,Sanchez,Godinez,Miranda,Guzmán,Jasso,Aguilera,Acoltzi,Zuñiga,flores,Vido,lecona,Valdés,Lopez,Rivera,LARA,Páramo,Luna,Robles,Cabrera,Flores,NERI,MORALES,SALUM,RIEGO,Castillo,Morales,Calderon,CRESPO,López,Jimenez,Calderon,Quintero,Chavarria,TORRES,AGUNDIS,Machuca,Gómez,Villanueva,cruz,Cuevas,López,Gonzalez,GARCIA,Sánchez,Buendia,Castañeda,Velarde,Ortega,LOUSTAUNAU,Marin,Montoya,GARCIA,Zapata,Zorzano,Reyes,PATLAN,Gaytan,Gonzalez,Vargas,Cantabrana,Reyna,Fernández,Serrano,VAZQUEZ,MORENOS,Carrillo,ORTIZ,Pliego,Vidal,espinoza,orozco,Aggi,MARTINEZ,Leon,Cedillo,Soria,Acevedo,Salazar,Naranjo,Pedroza,Sánchez de Tagle,CRUZ,Chávez,Rios,Sarmina,ROJO,Tepole,Jiménez,Zendejas,Gahona,Luna,VILCHIS,Todd,Lopez,Muñoz,Escamilla,CRUZ,ARREOLA,Gómez,Beltran,Arevalo";
	
	private static List<String> lsNames, lsSurNames;
	private static Random rnd;
	
	private static void ordenaPosts() {
		//Crea una coleccion de Post para ordenar
		removeDuplicates();
		List<Post> lsPost = new ArrayList<Post>();
		rnd = new Random();
		int max =10;
		IntStream.range(0, max).forEach(i -> {
			//Post post = new Post();
			lsPost.add( new Post(
					max-i,//i+1,  //id
					lsNames.get(rnd.nextInt(lsNames.size())) + " " + lsSurNames.get( rnd.nextInt(lsSurNames.size()) ), //author
					i*2+1,	//authorId
					50+(i*2+1),	//likes
					(rnd.nextInt(98) +1)*.01, //popularity
					i*2+5 //reads
					) );
		});
		
		//Imprimir en pantalla los Posts
		lsPost.forEach(System.out::println );
		
		/* **** Ordenar Natural Order y Reverse Order  */
		System.out.println("Natural Order:");
		Collections.sort( lsPost ); //Necesita implementar Comparable<Post> ,con metodo comparator por el atributo deseado
		lsPost.forEach( p -> System.out.println(p));
		System.out.println("Reverse Order:");
		Collections.sort( lsPost, Collections.reverseOrder() ); 
		lsPost.forEach( p -> System.out.println(p));
		
		/* Ordenar por usando Comparator (Extraer el metodo compareTo a un objeto) */
		Comparator<Post> compAuthor = (Post p1, Post p2) -> p1.getAuthor().compareTo(p2.getAuthor());
		System.out.println("Author (asc) Order:");
		Collections.sort(lsPost, compAuthor);
		lsPost.forEach(System.out::println );
		System.out.println("Author Desc Order:");
		Collections.sort(lsPost, compAuthor.reversed() );
		lsPost.forEach(System.out::println);
	}
	
	
	public static void main(String[] args) {
//		removeDuplicates();
		ordenaPosts();		
		
	}
	
	/**
	 * Muestra como funciona un Set a partir de un Stream para evitar duplicados 
	 */
	private static void removeDuplicates() {
		Set<String> setNames, setSurName;
		//List<String> lsNames = Arrays.asList( stNames.split(",") ); // 103
		setNames =  Stream.of( stNames.split(",") ).collect(Collectors.toSet());//92
		lsNames = setNames.stream().collect(Collectors.toList());		
		
		//List<String> lsSurNames = Arrays.asList( stSurNames.split(",") ); //204
		setSurName = Stream.of( stSurNames.split(",") ).collect( Collectors.toSet() ); //178
		lsSurNames = setSurName.stream().collect(Collectors.toList());
		 
		/* **** Ordenar Natural Order y Reverse Order   (Mayusculas tienen precedencia lexicografica: rigo,Anel) *** */
		//TODO normalizar nombres a Camel Case
		System.out.println(lsNames);
		Collections.sort( lsNames );
		System.out.println("Ascendente:\n"+lsNames);
		Collections.sort( lsNames, Collections.reverseOrder() );
		System.out.println("Descendente:\n"+lsNames);
		
	}
}

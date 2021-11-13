package netto.app.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class FileReadingDemo {
	
	public final static String CONTENT_TYPE=";charset=UTF-8";
	public final static String PCHARSET = "UTF-8";
	
	/* auxiliares */
	static BufferedReader objReader;
	static StringBuilder sb;
	
	
//	/**
//	 * Lee un archivo planto, generando un Builder substituyendo Tabuladores por un espacio en blanco
//	 * utilizando el charset Definido
//	 * @param fullPath
//	 * @param stCharset
//	 * @return
//	 */
//	public static StringBuilder getBuilderNoTabsFile( String fullPath, String stCharset) throws IOException{
//		sb = new StringBuilder();
//		if(stCharset==null || stCharset.trim().equals("")){
//			stCharset=PCHARSET;	//"UTF8";
//		}
//		BufferedReader infile = new BufferedReader(
//				new InputStreamReader(
//	                         new FileInputStream(fullPath), stCharset));
//	    String strLine;
//	    while ((strLine = infile.readLine()) != null) 
//	    {
//	    	String unTabedLine = removeTabs(strLine).concat(" ");
//	        sb.append(unTabedLine).append("\n");
//	    }
//	    infile.close();
//	    return sb;
//	}
	
	private static void demoReplace() {
		String s = "\\sbas       def \\sg"; //con \\s se esta 'escapando' una cadena \s en Java 
	    System.out.println(s +"\n");
	    
	    System.out.println(s.replaceAll("\\s+", "_")); //Reemplaza grupo de espacios en blanco por un solo _
	    System.out.println(s.replace(" ", "_")); //Reemplaza por cada espacio en blanco un _
	    
	    System.out.println(s.replace("\\s", "@")); //Reemplaza el segmento de caracteres \s por @_
	    System.out.println(s.replaceAll("\\\\s+", "@")); //Reemplaza el segmento de caracteres \s por @  
	}
	
	/** Lee un archivo de multiples lineas con Titulos */
	public static void readWithHeadersByStream() throws IOException {
		objReader = new BufferedReader( 
				new FileReader("/home/evalle/workspace/java-demo-app/inputFiles/inFileHeaders.txt") );  //Ruta absoluta para prueba		
		
		//1er elemento Son Titulos, Headers o variables de control
		String strLine = objReader.readLine();
		System.out.println("HeaderLine: " + strLine);
		String[] headers = strLine
				.replaceAll("\\s+", " ")//Remplaza multiples espacios por uno solo
				.split(" ");	//Separa los elementos por caracter/espacioEnBlanco
		
		System.out.println( "headers.length: " + headers.length );
		for(String inputSt : headers) {
			System.out.println(inputSt);
		}
		
		List<String> lsTokens;
		int counter = 2; //1 es titulo 
		//Leyendo cada linea del reader
		while ((strLine = objReader.readLine()) != null) {			
			lsTokens = Arrays.asList( strLine.split(" "));
			System.out.println("("+(counter++)+"): " + strLine +" | tokens: " + lsTokens );
		}

        objReader.close();
	}
	
	/** Crea una Lista de Listas de Enteros [List< List<Integer> >] a partir de un archivo txt usando un Stream 
	 * (No valida cada linea)
	 * @throws IOException */	
	public static void setListOfList() throws IOException {
		List< List<Integer> > lsList;
//		List<Integer> lsInt;
		
		objReader = new BufferedReader( 
				new FileReader("/home/evalle/workspace/java-demo-app/inputFiles/inFileLists.txt") );  //Ruta absoluta para prueba	
		//Si 1er elemento Son Titulos, Headers o variables de control
		String[] headers = objReader.readLine()
				.replaceAll("\\s+", " ")//Remplaza multiples espacios por uno solo
				.split(" ");	//Separa los elementos por caracter/espacioEnBlanco
		//Para este archivo, [0]=primeros/ultimos [1]=#elementos
		if(headers.length==2) {
			lsList = new ArrayList<List<Integer> >();
			Integer numElems = Integer.parseInt(headers[1]);
			if(headers[0].equals("primeros")) {
				System.out.println("Primeros " + numElems + " elementos " );
				IntStream.range(0, numElems).forEach(i -> {
					try {
						lsList.add(
						        Stream.of(objReader.readLine().replaceAll("\\s+$", "").split(" "))
						        .map(Integer::parseInt)
						        .collect(Collectors.toList())
						);
					} catch (IOException e) {
						throw new RuntimeException(e);
					}
				});
			}
			else { //ultimos por eliminacion
				System.out.println("ultimos " + numElems + " elementos " );
				System.out.println(" Como no se sabe el numero de elementos total, se genera una lista con todos y luego se itera en reversa  ");
			}
			
			
			System.out.println("Elementos en lsList:  " + lsList.size() );
			System.out.println( lsList );
		}
		else {
			System.err.println("Archivo no aceptado");
		}

        objReader.close();
	}
	
	
	public static void main(String[] args) {
		try {
//			readWithHeadersByStream();
			setListOfList();
//			demoReplace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

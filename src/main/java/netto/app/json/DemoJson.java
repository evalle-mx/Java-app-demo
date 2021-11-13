package netto.app.json;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.json.CDL;
import org.json.Cookie;
import org.json.HTTP;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class DemoJson {
	
	public static void main(String[] args) {
//		jsonGoogleDemo();
		jsonOrgDemo();
//		createDynamicJSONArrayFromFile();
	}
	/**
	 * Muestra funcionalidad de Json de Google
	 */
	public static void jsonGoogleDemo() {
		JsonObject json = new JsonObject();
		json.addProperty("nombre", "Violeta" );
		json.addProperty("apellido", "Romero" );
		json.addProperty("edad", 35 );
		
		System.out.println("json: " +json.toString());
		JsonArray jsArr = new JsonArray();
		jsArr.add(json);
		System.out.println("jsArr: " +jsArr.toString());
		Integer edad = json.get("edad").getAsInt() ;
				//getAsInt("edad");
		System.out.println("edad de " + (json.get("nombre"))+ " " + edad);
		System.out.println("edad de " + (json.get("nombre").getAsString())+ " " + edad);
	}
	
	/**
	 * Muestra funcionalidad de JSON de org.json
	 */
	public static void jsonOrgDemo() {
		String datos = "Violeta,Romero,35";
		
		JSONObject jsonA = new JSONObject();
		jsonA.put("nombre", Arrays.asList(datos.split(",") ).get(0));
		jsonA.put("apellido", Arrays.asList(datos.split(",") ).get(1));
		jsonA.put("edad", Integer.parseInt(Arrays.asList(datos.split(",") ).get(2)));
		System.out.println("json: " +jsonA.toString());
		System.out.println( new JSONArray().put(jsonA).toString() );		
		
		JSONObject jsonB = new JSONObject("{ \"updated\":true, \"label\":\"test\"} ");
		jsonB.put("asignedTo", jsonA);		
		
		
		//Creating JSONArray Directly From a Collection or an Array
		List<String> countries = Arrays.asList("MEX", "CAN", "USA", "GER" );
		JSONArray jsCountries = new JSONArray(countries);
		jsonB.put("countries", jsCountries);
		jsonB.put("codes", "AC,BC,DE".split(","));
		
		System.out.println(jsonB);
		
		// JSONTokener
		JSONTokener jt = new JSONTokener( jsonA.getString("nombre" ));
		while(jt.more()) {
			System.out.println(jt.next()); //Char
		}
		
		//CDL
		//Producing Comma Delimited Text From JSONArray
		System.out.println("Paises: " + CDL.rowToString(jsCountries) );
		String staff = "name,city,age" +
				  "\njohn,chicago,22" +
				  "\ngary,florida,35" +
				  "\nsal,vegas,18";
		
		JSONArray jsStaff = CDL.toJSONArray(staff);
		System.out.println(jsStaff);
		
		//Converting a JSONObject into Cookie String
		String cookie = "username=John Doe; expires=Thu, 18 Dec 2013 12:00:00 UTC; path=/";
		JSONObject cookieJO = Cookie.toJSONObject(cookie);
		System.out.println("cookieJO: " + cookieJO );
		cookieJO.put("value", "Juan Perez");
		String cookie2 = Cookie.toString(cookieJO);
		System.out.println("cookie2: " + cookie2);
		
		
		//Converting JSONObject to HTTP Header and String Back
		JSONObject jo = new JSONObject();
		jo.put("Method", "POST");
		jo.put("Request-URI", "http://www.example.com/");
		jo.put("HTTP-Version", "HTTP/1.1");
		String httpStr = HTTP.toString(jo);
		System.out.println( "httpStr: " + httpStr );
		JSONObject httpJson = HTTP.toJSONObject("POST \"http://www.google.com/\" HTTP/1.2");
		System.out.println( "httpJson: " + httpJson );
	}
	
	
	/** 
	 * Produce Un JSONArray a partir de un archivo plano, 
	 * (Opcional: 1a linea es nombre atributo)
	 */
	@SuppressWarnings("resource")
	public static void createDynamicJSONArrayFromFile() {
		String delimiter = ",";
		try {
			BufferedReader objReader = new BufferedReader( 
					new FileReader("/home/evalle/workspace/java-demo-app/inputFiles/usuarios.csv") );
			String line, stAttributes = objReader.readLine();
					//"idPersona,email,Nombre,edad,activo,empresa"; /* OBLIGATORIAMENTE debe coincidir el numero y orden de columnas */
			String[] attrNames = stAttributes
					.replaceAll("\\s+", "")//Remplaza multiples espacios
					.split(delimiter);
			JSONArray ja = new JSONArray();   //ja.put("name"); ja.put("city");ja.put("age");
			for(String atName: attrNames) {
				ja.put(atName);
			}
			JSONArray jsResult = new JSONArray();
			while((line = objReader.readLine())!=null ) {
				jsResult.put( CDL.toJSONArray(ja, line.replaceAll("\\s+", " ")).getJSONObject(0) );
			}
			
			/*  IMPRIMIR RESULTADOS PARA DEMO */
			System.out.println("# elementos: "+jsResult.length() );
			//System.out.println(jsResult);
			IntStream.range(0, jsResult.length()-1)
				.forEach( i -> System.out.println(i+") " + jsResult.getJSONObject(i)) );
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}

package netto.projs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import netto.app.util.CommonUtil;


/**
 * Clase encargada de crear Jsons a partir de archivos CSV
 * @author evalle
 *
 */
public class CreateJsonFromCsv {

	static final String fileQA =  
//			"input/javaReviewQa1.csv";
			"input/simplilearnJavaQa.csv"; 
	
	static final String absoluteOutPath = "/home/evalle/app/webServer/PORTFOLIO/wikinetto/json/<SIMPLE_NAME>.json";
	
	public static void main(String[] args) {
		try {
			exportJSONQAFromCsvMultipleAnswer(fileQA, absoluteOutPath);
		} catch (IOException | JSONException e) {
			e.printStackTrace();
		}
	}
	
	/** 
	 * Produce Un JSONArray a partir de un archivo csv, 
	 * Produce un Json con dos atributos, question(cadena) y answer(Arreglo de cadenas)
	 * @throws JSONException 
	 * @throws IOException 
	 */
	@SuppressWarnings("resource")
	protected static void exportJSONQAFromCsvMultipleAnswer(String inRootfileName, String absoluteOutPath) throws IOException, JSONException {
		/* Lectura de archivo CSV y creacion de JSON */
		String delimiter = ";";
		String simpleName = inRootfileName.substring( inRootfileName.lastIndexOf("/")+1, inRootfileName.lastIndexOf("."));
		System.out.println("Leyendo de archivo (en raiz de proyecto) "+inRootfileName);
		BufferedReader objReader = new BufferedReader( 
				new FileReader(inRootfileName) );
		String line;
				//question;answer
		JSONArray jQA = new JSONArray(), jsAnswer;
		JSONObject json ;
		String question, answers;
		int id = 1;
		while((line = objReader.readLine())!=null ) {
			//System.out.println(id+"> "+line);
			if(line.length()>1 && !line.startsWith("#") && !line.startsWith("//")) { //Omite lineas vacias o comentarios
				question = line.split(delimiter)[0];
				answers = line.split(delimiter)[1].trim();
				if(answers.endsWith(".")) {
					answers = answers.substring(0, answers.length()-1);
				}
				json = new JSONObject();
				json.put("id", id++);
				json.put("question", question);
				jsAnswer = new JSONArray(answers.split("\\."));
				json.put("answer", jsAnswer);
				jQA.put(json);
			}
		}
		
		//Exportar a ruta
		CommonUtil.writeFile(absoluteOutPath.replace("<SIMPLE_NAME>", simpleName), 
				CommonUtil.formatJsonString(jQA.toString()),
				//formtJsonByGoogle(jQA.toString()), 
				false);
		System.out.println("Se escribio Archivo "+ absoluteOutPath.replace("<SIMPLE_NAME>", simpleName) );
	}
}

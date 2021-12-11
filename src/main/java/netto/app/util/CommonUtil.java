package netto.app.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import com.google.gson.JsonElement;
//import com.google.gson.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CommonUtil {

	public final static String CONTENT_TYPE=";charset=UTF-8";
	public final static String PCHARSET = "UTF-8";
	
	
//	static Logger log4j = Logger.getLogger( CommonUtil.class );
	
	/* ***********************   JSON   ********************* */
	
//	/**
//	 * Realiza un formateo para identar una Cadena Json Usando Parser
//	 * @param jsonString
//	 * @return
//	 */
//	public static String formtJsonByGoogle(String jsonString){
//		JsonParser parser = new JsonParser();
//		JsonElement el = parser.parse(jsonString);
//		
//		Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
//		jsonString = gson.toJson(el);
//		
//		return jsonString;
//	}
	
	/*
	 * 
	 */
	public static String formatJsonString(String jsonString) {
		String prettyJson;
       ObjectMapper mapper = new ObjectMapper();
       try {
           Object jsonObject = mapper.readValue(jsonString, Object.class);
           prettyJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObject);
           //System.out.println(prettyJson);
           return prettyJson;
       } catch (IOException e) {
           e.printStackTrace();
           return null;
       }
   }
	
	/**
	 * Obtiene el json contenido en un archivo plano .json
	 * @param jsonPath [i.e. /home/user/dir/read-1.json]
	 * @return
	 */
	public static String getJsonFile(String jsonFullPath) {		
		String jsonFile = "[]";
		try {
			jsonFile = getBuilderNoTabsFile(jsonFullPath, PCHARSET).toString();
		} catch (IOException e) {			
			//log4j.fatal("Excepción al generar Json desde archivo: [".concat(jsonPath).concat("]: "), e);
			System.err.println("Excepción al generar Json desde archivo: [".concat(jsonFullPath).concat("]: ") );
			e.printStackTrace();
			jsonFile = "[]";
		}
		return jsonFile;
	}
	
	
	/* ************************   STRING  *************************** */
	/**
     * Remueve los espacios en blanco al principio y final de una cadena, 
     * sin afectar los que se encuentran entre caracteres 
     * @param tabulada
     * @return
     */
    public static String removeTabs(String tabulada){
    	String subCadena = tabulada.replace("\t", "");
		boolean val = true;
		do{
			if(subCadena!=null && subCadena.length()>0){
				String prim = subCadena.substring(0,1);
				if(prim.equals(" ")){
					subCadena = subCadena.substring(1,subCadena.length());
				}else{
					//val = false;
					if(subCadena.length()>0){
						String last = subCadena.substring(subCadena.length()-1,subCadena.length());
						if(last.equals(" ")){
							subCadena = subCadena.substring(0,subCadena.length()-1);
						}else{
							val = false;
						}
					}
				}
			}else{
				if(subCadena==null){
					subCadena = "";
				}
				val = false;
			}
			
		}while(val);
		return subCadena;
    }
	
	/**
	 * Lee un archivo planto, generando un Builder substituyendo Tabuladores por un espacio en blanco
	 * utilizando el charset Definido
	 * @param fullPath
	 * @param stCharset
	 * @return
	 */
	public static StringBuilder getBuilderNoTabsFile( String fullPath, String stCharset) throws IOException{
		StringBuilder sb = new StringBuilder();
		if(stCharset==null || stCharset.trim().equals("")){
			stCharset=PCHARSET;	//"UTF8";
		}
		BufferedReader infile = new BufferedReader(
				new InputStreamReader(
	                         new FileInputStream(fullPath), stCharset));
	    String strLine;
	    while ((strLine = infile.readLine()) != null) 
	    {
	    	String unTabedLine = removeTabs(strLine).concat(" ");
	        sb.append(unTabedLine).append("\n");
	    }
	    infile.close();
	    return sb;
	}
	
	/* ************************   I/O  Lectura *************************** */
	/**
	 * Muestra una lista de archivos contenidos dentro de un directorio
	 * @param dirPath
	 */
	public static List<String> listFilesInDir(String dirPath){
		List<String> lsFiles = null;
		File dir = new File(dirPath);
		FileFilter fileFilter = new FileFilter() {
	        public boolean accept(File file) {
	            return file.isFile();
	        }
	    };
	    
	    File[] files = dir.listFiles(fileFilter);
		
		if(files!=null){
			lsFiles = new ArrayList<String>();
			for(int x=0;x<files.length;x++){				
				lsFiles.add(x, files[x].getName());
//				System.out.println(files[x].getName());
			}
		}else{
			System.out.println("El directorio esta vacio");
		}
		return lsFiles;
	}
	
	/**
	 * Lee un archivo y genera una Lista de cadenas por cada linea en el archivo
	 * @param fullPath
	 */
	public static List<String> getLinesFile(String fullPath){
		List<String> lsRead = new ArrayList<String>();
		try 
	    {
	        BufferedReader infile = new BufferedReader(new FileReader(fullPath));
	        String strLine;
	        while ((strLine = infile.readLine()) != null) 
	        {
	        	lsRead.add(strLine);
	        }
	        infile.close();	        
	    } 
	    catch (IOException e) 
	    {	//System.err.println("IOException "+e);
	    	e.printStackTrace();
	    	System.err.println("Excepcion de lectura "+ e.getLocalizedMessage());
	    }
	    
	    return lsRead;
	}
	
	/* ******************** I/O  ESCRITURA / SALIDA ********************************************************* */
	/**
	 * Escribe una cadena de texto en un archivo
	 * @param filePath ruta, nombre y extension  del archivo
	 * @param texto Cadena a agregar
	 * @param append agrega (true) o reemplaza (false)
	 */
	public static void writeFile(String filePath, String texto, String encode, boolean append ){
		BufferedWriter bufferedWriter;
		if(null == texto){
			return;
		}
		try {
			//bufferedWriter = new BufferedWriter(new FileWriter(filePath, append));
			bufferedWriter = new BufferedWriter (new OutputStreamWriter(new FileOutputStream(filePath, append),encode));
			bufferedWriter.write(texto);
			bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Escribe una cadena de texto en un archivo
	 * @param filePath ruta, nombre y extension  del archivo
	 * @param texto Cadena a agregar
	 * @param append agrega (true) o reemplaza (false)
	 * @throws IOException 
	 */
	public static void writeFile(String filePath, String texto, boolean append ) {
		writeFile(filePath, texto, PCHARSET, append );
	}
	
	public static int binomialCoeff(int n, int k) {
		if(n >=k && k>=0) {
			int res = 1;
			if(k > n-k) {
				k = n-k;
			}
			for(int a=0; a<k; a++) {
				res *= (n-a);
				res /= (a+1);
			}
			return res;
		}
		else
			return 0;
	}
	
	/**
	 * Realiza una copia de archivo de un origen a una copia
	 * @param pathOrigen
	 * @param pathDestino
	 * @return
	 * @throws Exception
	 */
	public static boolean copyFile(String pathOrigen, String pathDestino) throws Exception {
		boolean copied = false;
		System.out.println("pathOrigen: " + pathOrigen);
		System.out.println("pathDestino: " + pathDestino);
		InputStream is = null;
	    OutputStream os = null;
		
		try{
			File fileI = new File(pathOrigen);
			File fileO = new File(pathDestino);
			if(fileI.exists()){				
				is = new FileInputStream(fileI);
		        os = new FileOutputStream(fileO);
		        byte[] buffer = new byte[1024];
		        int length;
		        while ((length = is.read(buffer)) > 0) {
		            os.write(buffer, 0, length);
		        }
		        copied = true;
			}else{
				System.err.println("El archivo Origen no existe");
			}
		}catch (Exception e) {
			e.printStackTrace();
			System.err.println("Excepcion: "+ e.getLocalizedMessage());
			copied = false;
			throw e;
		}finally {
			if(is!=null){
				is.close();
			}
	        if(os!=null){
	        	os.close();
	        }
	    }
		
		return copied;
	}
	
	/**
	 * Crea la estructura de directorios si no existe
	 * @param fullPath
	 * @param crear
	 * @return
	 */
	public static boolean createDirIfNotExist(String fullPath, boolean crear) {
		//BACKUP_JSON_DIR
		File directory = new File(fullPath);
		
		if (!directory.exists()){
			System.out.println("NO existe ruta: " + fullPath );
			if(crear) {
				System.out.println("Se crea ruta");
				directory.mkdirs();
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return true;
		}
		
	}

	
	/* **************  DEPRECADOS o TEMPORALES por compatibilidad  ******************* */
	/**
	 * Obtiene el json contenido en un archivo plano .json desde el directorio designado
	 * @param jsonFileName [i.e. read-1.json]
	 * @param jsonDir [i.e. /home/user/dir/]
	 * @return
	 */
	public static String getJsonFile(String jsonFileName, String jsonDir) {
		String jsonPath = null;
		jsonPath = jsonDir.concat(jsonFileName);
		return getJsonFile(jsonPath);
	}
	
	/**
	 * Se reemplazan tildes y acentos específicos de Vocales
	 * @param token
	 * @return el token modificado
	 */
	public static String replaceAccentMarks(String token){ //replaceEspecialChars
		return token.replaceAll("[àáâãäå]","a").
					replaceAll("[èéêë]","e").
					replaceAll("[ìíîï]","i").
					replaceAll("[òóôõö]","o").
					replaceAll("[ùúûü]","u");
	}
	
	/**
	 * Obtiene la extensión de la ruta de archivo
	 * @param fullPath
	 * @return
	 */
	public static String getFileExt(String fullPath){
		String fileName = "", fileExt="";
		
		if(fullPath!=null && !(fullPath.trim().equals("")) ){
			
				fileName = fullPath.substring(fullPath.lastIndexOf("/")+1,fullPath.length());
				
				if(fileName.indexOf(".")!=-1){
					fileExt = fullPath.substring(fullPath.lastIndexOf(".")+1,fullPath.length());
				}
		}
		
		return fileExt;
	}
	
}

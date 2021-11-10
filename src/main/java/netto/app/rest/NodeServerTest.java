package netto.app.rest;

import java.net.ConnectException;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status.Family;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class NodeServerTest {
	
	private static final String CONTENT_TYPE=";charset=UTF-8";
	private static WebTarget target;
	private static Gson gson;
	private final static String RESOURCE = "http://127.0.0.1:3000";
	
	static final Logger log4j = LogManager.getLogger( NodeServerTest.class );	
	
	public static void main(String[] args) {
		try {
			initClient();
			pingService();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected static String pingService() 
			throws ConnectException, Exception{
		
		String jsonResponse= null;
		Response response = null;
		
		response = getRequest( "/url" ); 
				//postRequest("{}", "/url" );
		if (response.getStatusInfo().getFamily() == Family.SUCCESSFUL) {
			jsonResponse =  response.readEntity(String.class);
		} else {
			 int cve = response.getStatus();
			 log4j.error("Rest.ERROR --> estatus: " + cve );
			 jsonResponse =  response.readEntity(String.class);
			 
			 if(jsonResponse.startsWith("<!DOCTYPE html")){
				 log4j.error("Posible Error de comunicación con: "+RESOURCE +": ", new NullPointerException());
			 }
		}
		log4j.debug("\n Response:\n " + jsonResponse +"\n");
		return jsonResponse;
	}
	
	
	private static void initClient(){
		log4j.trace("<initClient> on Resource: " + RESOURCE);
		log4j.debug("<initClient> on Resource: " + RESOURCE);
		log4j.info("<initClient> on Resource: " + RESOURCE);//"We've just greeted the user!");
//		log4j.warn("We've just greeted the user!");
//		log4j.error("We've just greeted the user!"); //Default enabled
//		log4j.fatal("We've just greeted the user!"); //Default enabled
		
		 if(target == null){
			 target =ClientBuilder.newClient().target(RESOURCE);
		 }
	}
	
	/**
	 * Consume endpoint GET
	 * @param endPoint
	 * @return
	 */
	protected static Response getRequest(String endPoint){	//Object servicioDto,String nombreServicio){
		 log4j.debug("<GET> " );
		 if(gson == null){
			 gson = new GsonBuilder().setDateFormat("MMM dd, yyyy HH:mm:ss a").create(); 

		 }
		  log4j.debug("\n target: " + target+"\n endPoint="+endPoint);
		  return  target.path(endPoint).
	        		request(MediaType.APPLICATION_JSON_TYPE+CONTENT_TYPE).
	        		get(Response.class);
	}
	/**
	 * Consume endpoint POST Enviando Entity en el body
	 * @param inputJson
	 * @param endPoint
	 * @return
	 */
	protected static Response postRequest(String inputJson, String endPoint){	//Object servicioDto,String nombreServicio){
		 log4j.debug("<POST> " );
		 if(gson == null){
			 gson = new GsonBuilder().setDateFormat("MMM dd, yyyy HH:mm:ss a").create(); 

		 }
		  log4j.debug("\n target: " + target+"\n endPoint="+endPoint);
		  log4j.debug("Json==>: \n" + inputJson );
		  return  target.path(endPoint).
	        		request(MediaType.APPLICATION_JSON_TYPE+CONTENT_TYPE).
	        		post(Entity.entity(inputJson, MediaType.APPLICATION_JSON+CONTENT_TYPE),Response.class);
	}
	
	protected static Response putRequest(String inputJson, String endPoint){	//Object servicioDto,String nombreServicio){
		 log4j.debug("request() -->   gson="+gson );
		 if(gson == null){
			 gson = new GsonBuilder().setDateFormat("MMM dd, yyyy HH:mm:ss a").create(); 

		 }
		  log4j.debug("request() -->  \n target: " + target+"\n endPoint="+endPoint);
		  log4j.debug("Json==>: \n" + inputJson );
		  return  target.path(endPoint).
	        		request(MediaType.APPLICATION_JSON_TYPE+CONTENT_TYPE).
	        		put(Entity.entity(inputJson, MediaType.APPLICATION_JSON+CONTENT_TYPE),Response.class);
	}
	
	protected static Response deleteRequest(String inputJson, String endPoint){	//Object servicioDto,String nombreServicio){
		 log4j.debug("request() -->   gson="+gson );
		 if(gson == null){
			 gson = new GsonBuilder().setDateFormat("MMM dd, yyyy HH:mm:ss a").create(); 

		 }
		  log4j.debug("request() -->  \n target: " + target+"\n endPoint="+endPoint);
		  log4j.debug("Json==>: \n" + inputJson );
		  return  target.path(endPoint).
	        		request(MediaType.APPLICATION_JSON_TYPE+CONTENT_TYPE).
	        		delete(Response.class);
	}

}
/*  //DEPENDENCIAS AGREGADAS EN POM
		<dependency>
		    <groupId>javax.ws.rs</groupId>
		    <artifactId>javax.ws.rs-api</artifactId>
		    <version>2.1.1</version>
		</dependency>
		<dependency>
		    <groupId>org.jboss.resteasy</groupId>
		    <artifactId>resteasy-client</artifactId>
		    <version>3.0.2.Final</version>
		</dependency>
		<!-- https://mvnrepository.com/artifact/log4j/log4j -->
		<dependency>
		    <groupId>log4j</groupId>
		    <artifactId>log4j</artifactId>
		    <version>1.2.17</version>
		</dependency>
		
		https://www.sentinelone.com/blog/maven-log4j2-project/
		
		
		<dependency>
		    <groupId>org.apache.logging.log4j</groupId>
		    <artifactId>log4j-api</artifactId>
		    <version>2.13.0</version>
		</dependency>
		<dependency>
		    <groupId>org.apache.logging.log4j</groupId>
		    <artifactId>log4j-core</artifactId>
		    <version>2.13.0</version>
		</dependency>
*/
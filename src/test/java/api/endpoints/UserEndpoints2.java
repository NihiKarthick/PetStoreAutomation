package api.endpoints;
import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

//UserEndpoints.java
//Created for Perform  Create, Read, Update and Delete Request User API'S
public class UserEndpoints2 {
	
	// method created for getting URL's from Properties file
	 static ResourceBundle getURL(){
		
		ResourceBundle routes=ResourceBundle.getBundle("routes"); //Load Properties file// name of the properties file
		
		return routes;
	}
	
	public static Response CreateUser(User Payload){
		
		String Post_URL = getURL().getString("post_url");
		
		Response res=given()
		  .contentType(ContentType.JSON)
		  .accept(ContentType.JSON)
		  .body(Payload)
		
		   .when()
		    .post(Post_URL);
		   
		return 	res;
		    
	}
	
	public static Response ReadUser(String UserName){
		
		String GET_URL = getURL().getString("get_url");
		Response res=given()
		  .pathParam("username", UserName)
		
		   .when()
		    .get(GET_URL);
		   
		return 	res;
		    
	}
	
public static Response UpdateUser(String UserName,User Payload){
		
	String PUT_URL = getURL().getString("update_url");
		Response res=given()
		  .contentType(ContentType.JSON)
		  .accept(ContentType.JSON)
		  .pathParam("username", UserName)
		  .body(Payload)
		
		   .when()
		    .put(PUT_URL);
		   
		return 	res;
		    
	}

public static Response DeleteUser(String UserName){
	String DELETE_URL = getURL().getString("delete_url");
	
	Response res=given()
	  .pathParam("username", UserName)
	
	   .when()
	    .delete(DELETE_URL);
	   
	return 	res;
	    
}
	
}

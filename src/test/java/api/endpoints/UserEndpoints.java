package api.endpoints;
import static io.restassured.RestAssured.given;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

//UserEndpoints.java
//Created for Perform  Create, Read, Update and Delete Request User API'S
public class UserEndpoints {
	
	public static Response CreateUser(User Payload){
		
		Response res=given()
		  .contentType(ContentType.JSON)
		  .accept(ContentType.JSON)
		  .body(Payload)
		
		   .when()
		    .post(Routes.post_url);
		   
		return 	res;
		    
	}
	
	public static Response ReadUser(String UserName){
		
		Response res=given()
		  .pathParam("username", UserName)
		
		   .when()
		    .get(Routes.get_url);
		   
		return 	res;
		    
	}
	
public static Response UpdateUser(String UserName,User Payload){
		
		Response res=given()
		  .contentType(ContentType.JSON)
		  .accept(ContentType.JSON)
		  .pathParam("username", UserName)
		  .body(Payload)
		
		   .when()
		    .put(Routes.update_url);
		   
		return 	res;
		    
	}

public static Response DeleteUser(String UserName){
	
	Response res=given()
	  .pathParam("username", UserName)
	
	   .when()
	    .delete(Routes.delete_url);
	   
	return 	res;
	    
}
	
}

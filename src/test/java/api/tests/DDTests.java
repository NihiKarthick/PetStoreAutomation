package api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndpoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDTests {
	
	
	@Test(priority = 1, dataProvider = "Data",dataProviderClass = DataProviders.class)
	public void testPostuser(String userID,String Uname,String fname,String lname,String usermail,String pwd,String phone) {
		
		
		User userpayload= new User();
		
		userpayload.setId(Integer.parseInt(userID));
		userpayload.setUsername(Uname);
		userpayload.setFirstName(fname);
		userpayload.setLastName(lname);
		userpayload.setEmail(usermail);
		userpayload.setPassword(pwd);
		userpayload.setPhone(phone);
		
		Response createUser = UserEndpoints.CreateUser(userpayload);
				
		Assert.assertEquals(createUser.getStatusCode(), 200);
	}
	
	
	@Test(priority = 2,dataProvider = "UserNames",dataProviderClass = DataProviders.class)
	public void deleteUserByName(String Uname) {
		
		Response response = UserEndpoints.DeleteUser(Uname);
		Assert.assertEquals(response.getStatusCode(), 200);
	}

}

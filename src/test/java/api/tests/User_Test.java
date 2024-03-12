package api.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpoints;
import api.payload.User;
import io.restassured.response.Response;

public class User_Test {
	
	Faker faker;
	User userpayload;
 public Logger logger;
	
	@BeforeClass
	public void Setupdata() {
		
		faker= new Faker();
		userpayload=new User();
		
		userpayload.setId(faker.idNumber().hashCode());
		userpayload.setFirstName(faker.name().firstName());
		userpayload.setLastName(faker.name().lastName());
		userpayload.setUsername(faker.name().username());
		userpayload.setEmail(faker.internet().safeEmailAddress());
		userpayload.setPassword(faker.internet().password());
		userpayload.setPhone(faker.phoneNumber().cellPhone());
		
		//Initiating the logs
		
		logger=LogManager.getLogger(this.getClass());
		
	}
	@Test(priority = 1)
	public void test_post() {
		logger.info("****************Creating User***************");
		
		Response createUser = UserEndpoints.CreateUser(userpayload);
		createUser.then().log().all();
		
		Assert.assertEquals(createUser.getStatusCode(), 200);
		
		System.out.println("Response is captured and Validated Successfully");
		
		logger.info("****************User is Created***************");
	}
	
	@Test(priority  =2)
	public void test_getuser() {
		
		logger.info("****************Reading User Info***************");
		
		Response readUser = UserEndpoints.ReadUser(this.userpayload.getUsername());
		readUser.then().log().all();
		
        Assert.assertEquals(readUser.getStatusCode(), 200);
		
		System.out.println("Username is captured and Validated Successfully");
		logger.info("****************User Info is Displayed***************");
		
	}
	@Test(priority = 3)
	public void test_update() {
		
		//update data using Payload
		logger.info("****************Updating User Info***************");
		
		userpayload.setFirstName(faker.name().firstName());
		userpayload.setLastName(faker.name().lastName());
		userpayload.setEmail(faker.internet().safeEmailAddress());
		
		Response updateUser = UserEndpoints.UpdateUser(this.userpayload.getUsername(),userpayload);
		
		//Chai Assertion
		updateUser.then().log().body().statusCode(200);
		
		//TestNg Assertion
		Assert.assertEquals(updateUser.getStatusCode(), 200);
		
		//Checking data after update
		Response AfterupdateUser = UserEndpoints.UpdateUser(this.userpayload.getUsername(),userpayload);
		Assert.assertEquals(AfterupdateUser.getStatusCode(), 200);
		
		System.out.println("Username is updated and Validated Successfully");
		
		logger.info("****************User Info is Updated***************");
	}
	
	
	@Test(priority  =4)
	public void test_deleteuser() {
		logger.info("****************Deleting User Info***************");
		
		Response deleteUser = UserEndpoints.DeleteUser(this.userpayload.getUsername());
		deleteUser.then().log().all();
		
        Assert.assertEquals(deleteUser.getStatusCode(), 200);
		
		System.out.println("Username is deleted and Validated Successfully");
		
		logger.info("***************User Info is Deleted***************");
	}
}

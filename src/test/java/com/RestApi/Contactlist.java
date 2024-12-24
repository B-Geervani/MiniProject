package com.RestApi;
import static io.restassured.RestAssured.given;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class Contactlist {
	String tokenvalue;
	String dynamicEmail;
    String contactId ;
	String Logintoken;

  @Test(priority = 1)

  public void AddUser() {
	 
	  HashMap<String,Object>details = new HashMap<String,Object>();
	  details.put("firstName","James");
	  details.put("lastName","kira");
	  dynamicEmail = "Jamekira" + System.currentTimeMillis() + "@gmail.com";
	  details.put("email", dynamicEmail);
	  details.put("password","test@123");	
	  
	  Response res=given()
			  .header("Content-Type","application/json")
			  .header("Accept","application/json")
			  .body(details)
			  
			  .when().post("https://thinking-tester-contact-list.herokuapp.com/users");
	  
	                      res.then().log().body();
                tokenvalue=res.jsonPath().getString("token");

int code=res.getStatusCode();
Assert.assertEquals(code,201);
System.out.println("Generated token is: "+tokenvalue);
System.out.println("AddUser Status code is:"+res.getStatusLine().substring(res.getStatusLine().indexOf(" ")+1));
System.out.println(     ); //to avoid confusion added this line
}
  
  @Test(priority = 2,dependsOnMethods = "AddUser")
public void UserProfile() {
	  
	  Response res= given()
			  .header("Authorization", "Bearer " + tokenvalue)
			  .header("Content-Type","application/json")
			  .header("Accept","application/json")
			  .when().get("https://thinking-tester-contact-list.herokuapp.com/users/me");
	  
	  int code=res.getStatusCode();
      Assert.assertEquals(code,200);
      System.out.println("UserProfile Status code is:"+res.getStatusLine().substring(res.getStatusLine().indexOf(" ")+1));
      res.then().log().body();
      System.out.println(     );
   }
  
  @Test(priority = 3)
  public void UpdateUser() {
	  
	  HashMap<String,Object>Details = new HashMap<String,Object>();
	  Details.put("firstName","James");
	  Details.put("lastName","kira");
	  dynamicEmail ="jameskira"+System.currentTimeMillis()+"@gmail.com";
	  Details.put("email",dynamicEmail);
	  Details.put("password","test@123");	
	  
	  Response res= given()
			  .header("Authorization", "Bearer " + tokenvalue)
			  .header("Content-Type","application/json")
			  .header("Accept","application/json")
			  .body(Details)
			  .when().patch("https://thinking-tester-contact-list.herokuapp.com/users/me");
	  
	  int code=res.getStatusCode();
      Assert.assertEquals(code,200);
      System.out.println("UpdateUser Status code is:"+res.getStatusLine().substring(res.getStatusLine().indexOf(" ")+1));
      res.then().log().body();
      System.out.println(     );
	  }

  @Test(priority = 4)
  public void LogInUser() {
	  
	  HashMap<String, Object> loginDetails = new HashMap<>();
	    loginDetails.put("email", dynamicEmail);
	    loginDetails.put("password", "test@123");
	    
	  Response res= given()
              .header("Content-Type","application/json")
			  .header("Accept","application/json")
			  .body(loginDetails)
			  .when().post("https://thinking-tester-contact-list.herokuapp.com/users/login");
	  
	                         res.then().log().body();
	                     Logintoken = res.jsonPath().getString("token");
  
  int code=res.getStatusCode();
  Assert.assertEquals(code,200);
  System.out.println("Logintoken: " + Logintoken);
  System.out.println("LogInUser Status code is:"+res.getStatusLine().substring(res.getStatusLine().indexOf(" ")+1));
  System.out.println(     );
  }

  @Test(priority = 5,dependsOnMethods = "LogInUser")
  public void AddContact() {
	  
	  Response res= given()
			  .header("Authorization", "Bearer " + Logintoken)
			  .header("Content-Type","application/json")
			  .header("Accept","application/json")
			  .body("{\n"
			  		+ "\"firstName\": \"Limar\",\n"
			  		+ "\"lastName\": \"Max\",\n"
			  		+ "\"birthdate\": \"1998-01-01\",\n"
			  		+ "\"email\": \"limar@gmail.com\",\n"
			  		+ "\"phone\": \"8001435555\",\n"
			  		+ "\"street1\": \"1 Main St.\",\n"
			  		+ "\"street2\": \"Apartment A\",\n"
			  		+ "\"city\": \"Anytown\",\n"
			  		+ "\"stateProvince\": \"KS\",\n"
			  		+ "\"postalCode\": \"12345\",\n"
			  		+ "\"country\": \"USA\"\n"
			  		+ "}")
			  .when().post("https://thinking-tester-contact-list.herokuapp.com/contacts");
	  
	  int code=res.getStatusCode();
	  contactId = res.jsonPath().getString("_id");
	  Assert.assertEquals(code,201);
      System.out.println("AddContact Status code is:"+res.getStatusLine().substring(res.getStatusLine().indexOf(" ")+1));
      res.then().log().body();
      System.out.println(     );
  }
  
  @Test(priority = 6)
  public void GetContactList() {
	  Response res= given()
			  .header("Authorization", "Bearer " +Logintoken)
			  .header("Content-Type","application/json")
			  .header("Accept","application/json")
			  .when().get("https://thinking-tester-contact-list.herokuapp.com/contacts");
	  
	  int code=res.getStatusCode();
      Assert.assertEquals(code,200);
      System.out.println("GetContactList Status code is:"+res.getStatusLine().substring(res.getStatusLine().indexOf(" ")+1));
      res.then().log().body();
      System.out.println(     );
	  }
  
  @Test(priority = 7)
  public void GetContact() {
	  Response res= given()
			  .header("Authorization", "Bearer " + Logintoken)
			  .header("Content-Type","application/json")
			  .header("Accept","application/json")
			  .when().get("https://thinking-tester-contact-list.herokuapp.com/contacts/");
	  
	  int code=res.getStatusCode();
      Assert.assertEquals(code,200);
      System.out.println("GetContact Status code is:"+res.getStatusLine().substring(res.getStatusLine().indexOf(" ")+1));
      res.then().log().body();
      System.out.println(     );
	  }
  
  @Test(priority = 8)
  public void UpdateContact() {
	  Response res= given()
			  .header("Authorization", "Bearer " + Logintoken)
			  .header("Content-Type","application/json")
			  .header("Accept","application/json")
			  .body("{\n"
			  		+ "\"firstName\": \"levi\",\n"
			  		+ "\"lastName\": \"Leo\",\n"
			  		+ "\"birthdate\": \"1999-02-02\",\n"
			  		+ "\"email\": \"levi@gmail.com\",\n"
			  		+ "\"phone\": \"8005554242\",\n"
			  		+ "\"street1\": \"13 School St.\",\n"
			  		+ "\"street2\": \"Apt. 5\",\n"
			  		+ "\"city\": \"Washington\",\n"
			  		+ "\"stateProvince\": \"QC\",\n"
			  		+ "\"postalCode\": \"A1A1A1\",\n"
			  		+ "\"country\": \"Canada\"\n"
			  		+ "}")
			  .when().put("https://thinking-tester-contact-list.herokuapp.com/contacts/"+contactId);
	  
	  int code=res.getStatusCode();
      Assert.assertEquals(code,200);
      System.out.println("UpdateContact Status code is:"+res.getStatusLine().substring(res.getStatusLine().indexOf(" ")+1));
      res.then().log().body();
      System.out.println(     );
	  }

  @Test(priority = 9)
  public void Updatecontactpartial() {
	  Response res= given()
			  .header("Authorization", "Bearer " + Logintoken)
			  .header("Content-Type","application/json")
			  .header("Accept","application/json")
			  .body("{\n"
			  		+ "\"firstName\": \"levi\"\n"
			  		+ "}")
	  
			  .when().patch("https://thinking-tester-contact-list.herokuapp.com/contacts/"+contactId);
	  
	  int code=res.getStatusCode();
      Assert.assertEquals(code,200);
      System.out.println("Updatecontactpar Status code is:"+res.getStatusLine().substring(res.getStatusLine().indexOf(" ")+1));
      String firstName = res.jsonPath().getString("firstName");
      Assert.assertEquals(firstName, "levi");
      System.out.println("firstName: " + firstName);
      res.then().log().body();
      System.out.println(     );
	   }
  
  @Test(priority = 10)
  public void LogoutUser () {
	  Response res= given()
			  .header("Content-Type","application/json")
			  .header("Accept","application/json")
			  .header("Authorization", "Bearer " + Logintoken)
			  .when().post("https://thinking-tester-contact-list.herokuapp.com/users/logout");
	  
	  int code=res.getStatusCode();
      Assert.assertEquals(code,200);
      System.out.println("LogoutUser Status code is:"+res.getStatusLine().substring(res.getStatusLine().indexOf(" ")+1));
      res.then().log().body();
      System.out.println(     );
  }

}

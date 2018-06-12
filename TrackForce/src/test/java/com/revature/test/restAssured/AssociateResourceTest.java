package com.revature.test.restAssured;

import static org.hamcrest.Matchers.hasSize;
import static io.restassured.RestAssured.given;

import java.util.Set;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.revature.model.AssociateInfo;
import com.revature.services.AssociateService;
import com.revature.services.JWTService;


public class AssociateResourceTest {

	AssociateService associateService = new AssociateService();
	Set<AssociateInfo> associates;
	static final String URL = "http://localhost:8085/TrackForce/associates";

	@BeforeClass
	public void beforeClass() {
		associates = associateService.getAllAssociates();
		System.out.println(associates.size());
	}
	
	@Test(priority = 5)
	public void testGetAllAssociates() {
		String token = JWTService.createToken("TestAdmin", 1);
		System.out.println(token);
		
        given().header("Authorization",token).when().get(URL).then().assertThat().statusCode(200);
        given().header("Authorization",token).when().get(URL).then().assertThat().body("firstName", hasSize(associates.size()));     
	}
}

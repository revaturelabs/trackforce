package com.revature.test.restAssured;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.revature.entity.TfTrainer;
import com.revature.services.JWTService;
import com.revature.services.TrainerService;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;

public class TrainerResourceTest {
    
    TrainerService TrainerService = new TrainerService();
    List<TfTrainer> trainers;
    String token;

    static final String URL = "http://52.87.205.55:8086/TrackForce/trainers";

    @BeforeClass
    public void beforeClass() {
        token = JWTService.createToken("TestAdmin", 1);
        System.out.println(token);
        trainers = TrainerService.getAllTrainers();
    }
    
    @Test(priority=1)
    public void testGetAllTrainers() {
        Response response = given().header("Authorization", token).when().get(URL).then().extract().response();
        
        assertTrue(response.getStatusCode()==204);
        assertTrue(response.getContentType().equals(MediaType.APPLICATION_JSON));
        
        given().header("Authorization", "Bad Token").when().get(URL).then().assertThat().statusCode(401);

        given().header("Authorization", "Bad Token").when().get(URL + "/notAURL").then().assertThat().statusCode(404);
    }
    
    @Test(priority=2)
    public void testGetTrainer() {
        Response response = given().header("Authorization",token).when().get(URL + "/" + 1).then().extract().response();
        assertTrue(response.getStatusCode() == 204);
        
        given().header("Authorization", "Bad Token").when().get(URL + "/" + 1).then().assertThat().statusCode(401);

        given().header("Authorization", "Bad Token").when().get(URL + "/notAURL").then().assertThat().statusCode(404);
    }
    
    @Test(priority=3)
    public void getTrainerPrimaryBatches() {
        Response response = given().headers("Authorization",token).when().get(URL + "/" + 1 + "/batch").then().extract().response();
        assertTrue(response.getStatusCode() == 204);
        
        given().header("Authorization", "Bad Token").when().get(URL + "/" + 1 + "/batch").then().assertThat().statusCode(401);

        given().header("Authorization", "Bad Token").when().get(URL + "/notAURL").then().assertThat().statusCode(404);
        
    }
    
    @Test(priority=4)
    public void getTrainerCotrainerBatch() {
        Response response = given().headers("Authorization",token).when().get(URL + "/" + 1 + "/cotrainerbatch").then().extract().response();
        assertTrue(response.getStatusCode()==204);
        
        given().header("Authorization", "Bad Token").when().get(URL + "/" + 1 + "/cotrainerbatch").then().assertThat().statusCode(401);

        given().header("Authorization", "Bad Token").when().get(URL + "/notAURL").then().assertThat().statusCode(404);
    }

}

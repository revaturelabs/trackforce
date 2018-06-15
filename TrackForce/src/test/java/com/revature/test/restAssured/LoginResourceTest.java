package com.revature.test.restAssured;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.revature.services.JWTService;

public class LoginResourceTest {

    static final String URL = "http://localhost:8085/TrackForce/users";
    String token;

    @BeforeClass
    public void beforeClass() {
        token = JWTService.createToken("TestAdmin", 1);
        System.out.println(token);
    }

    @Test
    public void submitCredentials() {
        given().contentType("application/json").body(
                "{ \"username\": \"TestAdmin\", \"password\": \"TestAdmin\"}")
                .post(URL + "/login").then().assertThat().statusCode(200);
    }
}
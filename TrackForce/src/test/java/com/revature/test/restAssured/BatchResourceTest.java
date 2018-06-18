package com.revature.test.restAssured;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.revature.services.BatchService;
import com.revature.services.JWTService;
import io.restassured.response.Response;
public class BatchResourceTest {
    static final String URL = "http://localhost:8085/TrackForce/batches";
    private String token;
    BatchService service ;
    @BeforeClass
    public void beforeClass() {
        token = JWTService.createToken("TestAdmin", 1);
        System.out.println(token);
        service = new BatchService();
    }
    @Test(priority = 1)
    public void getAllBatchesTest() {
        int size = service.getAllBatches().size();
        Response response = given().header("Authorization", token).when().get(URL).then().extract().response();
        assertTrue(response.getStatusCode() == 200);
        assertTrue(response.contentType().equals("application/json"));
        given().header("Authorization", token).when().get(URL).then().assertThat().body("batchName", Matchers.hasSize(size));
        
        token = JWTService.createToken("TestSales", 2);
        response = given().header("Authorization", token).when().get(URL).then().extract().response();
        assertTrue(response.getStatusCode() == 200);
        assertTrue(response.contentType().equals("application/json"));
        token = JWTService.createToken("TestManger", 3);
        response = given().header("Authorization", token).when().get(URL).then().extract().response();
        assertTrue(response.getStatusCode() == 200);
        assertTrue(response.contentType().equals("application/json"));
        token = JWTService.createToken("TestTrainer", 4);
        response = given().header("Authorization", token).when().get(URL).then().extract().response();
        assertTrue(response.getStatusCode() == 200);
        assertTrue(response.contentType().equals("application/json"));
    }
    @Test(priority = 2)
    public void getAllBatchesBackwardsDateTest() {
        Response response = given().header("Authorization", token).queryParam("start", 1600000000000L)
                .queryParam("start", 1490000000000L).when().get(URL).then().extract().response();
        assertTrue(response.getStatusCode() == 200);
        assertTrue(response.contentType().equals("application/json"));
    }
    @Test(priority = 2)
    public void getBatchesInARangeTest() {
        Response response = given().header("Authorization", token).queryParam("start", 1480000000000L)
                .queryParam("start", 1490000000000L).when().get(URL).then().extract().response();
        assertTrue(response.getStatusCode() == 200);
        assertTrue(response.contentType().equals("application/json"));
    }
    @Test(priority = 3)
    public void getAllBatchesInvalidAuthorizationTest() {
        Response response = given().header("Authorization", "NotAuthorization").when().get(URL).then().extract()
                .response();
        assertTrue(response.getStatusCode() == 401);
    }
    @Test(priority = 4)
    public void getAllBatchesUnauthorizedTest() {
        token = JWTService.createToken("TestAssociate", 5);
        Response response = given().header("Authorization", token).when().get(URL).then().extract()
                .response();
        System.out.println(response.getStatusCode());
        assertTrue(response.getStatusCode() == 403);
        System.out.println(response.contentType());
        assertFalse(response.contentType().equals("application/json"));
        given().header("Authorization", token).when().get(URL).then().assertThat().body("batchName", Matchers.hasSize(0));
    }
    @Test(priority = 5)
    public void getBatchByIdTest() {
        token = JWTService.createToken("TestAdmin", 1);
        Response response = given().header("Authorization", token).pathParam("id", "14").when().get(URL + "/{id}")
                .then().extract().response();
        assertTrue(response.getStatusCode() == 200);
        assertTrue(response.contentType().equals("application/json"));
        token = JWTService.createToken("TestSales", 2);
        response = given().header("Authorization", token).pathParam("id", "14").when().get(URL + "/{id}").then()
                .extract().response();
        assertTrue(response.getStatusCode() == 200);
        assertTrue(response.contentType().equals("application/json"));
        token = JWTService.createToken("TestManger", 3);
        response = given().header("Authorization", token).pathParam("id", "14").when().get(URL + "/{id}").then()
                .extract().response();
        assertTrue(response.getStatusCode() == 200);
        assertTrue(response.contentType().equals("application/json"));
        token = JWTService.createToken("TestTrainer", 4);
        response = given().header("Authorization", token).pathParam("id", "14").when().get(URL + "/{id}").then()
                .extract().response();
        assertTrue(response.getStatusCode() == 200);
        assertTrue(response.contentType().equals("application/json"));
    }
    @Test(priority = 6)
    public void getBatchByBadIdTest() {
        Response response = given().header("Authorization", token).pathParam("id", "-14").when().get(URL + "/{id}")
                .then().extract().response();
        assertTrue(response.getStatusCode() == 200);
    }
    @Test(priority = 7)
    public void getBatchByIdInvalidAuthorizationTest() {
        Response response = given().header("Authorization", "NotAuthorization").pathParam("id", "14").when()
                .get(URL + "/{id}").then().extract().response();
        assertTrue(response.getStatusCode() == 401);
    }
    @Test(priority = 8)
    public void getBatchByIdUnauthorizationTest() {
        token = JWTService.createToken("TestAssociate", 5);
        Response response = given().header("Authorization", token).pathParam("id", "14").when()
                .get(URL + "/{id}").then().extract().response();
        assertTrue(response.getStatusCode() == 401);
    }
    @Test(priority = 9)
    public void getAssociatesByBatchIdTest() {
        token = JWTService.createToken("TestAdmin", 1);
        Response response = given().header("Authorization", token).pathParam("id", "14").when()
                .get(URL + "/{id}/associates").then().extract().response();
        assertTrue(response.getStatusCode() == 200);
        assertTrue(response.contentType().equals("application/json"));
        token = JWTService.createToken("TestSales", 2);
        response = given().header("Authorization", token).pathParam("id", "14").when().get(URL + "/{id}/associates")
                .then().extract().response();
        assertTrue(response.getStatusCode() == 200);
        assertTrue(response.contentType().equals("application/json"));
        token = JWTService.createToken("TestManger", 3);
        response = given().header("Authorization", token).pathParam("id", "14").when().get(URL + "/{id}/associates")
                .then().extract().response();
        assertTrue(response.getStatusCode() == 200);
        assertTrue(response.contentType().equals("application/json"));
        token = JWTService.createToken("TestTrainer", 4);
        response = given().header("Authorization", token).pathParam("id", "14").when().get(URL + "/{id}/associates")
                .then().extract().response();
        assertTrue(response.getStatusCode() == 200);
        assertTrue(response.contentType().equals("application/json"));
    }
    @Test(priority = 10)
    public void getAssociateByBadBatchIdTest() {
        Response response = given().header("Authorization", token).pathParam("id", "-14").when()
                .get(URL + "/{id}/associates").then().extract().response();
        assertTrue(response.getStatusCode() == 200);
    }
    @Test(priority = 11)
    public void getAssociateByBatchIdInvalidAuthorizationTest() {
        Response response = given().header("Authorization", "NotAuthorization").pathParam("id", "14").when()
                .get(URL + "/{id}/associates").then().extract().response();
        assertTrue(response.getStatusCode() == 401);
    }
    @Test(priority = 8)
    public void getAssociateByBatchIdUnauthorizationTest() {
        token = JWTService.createToken("TestAssociate", 5);
        Response response = given().header("Authorization", "NotAuthorization").pathParam("id", "14").when()
                .get(URL + "/{id}/associates").then().extract().response();
        assertTrue(response.getStatusCode() == 401);
    }
}
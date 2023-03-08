package testt;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class TestHelp {

    public static Response getApi(String endpoint){

        Response response = given()
                .baseUri("https://reqres.in")
                .when()
                .log().all()
                .get(endpoint);
        return response;
    }


    public static Response apiRegister(String endpoint, String login, String pass){

        String requestBody = "{\n" +
                "    \"email\": \"" + login + "\",\n" +
                "    \"password\": \"" + pass + "\"\n" +
                "}";

        Response response = RestAssured.given()
                .baseUri("https://reqres.in")
                .contentType("application/json")
                .body(requestBody)
                .post(endpoint);
        System.out.println(response.getBody().asString());
        return response;
    }

    public static void assertionResponse(Response response){

        SoftAssert softAssert = new SoftAssert();
        JsonPath jsonPath = new JsonPath(response.getBody().asString());

        Assert.assertEquals(response.getStatusCode(),200, "status code does not ");


         /*try{
            Assert.assertEquals(statusCode,200);
        }catch(AssertionError err){
            System.out.println("Status code does not match expected value 200");
            return;
        }*/
        softAssert.assertFalse(response.getBody().asString().isEmpty(), "Response body is empty");
        softAssert.assertTrue(jsonPath.get("id") != null && !jsonPath.get("id").toString().isEmpty() &&
                jsonPath.get("token") != null && !jsonPath.get("token").toString().isEmpty(), "one of expected attributes ");
    }






    public static void main(String[] args) {}

}


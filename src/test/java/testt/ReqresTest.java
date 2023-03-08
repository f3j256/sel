package testt;


import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static testt.TestHelp.*;


public class ReqresTest {

    @Test
    public void getUserTest() {
        Response response = getApi("/api/users/2");
        String firstName = response.jsonPath().getString("data.first_name");
        Assert.assertEquals(firstName, "Janet");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }

    @Test
    public void checkRegister(){
        Response response1 = apiRegister("/api/register", "michael.lawson@reqres.in", "deadred");
        assertionResponse(response1);

        Response response2 = apiRegister("/api/register", "michael.lawson@reqres.in", "");
        assertionResponse(response2);
    }

    @Test
    public void checkDelay(){
        Response response = getApi("/api/users?delay=5");
        long responseTimeInMilliseconds = response.timeIn(TimeUnit.MILLISECONDS);

        System.out.println("Response time in milliseconds: " + responseTimeInMilliseconds);
    }



}

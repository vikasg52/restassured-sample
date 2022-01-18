package SampleTest.tests;

import SampleTest.util.Utility;
import io.qameta.allure.Description;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import static io.restassured.RestAssured.given;

public class CreateBookingTest
{
    private RequestSpecification requestSpec;

    @BeforeMethod
    public void createRequestSpecification() {

        requestSpec = new RequestSpecBuilder().
                setBaseUri("https://restful-booker.herokuapp.com").
                setContentType(ContentType.JSON).
                build();
    }

    /*******************************************************
     * Send a post request to /booking
     * and check that the response has HTTP status code 200
     ******************************************************/

    @Description("Validate that create booking API is working and returning status code 200")
    @Test(priority =1)
    public void CreateBooking_CheckResponseCode_expect200() throws IOException {
        byte[] jsonData = Files.readAllBytes(Paths.get("./src/test/data/createUser.json"));
        given().
                spec(requestSpec).
                when().
                body(jsonData).
                log().uri().
                post("/booking").
                then().
                assertThat().
                statusCode(200);
    }

    /*******************************************************
     * Send a post request to /booking
     * and check that the response has String bookingId with
     * length>0
     ******************************************************/

    @Description("Validate that create booking API is working and returning status code 200")
    @Test(priority =2)
    public void CreateBooking_CheckBookingId() throws IOException {
        SoftAssert asert = new SoftAssert();
        Response response = Utility.createBooking(requestSpec);
        String bookingId = Utility.getData(response,"bookingid");
        asert.assertTrue(bookingId.length()>0,"bookingid value is null or less than 0");
        asert.assertAll();
    }

}


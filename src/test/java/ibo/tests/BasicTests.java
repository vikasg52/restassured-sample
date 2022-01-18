package ibo.tests;

import dataentities.Login;
import ibo.util.Utility;
import io.qameta.allure.Description;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static ibo.util.Utility.getToken;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class BasicTests {
    private RequestSpecification requestSpec;

    @BeforeMethod
    public void createRequestSpecification() {

        requestSpec = new RequestSpecBuilder().
                setBaseUri("https://restful-booker.herokuapp.com").
                setContentType(ContentType.JSON).
                build();
    }

    /*******************************************************
     * Send a GET request to /auth
     * and check that the response has HTTP status code 200
     ******************************************************/

    @Description("Validate that login API is working and returning status code 200")
    @Test(priority =0)
    public void requestToken_CheckResponseCode_expect200() {
        Login login = new Login("admin", "password123");
        given().
                spec(requestSpec).
                when().
                body(login).
                log().uri().
                post("/auth").
                then().
                log().body().
                assertThat().
                statusCode(200);
    }

    /*******************************************************
     * Send a GET request to /auth
     * and check that the response has string, token
     ******************************************************/

    @Description("Validate that login API is working and returning token string in response")
    @Test
    public void requestToken_CheckResponseHas_String_Token() {
        Login login = new Login("admin", "password123");
        given().
                spec(requestSpec).
                when().
                body(login).
                log().uri().
                log().body().
                post("/auth").
                then().
                log().body().
                assertThat().
                body(containsString("token"));
    }

    /*******************************************************
     * Send a GET request to /auth
     * and check that the response string token is not empty
     ******************************************************/

    @Description("Validate that login API is working and token string length is greater than 0")
    @Test()
    public void requestToken_CompareLength_Greater_ThankZero() {
        SoftAssert validate = new SoftAssert();
        String tokenResponse = getToken(Utility.getResponseBody(requestSpec));
        validate.assertTrue(!tokenResponse.isEmpty(),"Response token is empty");
        validate.assertAll();
    }
}


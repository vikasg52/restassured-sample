package ibo.util;

import dataentities.Login;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Base64;

import static io.restassured.RestAssured.given;


public class Utility {
//    public static String username = "admin";
//    public static String password = "password123";

//    public static String encode(String str1, String str2) {
//        return new String(Base64.getEncoder().encode((str1 + ":" + str2).getBytes()));
//    }


    @Step("Send Login API call and return the response body")
    public static Response getResponseBody(RequestSpecification requestSpec ) {
        Login login = new Login("admin", "password123");
        return
        given().
                spec(requestSpec).
                when().
                body(login).
                log().uri().
                post("/auth").
                then().
                log().body().
                statusCode(200).
                extract().
                response();
    }


    @Step("Fetch the token string from the response body")
    public static String getToken(Response loginResponse) {
        return loginResponse.jsonPath().getString("token");
    }


}

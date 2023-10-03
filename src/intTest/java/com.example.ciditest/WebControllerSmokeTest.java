package com.example.ciditest;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class WebControllerSmokeTest {

    @BeforeAll
    public static void setUpClass() {
        RestAssured.baseURI = "http://localhost:8080";
    }

    @Test
    public void shouldReturnHtmlContainingTitleOfIndexPageWhenAccessingRoot() {

        given()
                .when()
                .get("/")
                .then()
                .statusCode(200)
                .body("html.head.title", equalTo("Chat 1.0"));
    }

    @Test
    public void shouldReturnHtmlContainingTitleOfIndexPageWhenSendingNewMessage() {

            given()
                    .when()
                    .get("/addmessage?username=Username&message=Message")
                    .then()
                    .statusCode(200)
                    .body("html.head.title", equalTo("Chat 1.0"));
    }


}

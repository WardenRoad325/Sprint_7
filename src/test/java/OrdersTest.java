import io.restassured.RestAssured;
import org.example.CreateOrderRequest;
import org.junit.Before;
import org.junit.Test;
import junitparams.JUnitParamsRunner;
import org.junit.runner.RunWith;
import junitparams.Parameters;

import java.util.Arrays;
import java.util.Collections;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(JUnitParamsRunner.class)
public class OrdersTest {
    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
    }

    @Test
    @Parameters({"BLACK", "GREY"})
    public void createOrderWithBothColors(String color) {
        CreateOrderRequest orderData = new CreateOrderRequest("Naruto", "Uchiha", "Konoha, 142 apt.", Arrays.asList("BLACK", "GREY"), "+7 800 355 35 35", "Saske, come back to Konoha", 5, "2020-06-06", 4);

        given()
                .contentType("application/json")
                .body(orderData)
                .when()
                .post("/api/v1/orders")
                .then()
                .statusCode(201)
                .body("track", notNullValue());
    }

    @Test
    public void createOrderWithNoColor() {
        CreateOrderRequest orderData = new CreateOrderRequest("Naruto", "Uchiha", "Konoha, 142 apt.", Collections.emptyList(), "+7 800 355 35 35", "Saske, come back to Konoha", 5, "2020-06-06", 4);

        given()
                .contentType("application/json")
                .body(orderData)
                .when()
                .post("/api/v1/orders")
                .then()
                .statusCode(201)
                .body("track", notNullValue());
    }
}






package Helper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.example.CreateOrderRequest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class OrderApi {
    public OrderApi() {
        // Инициализация базового URI для API
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
    }

    public void createOrder(CreateOrderRequest orderData) {
        Response response = given()
                .contentType("application/json")
                .body(orderData)
                .when()
                .post("/api/v1/orders");

        response.then()
                .statusCode(201)
                .body("track", notNullValue());
    }
}


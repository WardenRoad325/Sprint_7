package helper;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.example.CreateOrderRequest;

import static io.restassured.RestAssured.given;

public class OrderApi {
    private static final String BASE_URI = "https://qa-scooter.praktikum-services.ru";
    private static final String ORDER_API_PATH = "/api/v1/orders";

    public OrderApi() {
        // Инициализация базового URI для API
        RestAssured.baseURI = BASE_URI;
    }

    public Response sendCreateOrderRequest(CreateOrderRequest orderData) {
        return given()
                .baseUri(BASE_URI)
                .contentType("application/json")
                .body(orderData)
                .when()
                .post(ORDER_API_PATH);
    }

    public Response sendGetOrderListRequest() {
        return given()
                .baseUri(BASE_URI)
                .contentType("application/json")
                .when()
                .get(ORDER_API_PATH);
    }
}




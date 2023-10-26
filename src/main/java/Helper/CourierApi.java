package Helper;

import io.restassured.response.Response;
import org.example.LoginCourier;
import org.example.RegisterCourier;

import static io.restassured.RestAssured.given;

public class CourierApi {
    private static final String BASE_URI = "https://qa-scooter.praktikum-services.ru";
    private static final String COURIER_API_PATH = "/api/v1/courier";

    public static Response registerCourier(RegisterCourier courierData) {
        return given()
                .baseUri(BASE_URI)
                .contentType("application/json")
                .body(courierData)
                .when()
                .post(COURIER_API_PATH);
    }

    public static Response loginCourier(LoginCourier loginData) {
        return given()
                .baseUri(BASE_URI)
                .contentType("application/json")
                .body(loginData)
                .when()
                .post(COURIER_API_PATH + "/login");
    }

    public static Response deleteCourier(String courierId) {
        return given()
                .baseUri(BASE_URI)
                .when()
                .delete(COURIER_API_PATH + "/" + courierId);
    }
}


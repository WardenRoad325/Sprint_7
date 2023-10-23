import io.restassured.RestAssured;
import org.example.LoginCourier;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.notNullValue;

public class LoginCourierTest {
    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
    }

    @Test
    public void validCourierAuthorization() {
        LoginCourier validCourier = new LoginCourier("dse12346", "1234");
        given()
                .contentType("application/json")
                .body(validCourier)
                .when()
                .post("/api/v1/courier/login")
                .then()
                .statusCode(200)
                .body("id", notNullValue());
    }

    @Test
    public void wrongPasswordAuthorization() {
        LoginCourier wrongPasswordCourier = new LoginCourier("dse12346", "wrongpassword");
        given()
                .contentType("application/json")
                .body(wrongPasswordCourier)
                .when()
                .post("/api/v1/courier/login")
                .then()
                .statusCode(404); // Проверьте правильный код ошибки
    }

    @Test
    public void missingPasswordAuthorization() {
        LoginCourier missingPasswordCourier = new LoginCourier("dse12346","");
        given()
                .contentType("application/json")
                .body(missingPasswordCourier)
                .when()
                .post("/api/v1/courier/login")
                .then()
                .statusCode(400); // Проверьте правильный код ошибки
    }

    @Test
    public void missingUsernameAuthorization() {
        LoginCourier missingUsernameCourier = new LoginCourier("", "1234");
        given()
                .contentType("application/json")
                .body(missingUsernameCourier)
                .when()
                .post("/api/v1/courier/login")
                .then()
                .statusCode(400);
    }

    @Test
    public void nonExistentUserAuthorization() {
        LoginCourier nonExistentUser = new LoginCourier("nonexistentuser", "password");
        given()
                .contentType("application/json")
                .body(nonExistentUser)
                .when()
                .post("/api/v1/courier/login")
                .then()
                .statusCode(404);
    }
}




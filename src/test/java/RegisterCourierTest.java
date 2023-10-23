import io.restassured.RestAssured;
import org.example.RegisterCourier;
import org.example.LoginCourier;
import org.junit.Before;
import org.junit.Test;
import com.github.javafaker.Faker;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class RegisterCourierTest {

    private Faker faker;
    private String registeredCourierId;

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
        faker = new Faker();
    }

    @Test
    public void testRegisterAndDeleteCourierWithRandomData() {
        String randomUsername = faker.name().username();
        String randomPassword = faker.internet().password();
        String randomName = faker.name().fullName();

        // Регистрация курьера
        RegisterCourier courierData = new RegisterCourier(randomUsername, randomPassword, randomName);
        given()
                .contentType("application/json")
                .body(courierData)
                .when()
                .post("/api/v1/courier")
                .then()
                .statusCode(201)
                .body("ok", equalTo(true));

        // Логин курьера и получение id
        LoginCourier loginCourier = new LoginCourier(randomUsername, randomPassword);
        registeredCourierId = Integer.toString(given()
                .contentType("application/json")
                .body(loginCourier)
                .when()
                .post("/api/v1/courier/login")
                .then()
                .statusCode(200)
                .body("id", notNullValue())
                .extract()
                .path("id"));



        // Удаление курьера
        given()
                .when()
                .delete("/api/v1/courier/" + registeredCourierId)
                .then()
                .statusCode(200); // Предположим, что успешное удаление возвращает статус 204
    }
}













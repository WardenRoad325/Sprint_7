import Helper.CourierApi;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.example.LoginCourier;
import org.example.RegisterCourier;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Description;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@Epic("Courier Registration")
@Feature("Registration Feature")
public class RegisterCourierTest {

    private Faker faker;
    private Integer registeredCourierId;

    @Before
    public void setUp() {
        faker = new Faker();
    }

    @Test
    @Description("This test registers a courier with random data and then deletes the courier.")
    public void testRegisterAndDeleteCourierWithRandomData() {
        String randomUsername = faker.name().username();
        String randomPassword = faker.internet().password();
        String randomName = faker.name().fullName();

        RegisterCourier courierData = new RegisterCourier(randomUsername, randomPassword, randomName);

        // Регистрация курьера
        Response registerResponse = CourierApi.registerCourier(courierData);
        registerResponse
                .then()
                .statusCode(SC_CREATED)
                .body("ok", equalTo(true));

        // Логин курьера и получение ID
        LoginCourier loginData = new LoginCourier(randomUsername, randomPassword);
        Response loginResponse = CourierApi.loginCourier(loginData);
        loginResponse
                .then()
                .statusCode(SC_OK)
                .body("id", notNullValue());
        registeredCourierId = loginResponse.path("id");
    }

    @After
    public void tearDown() {
        // Вызываем удаление курьера, чтобы гарантировать очистку ресурсов
        if (registeredCourierId != null) {
            Response deleteResponse = CourierApi.deleteCourier(String.valueOf(registeredCourierId));
            deleteResponse
                    .then()
                    .statusCode(SC_OK);
        }
    }
}

















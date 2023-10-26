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
import io.qameta.allure.Story;
import io.qameta.allure.Description;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@Epic("Courier Login")
@Feature("Login Tests")
public class LoginCourierTest {

    private Faker faker;
    private Integer registeredCourierId;
    private String randomUsername;

    @Before
    public void setUp() {
        faker = new Faker();
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

    @Test
    @Story("Register and Login Courier")
    @Description("Test for registering and logging in a courier")
    public void testRegisterAndLoginCourier() {
        randomUsername = faker.name().username();
        String randomPassword = faker.internet().password();
        String randomName = faker.name().fullName();

        RegisterCourier courierData = new RegisterCourier(randomUsername, randomPassword, randomName);

        // Регистрация курьера
        Response registerResponse = CourierApi.registerCourier(courierData);
        registerResponse
                .then()
                .statusCode(SC_CREATED)
                .body("ok", equalTo(true));

        registeredCourierId = registerResponse.path("id");

        // Логин курьера и проверка
        LoginCourier loginData = new LoginCourier(randomUsername, randomPassword);
        Response loginResponse = CourierApi.loginCourier(loginData);
        loginResponse
                .then()
                .statusCode(SC_OK)
                .body("id", notNullValue());
    }

    @Test
    @Story("Login with Wrong Password")
    @Description("Test for logging in with the wrong password")
    public void testLoginWithWrongPassword() {
        // Тест для логина с неправильным паролем
        LoginCourier loginData = new LoginCourier(randomUsername, "wrong_password");
        Response loginResponse = CourierApi.loginCourier(loginData);
        loginResponse
                .then()
                .statusCode(SC_BAD_REQUEST); // Подставьте правильный код ошибки
    }

    @Test
    @Story("Login with Missing Password")
    @Description("Test for logging in with a missing password")
    public void testLoginWithMissingPassword() {
        // Тест для логина с отсутствующим паролем
        LoginCourier loginData = new LoginCourier(randomUsername, "");
        Response loginResponse = CourierApi.loginCourier(loginData);
        loginResponse
                .then()
                .statusCode(SC_BAD_REQUEST);
    }

    @Test
    @Story("Login with Missing Username")
    @Description("Test for logging in with a missing username")
    public void testLoginWithMissingUsername() {
        // Тест для логина с отсутствующим именем пользователя
        LoginCourier loginData = new LoginCourier("", "password");
        Response loginResponse = CourierApi.loginCourier(loginData);
        loginResponse
                .then()
                .statusCode(SC_BAD_REQUEST);
    }

    @Test
    @Story("Login with Non-Existent User")
    @Description("Test for logging in with a non-existent user")
    public void testLoginWithNonExistentUser() {
        // Тест для логина с несуществующим пользователем
        LoginCourier loginData = new LoginCourier("nonexistentuser", "password");
        Response loginResponse = CourierApi.loginCourier(loginData);
        loginResponse
                .then()
                .statusCode(SC_NOT_FOUND);
    }
}








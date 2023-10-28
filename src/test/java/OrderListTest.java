import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Description;
import io.restassured.response.Response; // Импорт класса Response
import helper.OrderApi; // Импорт класса OrderApi

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.notNullValue;

@Epic("Order List")
@Feature("Check Order List")
public class OrderListTest {
    private final OrderApi orderApi = new OrderApi(); // Создание экземпляра OrderApi

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
    }

    @Test
    @Description("Check the list of orders")
    public void checkOrderList() {
        // Используйте метод sendGetOrderListRequest из OrderApi
        Response response = orderApi.sendGetOrderListRequest();

        response.then()
                .statusCode(SC_OK)
                .body("orders", notNullValue());
    }
}



import Helper.OrderApi;
import io.restassured.RestAssured;
import org.example.CreateOrderRequest;
import org.junit.Before;
import org.junit.Test;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.runner.RunWith;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Description;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@RunWith(JUnitParamsRunner.class)
@Epic("Orders")
@Feature("Create Order")
public class OrdersTest {
    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
    }

    // Создаем объект OrderApi для работы с заказами
    private final OrderApi orderApi = new OrderApi();

    @Test
    @Parameters({"BLACK", "GREY", "BLACK, GREY", ""})
    @Description("Create an order with specified colors")
    public void createOrderWithColors(String... colors) {
        // Объединяем параметры в одну строку через запятую и пробел
        String colorsString = String.join(", ", colors);

        CreateOrderRequest orderData = new CreateOrderRequest("Naruto", "Uchiha", "Konoha, 142 apt.",
                parseColors(colorsString),
                "+7 800 355 35 35",
                "Saske, come back to Konoha", 5,
                "2020-06-06", 4);

        // Используем метод createOrder из OrderApi для создания заказа
        orderApi.createOrder(orderData);
    }

    private List<String> parseColors(String colorsString) {
        // Проверяем, если colorsString пуст или null, заменяем его на пустой список
        return (colorsString == null || colorsString.isEmpty()) ? Collections.emptyList() : Arrays.asList(colorsString.split(",\\s*"));
    }
}












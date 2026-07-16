import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static page.MainPage.URL_MAIN;

public class Helper {
    public static String accessToken;
    public static String email;
    public static String password;

    @Step("Создать пользователя")
    public static void createUserApi() {
        RestAssured.baseURI = URL_MAIN;
        Faker faker = new Faker();

        email = faker.name().lastName() + "_" + System.currentTimeMillis() + "@yandex.ru";
        password = "password";
        String name = faker.name().firstName();

        String jsonBody = String.format(
                "{\"name\":\"%s\",\"email\":\"%s\",\"password\":\"%s\"}",
                name, email, password
        );

        Response response = given()
                .contentType(ContentType.JSON)
                .body(jsonBody)
                .log().all()
                .when()
                .post("/api/auth/register");
           accessToken = JsonPath.from(response.asString()).getString("accessToken");
    }

    @Step("Удалить пользователя")
    public static void deleteUser() {
        RestAssured.baseURI = URL_MAIN;
        given()
                .header("Authorization", accessToken)
                .contentType(ContentType.JSON)
                .log().ifValidationFails()
                .when()
                .delete("/api/auth/user");
    }
}
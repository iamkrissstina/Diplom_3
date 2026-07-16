import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import model.LoginUser;
import model.UserModelCreate;

import static data.UserData.*;
import static io.restassured.RestAssured.given;
import static page.MainPage.URL_MAIN;

public class Helper {
    public static String accessToken;
    public static String email;
    public static String password;

    @Step("Создать пользователя")
    public static Response createUser(UserModelCreate user) {
        return given()
                .header("Content-type", "application/json")
                .body(user)
                .log().all()
                .when()
                .post(CREATE_USER_PATH)
                .then()
                .extract().response();
    }

    @Step("Авторизация пользователя")
    public static Response loginUser(LoginUser loginUser) {
        return given()
                .header("Content-type", "application/json")
                .body(loginUser)
                .log().all()
                .when()
                .post(LOGIN_USER_PATH)
                .then()
                .extract().response();
    }

    @Step("Получить accessToken через логин")
    public static String getAccessToken(LoginUser loginUser) {
        return accessToken = loginUser(loginUser)
                .then()
                .extract()
                .path("accessToken");
    }
    @Step("Удалить пользователя")
    public static Response deleteUser(LoginUser loginUser) {
        return given()
                .header("Authorization", getAccessToken(loginUser))
                .header("Content-type", "application/json")
                .when()
                .delete(DELETE_USER_PATH)
                .then()
                .extract().response();
    }
}
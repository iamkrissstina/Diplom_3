import com.github.javafaker.Faker;
import data.UserData;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import model.LoginUser;
import model.UserModelCreate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import page.LoginPage;
import page.RegisterPage;

import static data.UserData.generateValidUser;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.Assert.assertTrue;
import static page.MainPage.URL_MAIN;


public class RegisterTests extends BaseTestUI {
    UserModelCreate user;

    @Before
    public void createDataUser() {
        RestAssured.baseURI = URL_MAIN;
        user = generateValidUser();
    }
    @After
    public void tearDown() {
        if(user != null) {
            LoginUser loginUser = new LoginUser(user.getEmail(), user.getPassword());
            if (Helper.loginUser(loginUser).statusCode() == SC_OK) {
                Helper.deleteUser(loginUser);
            }
        }
    }
    @DisplayName("Успешная регистрация нового пользователя")
    @Description("Проверка сценария успешной регистрации: ввод валидных данных, переход на страницу логина.")
    @Test
    public void successRegister() {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.openRegisterPage();
        registerPage.registerUser(user.getName(), user.getEmail(), user.getPassword());
        LoginPage loginPage = new LoginPage(driver);
        assertTrue(loginPage.isLoginPageOpened());
    }
    @DisplayName("Ошибка регистрации при неверном пароле")
    @Description("Проверка обработки ошибки при регистрации: ввод пароля меньше 6 символов, подтверждение отображения сообщения об ошибке.")
    @Test
    public void ErrorRegister_WrongPassword() {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.openRegisterPage();
        registerPage.registerUser(user.getName(), user.getEmail(), "pass");
        assertTrue( registerPage.errorPassword().isDisplayed());
    }
}

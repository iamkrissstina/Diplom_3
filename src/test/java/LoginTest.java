
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import model.LoginUser;
import model.UserModelCreate;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import page.LoginPage;
import page.MainPage;
import page.RecoverPasswordPage;
import page.RegisterPage;

import static data.UserData.generateValidUser;
import static org.apache.http.HttpStatus.SC_OK;
import static page.MainPage.URL_MAIN;


public class LoginTest extends BaseTestUI {
    UserModelCreate user;
    LoginUser loginUser;
    @Before
    public void createUser() {
        RestAssured.baseURI = URL_MAIN;
        user = generateValidUser();
         Helper.createUser(user);
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
    // вход по кнопке «Войти в аккаунт» на главной;
    @DisplayName("Вход через кнопку «Войти в аккаунт» на главной странице")
    @Description("Проверка сценария входа: переход на главную, клик по кнопке «Войти в аккаунт», ввод логина/пароля, проверка перехода на главную страницу и видимости кнопки создания заказа.")
@Test
    public void loginAccountBtn() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.clickLoginAccountBtn();
        LoginPage loginPage = new LoginPage(driver);
        if (loginPage.isLoginPageOpened()) {
            loginPage.login(user.getEmail(), user.getPassword());
            Assert.assertTrue(mainPage.createOrderBtn().isDisplayed());
        }
    }
//вход через кнопку «Личный кабинет»;
@DisplayName("Вход через кнопку «Личный кабинет» на главной странице")
@Description("Проверка альтернативного сценария входа: переход на главную, клик по кнопке «Личный кабинет», ввод логина/пароля, проверка перехода на главную страницу и видимости кнопки создания заказа.")
    @Test
    public void loginPersonalAccountBtn() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.clickPersonalAccountBtn();
        LoginPage loginPage = new LoginPage(driver);
    if (loginPage.isLoginPageOpened()) {
        loginPage.login(user.getEmail(), user.getPassword());
        Assert.assertTrue(mainPage.createOrderBtn().isDisplayed());
    }
    }
    //вход через кнопку в форме регистрации;
    @DisplayName("Вход через кнопку на странице регистрации")
    @Description("Проверка входа из формы регистрации: открытие страницы регистрации, клик по ссылке/кнопке «Войти», ввод логина/пароля, проверка перехода на главную и видимости кнопки заказа.")
    @Test
    public void loginOnRegisterPage() {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.openRegisterPage();
        registerPage.clickLoginBtn_onRegisterPage();
        LoginPage loginPage = new LoginPage(driver);
        if (loginPage.isLoginPageOpened()) {
            loginPage.login(user.getEmail(), user.getPassword());
            MainPage mainPage = new MainPage(driver);
            Assert.assertTrue(mainPage.createOrderBtn().isDisplayed());
        }
    }
    //вход через кнопку в форме восстановления пароля.
    @DisplayName("Вход через кнопку на странице восстановления пароля")
    @Description("Проверка входа из сценария восстановления пароля: переход на страницу восстановления, клик по кнопке «Войти», ввод логина/пароля, проверка перехода на главную и видимости кнопки заказа.")
    @Test
    public void loginOnRecoverPasswordPage() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPageLogin();
        loginPage.clickRecoverPasswordBtn();
        RecoverPasswordPage recoverPasswordPage = new RecoverPasswordPage(driver);
        recoverPasswordPage.clickLoginBtn_onRecoverPage();
            loginPage.login(user.getEmail(), user.getPassword());
            MainPage mainPage = new MainPage(driver);
            Assert.assertTrue(mainPage.createOrderBtn().isDisplayed());
    }
}

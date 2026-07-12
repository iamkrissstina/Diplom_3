import com.github.javafaker.Faker;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page.LoginPage;
import page.MainPage;
import page.RecoverPasswordPage;
import page.RegisterPage;

import java.time.Duration;

import static page.LoginPage.URL_LOGIN;
import static page.MainPage.URL_MAIN;
import static page.RecoverPasswordPage.URL_RECOVERPASSWORD;

public class LoginTest extends BaseTestUI {
    String email;
    String password;
    String name;
    @Before
    public void createDataUser() {
         Faker user = new Faker();
          email = user.name().lastName() + "_" + System.currentTimeMillis() + "@yandex.ru";
          password = "password";
          name = user.name().firstName();
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.openRegisterPage();
        registerPage.registerUser(name, email, password);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlToBe(URL_LOGIN));
    }
    // вход по кнопке «Войти в аккаунт» на главной;
    @DisplayName("Вход через кнопку «Войти в аккаунт» на главной странице")
    @Description("Проверка сценария входа: переход на главную, клик по кнопке «Войти в аккаунт», ввод логина/пароля, проверка перехода на главную страницу и видимости кнопки создания заказа.")
@Test
    public void login_loginAccountBtn() {
    MainPage mainPage = new MainPage(driver);
    mainPage.openMainPage();
    mainPage.clickLoginAccountBtn();
    new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.urlToBe(URL_LOGIN));
    LoginPage loginPage = new LoginPage(driver);
    loginPage.login(email, password);
    new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.urlToBe(URL_MAIN));
    Assert.assertTrue(mainPage.createOrderBtn().isDisplayed());
}
//вход через кнопку «Личный кабинет»;
@DisplayName("Вход через кнопку «Личный кабинет» на главной странице")
@Description("Проверка альтернативного сценария входа: переход на главную, клик по кнопке «Личный кабинет», ввод логина/пароля, проверка перехода на главную страницу и видимости кнопки создания заказа.")
    @Test
    public void login_personalAccountBtn() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.clickPersonalAccountBtn();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlToBe(URL_LOGIN));
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(email, password);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlToBe(URL_MAIN));
        Assert.assertTrue(mainPage.createOrderBtn().isDisplayed());
    }
    //вход через кнопку в форме регистрации;
    @DisplayName("Вход через кнопку на странице регистрации")
    @Description("Проверка входа из формы регистрации: открытие страницы регистрации, клик по ссылке/кнопке «Войти», ввод логина/пароля, проверка перехода на главную и видимости кнопки заказа.")
    @Test
    public void login_onRegisterPage() {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.openRegisterPage();
        registerPage.clickLoginBtn_onRegisterPage();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlToBe(URL_LOGIN));
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(email, password);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlToBe(URL_MAIN));
        MainPage mainPage = new MainPage(driver);
        Assert.assertTrue(mainPage.createOrderBtn().isDisplayed());
    }
    //вход через кнопку в форме восстановления пароля.
    @DisplayName("Вход через кнопку на странице восстановления пароля")
    @Description("Проверка входа из сценария восстановления пароля: переход на страницу восстановления, клик по кнопке «Войти», ввод логина/пароля, проверка перехода на главную и видимости кнопки заказа.")
    @Test
    public void login_onRecoverPasswordPage() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRecoverPasswordBtn();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlToBe(URL_RECOVERPASSWORD));
        RecoverPasswordPage recoverPasswordPage = new RecoverPasswordPage(driver);
        recoverPasswordPage.clickLoginBtn_onRecoverPage();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlToBe(URL_LOGIN));
        loginPage.login(email, password);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlToBe(URL_MAIN));
        MainPage mainPage = new MainPage(driver);
        Assert.assertTrue(mainPage.createOrderBtn().isDisplayed());
    }
}

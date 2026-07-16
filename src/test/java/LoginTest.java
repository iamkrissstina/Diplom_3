
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import page.LoginPage;
import page.MainPage;
import page.RecoverPasswordPage;
import page.RegisterPage;


public class LoginTest extends BaseTestUI {
    @Before
    public void createUser() {
         Helper.createUserApi();
    }
    @After
    public void tearDown() {
        Helper.deleteUser();
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
            loginPage.login(Helper.email, Helper.password);
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
        loginPage.login(Helper.email, Helper.password);
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
            loginPage.login(Helper.email, Helper.password);
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
            loginPage.login(Helper.email, Helper.password);
            MainPage mainPage = new MainPage(driver);
            Assert.assertTrue(mainPage.createOrderBtn().isDisplayed());
    }
}

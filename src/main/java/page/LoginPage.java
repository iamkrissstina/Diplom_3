package page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    WebDriver driver;
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
    public static final String URL_LOGIN = "https://stellarburgers.education-services.ru/login";
    @Step("Открыть страницу логина")
    public void openPageLogin() {
        driver.get(URL_LOGIN);
    }

    @Step("Ожидаем открытия страницы логина")
    public boolean isLoginPageOpened() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(login));
            return true;
        }  catch (Exception e) {
            return false;
        }
    }

    //Кнопка "Зарегистрироваться"
    private By registerBtn_onLoginPage = By.xpath("//a[contains(text(),'Зарегистрироваться')]");
    @Step("Кликнуть на кнопку Зарегистрироваться")
    public void clickRegisterBtn() {
        driver.findElement(registerBtn_onLoginPage).click();
    }
// Кнопка "Восстановить пароль"
    private By recoverPasswordBtn = By.xpath("//a[contains(text(),'Восстановить пароль')]");
    @Step("Кликнуть на кнопку Восстановить пароль")
    public void clickRecoverPasswordBtn() {
        driver.findElement(recoverPasswordBtn).click();
    }
    // Заголовок Вход
    private By login = By.xpath("//h2[contains(text(),'Вход')]");
    @Step("Найти элемент на странице: заголовок Вход")
    public WebElement loginText() {
        return driver.findElement(login);
    }
  // заполнить email
    private By emailField_onLoginPage = By.xpath("//input[@name='name']");
    @Step("Заполнить email")
    public void fillEmail(String email) {
        driver.findElement(emailField_onLoginPage).sendKeys(email);
    }
    // заполнить пароль
    private By passwordField_onLoginPage = By.xpath("//input[@name='Пароль']");
    @Step("Заполнить пароль")
    public void fillPassword(String password) {
        driver.findElement(passwordField_onLoginPage).sendKeys(password);
    }
    // кнопка "Войти"
    private By loginBtn = By.xpath("//button[contains(text(), 'Войти')]");
    @Step("Кликнуть на кнопку Войти")
    public void clickLoginBtn() {
        driver.findElement(loginBtn).click();
    }
    @Step("Залогиниться")
    public void login(String email, String password) {
        fillEmail(email);
        fillPassword(password);
        clickLoginBtn();
    }
}

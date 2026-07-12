package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    WebDriver driver;
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
    public static final String URL_LOGIN = "https://stellarburgers.education-services.ru/login";
    //Кнопка "Зарегистрироваться"
    private By registerBtn_onLoginPage = By.xpath("//a[contains(text(),'Зарегистрироваться')]");
    public void clickRegisterBtn() {
        driver.findElement(registerBtn_onLoginPage).click();
    }
// Кнопка "Восстановить пароль"
    private By recoverPasswordBtn = By.xpath("//a[contains(text(),'Восстановить пароль')]");
    public void clickRecoverPasswordBtn() {
        driver.findElement(recoverPasswordBtn).click();
    }
    // Заголовок Вход
    private By login = By.xpath("//h2[contains(text(),'Вход')]");
    public WebElement loginText() {
        return driver.findElement(login);
    }
  // заполнить email
    private By emailField_onLoginPage = By.xpath("//input[@name='name']");
    public void fillEmail(String email) {
        driver.findElement(emailField_onLoginPage).sendKeys(email);
    }
    // заполнить пароль
    private By passwordField_onLoginPage = By.xpath("//input[@name='Пароль']");
    public void fillPassword(String password) {
        driver.findElement(passwordField_onLoginPage).sendKeys(password);
    }
    // кнопка "Войти"
    private By loginBtn = By.xpath("//button[contains(text(), 'Войти')]");
    public void clickLoginBtn() {
        driver.findElement(loginBtn).click();
    }
    // Вход
    public void login(String email, String password) {
        fillEmail(email);
        fillPassword(password);
        clickLoginBtn();
    }
}

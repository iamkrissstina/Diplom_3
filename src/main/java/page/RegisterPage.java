package page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegisterPage {

    WebDriver driver;
    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }
    public static final String URL_REGISTER = "https://stellarburgers.education-services.ru/register";
    @Step("Открыть страницу регистрации")
    public void openRegisterPage() {
        driver.get(URL_REGISTER);
    }
    // Поле "Имя"
    private By nameField = By.xpath("//label[text()='Имя']/following::input[1]");
    @Step("Заполнить имя")
    public void fillName(String name) {
        driver.findElement(nameField).sendKeys(name);
    }
    // Поле "Email"
private By emailField_onRegisterPage = By.xpath("//label[text()='Email']/following::input[1]");
    @Step("Заполнить email")
    public void fillEmail(String email) {
        driver.findElement(emailField_onRegisterPage).sendKeys(email);
    }
    // Поле "Пароль"
    private By passwordField_onRegisterPage = By.xpath("//input[@name='Пароль']");
    @Step("Заполнить пароль")
    public void fillPassword(String password) {
        driver.findElement(passwordField_onRegisterPage).sendKeys(password);
    }
        // Кнопка "Зарегистрироваться"
        private By registerBtn_onRegisterPage = By.xpath("//button[contains(text(), 'Зарегистрироваться')]");
    @Step("Нажать на кнопку Зарегистрироваться")
        public void clickRegisterBtn_onRegisterPage() {
            driver.findElement(registerBtn_onRegisterPage).click();
    }
    @Step("Шаг - Регистрация пользователя")
    public void registerUser(String name, String email, String password) {
        fillName(name);
        fillEmail(email);
        fillPassword(password);
        clickRegisterBtn_onRegisterPage();
    }
    // ошибка при введении некорректного пароля
    private By errorPassword = By.xpath("//p[contains(text(), 'Некорректный пароль')]");
    @Step("Найти элемент на странице: ошибка при введении некорректного пароля")
   public WebElement  errorPassword() {
       return driver.findElement(errorPassword);
   }

   //кнопка "Войти" в форме регистрации
    private By loginBtn_onRegisterPage = By.xpath("//a[@class='Auth_link__1fOlj']");
    @Step("Кликнуть на кнопку Войти в форме регистрации")
   public void clickLoginBtn_onRegisterPage() {
       driver.findElement(loginBtn_onRegisterPage).click();
   }
}

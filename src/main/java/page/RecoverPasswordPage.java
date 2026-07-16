package page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static page.LoginPage.URL_LOGIN;

public class RecoverPasswordPage {
    WebDriver driver;

    public RecoverPasswordPage(WebDriver driver) {
        this.driver = driver;
    }
    public static final String URL_RECOVERPASSWORD = "https://stellarburgers.education-services.ru/forgot-password";
    //Кнопка "Войти" на старнице восстановления пароля
    private By loginBtn_onRecoverPage = By.xpath("//a[@class='Auth_link__1fOlj']");
    @Step("Кликнуть на кнопку Войти на странице восстановления пароля")
    public void clickLoginBtn_onRecoverPage() {
        driver.findElement(loginBtn_onRecoverPage).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlToBe(URL_LOGIN));
    }
}

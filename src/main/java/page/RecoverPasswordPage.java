package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RecoverPasswordPage {
    WebDriver driver;

    public RecoverPasswordPage(WebDriver driver) {
        this.driver = driver;
    }
    public static final String URL_RECOVERPASSWORD = "https://stellarburgers.education-services.ru/forgot-password";
    //Кнопка "Войти" на старнице восстановления пароля
    private By loginBtn_onRecoverPage = By.xpath("//a[@class='Auth_link__1fOlj']");
    public void clickLoginBtn_onRecoverPage() {
        driver.findElement(loginBtn_onRecoverPage).click();
    }
}

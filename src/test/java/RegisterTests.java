import com.github.javafaker.Faker;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page.LoginPage;
import page.RegisterPage;
import java.time.Duration;
import static page.LoginPage.URL_LOGIN;


public class RegisterTests extends BaseTestUI {
        String email;
        String password;
        String name;

    @Before
    public void createDataUser() {
        Faker user = new Faker();
         email = user.name().lastName() + "_" + System.currentTimeMillis() + "@yandex.ru";
         password = "password";
         name = user.name().firstName();
    }
    @DisplayName("Успешная регистрация нового пользователя")
    @Description("Проверка сценария успешной регистрации: ввод валидных данных, переход на страницу логина.")
    @Test
    public void successRegister() {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.openRegisterPage();
        registerPage.registerUser(name, email, password);
        LoginPage loginPage = new LoginPage(driver);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlToBe(URL_LOGIN));
        Assert.assertTrue("", loginPage.loginText().isDisplayed());
    }
    @DisplayName("Ошибка регистрации при неверном пароле")
    @Description("Проверка обработки ошибки при регистрации: ввод пароля меньше 6 символов, подтверждение отображения сообщения об ошибке.")
    @Test
    public void ErrorRegister_WrongPassword() {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.openRegisterPage();
        registerPage.registerUser(name, email, "pass");
        Assert.assertTrue("", registerPage.errorPassword().isDisplayed());
    }
}

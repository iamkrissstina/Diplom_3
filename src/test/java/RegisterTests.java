import com.github.javafaker.Faker;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import page.LoginPage;
import page.RegisterPage;
import static org.junit.Assert.assertTrue;


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
        assertTrue(loginPage.isLoginPageOpened());
    }
    @DisplayName("Ошибка регистрации при неверном пароле")
    @Description("Проверка обработки ошибки при регистрации: ввод пароля меньше 6 символов, подтверждение отображения сообщения об ошибке.")
    @Test
    public void ErrorRegister_WrongPassword() {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.openRegisterPage();
        registerPage.registerUser(name, email, "pass");
        assertTrue( registerPage.errorPassword().isDisplayed());
    }
}

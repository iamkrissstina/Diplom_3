import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import page.MainPage;
import static org.junit.Assert.assertTrue;


public class ConstructorTests extends BaseTestUI{
    @DisplayName("Выбор категории «Соусы» в конструкторе бургера")
    @Description("Проверка переключения активной категории на «Соусы»: кнопка «Соусы» становится активной, категории «Булки» и «Начинки» становятся неактивными.")

@Test
    public void clickSauce() {
    driver.manage().window().maximize();
    MainPage mainPage = new MainPage(driver);
    mainPage.openMainPage();
    mainPage.clickSauceBtn();
    // Проверяем, что «Соусы» АКТИВНЫ
    assertTrue(mainPage.isSaucesActive());
    // Проверяем, что «Булки» НЕ активны
    assertTrue(mainPage.isBunsNotActive());
    // Проверяем, что «Начинки» НЕ активны
    assertTrue(mainPage.isFillingsNotActive());
}
    @DisplayName("Выбор категории «Начинки» в конструкторе бургера")
    @Description("Проверка переключения активной категории на «Начинки»: кнопка «Начинки» становится активной, категории «Соусы» и «Булки» становятся неактивными.")
    @Test
    public void clickFillings() {
        driver.manage().window().maximize();
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.clickFillingBtn();
        assertTrue(mainPage.isSaucesNotActive());
        assertTrue(mainPage.isBunsNotActive());
        assertTrue(mainPage.isFillingsActive());
    }
    @DisplayName("Выбор категории «Булки» в конструкторе бургера (по умолчанию)")
    @Description("Проверка состояния категорий при открытии страницы: категория «Булки» активна, категории «Соусы» и «Начинки» неактивны.")
    @Test
    public void clickBuns() {
        driver.manage().window().maximize();
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();
        assertTrue(mainPage.isSaucesNotActive());
        assertTrue(mainPage.isBunsActive());
        assertTrue(mainPage.isFillingsNotActive());
    }
}

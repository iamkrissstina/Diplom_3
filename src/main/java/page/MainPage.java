package page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    WebDriver driver;
    public MainPage(WebDriver driver) {
        this.driver = driver;
    }
    public static final String URL_MAIN = "https://stellarburgers.education-services.ru/";
    @Step("Открыть главную страницу")
    public void openMainPage() {
        driver.get(URL_MAIN);
    }
    // Кнопка "Личный кабинет"
    private By personalAccountBtn = By.xpath("//p[contains(text(),'Личный Кабинет')]");
    @Step("Кликнуть на кнопку Личный кабинет")
public void clickPersonalAccountBtn() {
    driver.findElement(personalAccountBtn).click();
}
    // Кнопка "Войти в аккаунт"
    private By loginAccountBtn = By.xpath("//button[contains(text(),'Войти в аккаунт')]");
    @Step("Кликнуть на кнопку Войти в аккаунт")
    public void clickLoginAccountBtn() {
        driver.findElement(loginAccountBtn).click();
    }
// Кнопка оформить заказ - когда пользователь авторизован
    private By createOrderBtn = By.xpath("//button[contains(text(), 'Оформить заказ')]");
    @Step("Найти элемент: кнопка оформить заказ")
public WebElement createOrderBtn() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(createOrderBtn));
    return driver.findElement(createOrderBtn);
}


    public static final String ACTIVE_CLASS = "tab_tab_type_current__2BEPc";

    // Локаторы
    private final By bun = By.xpath("//span[normalize-space()='Булки']/ancestor::div[contains(@class,'tab_tab__1SPyG')]");
    @Step("Кликнуть на раздел Булки")
    public void clickBunBtn() {
        driver.findElement(bun).click();
    }
    private final By sauce = By.xpath("//span[normalize-space()='Соусы']/ancestor::div[contains(@class,'tab_tab__1SPyG')]");
    @Step("Кликнуть на раздел Соусы")
    public void clickSauceBtn() {
        driver.findElement(sauce).click();
    }
    private final By filling = By.xpath("//span[normalize-space()='Начинки']/ancestor::div[contains(@class,'tab_tab__1SPyG')]");
    @Step("Кликнуть на раздел Начинки")
    public void clickFillingBtn() {
        driver.findElement(filling).click();
    }
    @Step("Проверяем раздел на активность")
    //  ЭТОТ МЕТОД ЖДЁТ, пока появится класс
    private boolean isTabActive(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(d -> {
                String classes = d.findElement(locator).getAttribute("class");
                return classes != null && classes.contains(ACTIVE_CLASS);
        });
    }
    @Step("Проверяем раздел на неактивность")
    //ЭТОТ МЕТОД НЕ ЖДЁТ, он просто проверяет, что класса НЕТ
    private boolean isTabNotActive(By locator) {
            String classes = driver.findElement(locator).getAttribute("class");
            return classes == null || !classes.contains(ACTIVE_CLASS);
    }

    @Step("Активность раздела Булки")
    public boolean isBunsActive() { return isTabActive(bun); }
    @Step("Активность раздела Соусы")
    public boolean isSaucesActive() { return isTabActive(sauce); }
    @Step("Активность раздела Начинки")
    public boolean isFillingsActive() { return isTabActive(filling); }

    @Step("Неактивность раздела Булки")
    public boolean isBunsNotActive() { return isTabNotActive(bun); }
    @Step("Неактивность раздела Соусы")
    public boolean isSaucesNotActive() { return isTabNotActive(sauce); }
    @Step("Неактивность раздела Начинки")
    public boolean isFillingsNotActive() { return isTabNotActive(filling); }
}

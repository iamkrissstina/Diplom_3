package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    WebDriver driver;
    public MainPage(WebDriver driver) {
        this.driver = driver;
    }
    public static final String URL_MAIN = "https://stellarburgers.education-services.ru/";
    public void openMainPage() {
        driver.get(URL_MAIN);
    }
    // Кнопка "Личный кабинет"
    private By personalAccountBtn = By.xpath("//p[contains(text(),'Личный Кабинет')]");
public void clickPersonalAccountBtn() {
    driver.findElement(personalAccountBtn).click();
}
    // Кнопка "Войти в аккаунт"
    private By loginAccountBtn = By.xpath("//button[contains(text(),'Войти в аккаунт')]");
    public void clickLoginAccountBtn() {
        driver.findElement(loginAccountBtn).click();
    }
// Кнопка оформить заказ - когда пользователь авторизован
    private By createOrderBtn = By.xpath("//button[contains(text(), 'Оформить заказ')]");
public WebElement createOrderBtn() {
    return driver.findElement(createOrderBtn);
}


    public static final String ACTIVE_CLASS = "tab_tab_type_current__2BEPc";

    // Локаторы
    private final By bun = By.xpath("//span[normalize-space()='Булки']/ancestor::div[contains(@class,'tab_tab__1SPyG')]");
    public void clickBunBtn() {
        driver.findElement(bun).click();
    }
    private final By sauce = By.xpath("//span[normalize-space()='Соусы']/ancestor::div[contains(@class,'tab_tab__1SPyG')]");
    public void clickSauceBtn() {
        driver.findElement(sauce).click();
    }
    private final By filling = By.xpath("//span[normalize-space()='Начинки']/ancestor::div[contains(@class,'tab_tab__1SPyG')]");
    public void clickFillingBtn() {
        driver.findElement(filling).click();
    }
    //  ЭТОТ МЕТОД ЖДЁТ, пока появится класс
    private boolean isTabActive(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(d -> {
                String classes = d.findElement(locator).getAttribute("class");
                return classes != null && classes.contains(ACTIVE_CLASS);
        });
    }

    //ЭТОТ МЕТОД НЕ ЖДЁТ, он просто проверяет, что класса НЕТ
    private boolean isTabNotActive(By locator) {
            String classes = driver.findElement(locator).getAttribute("class");
            return classes == null || !classes.contains(ACTIVE_CLASS);


    }

    // Публичные методы
    public boolean isBunsActive() { return isTabActive(bun); }
    public boolean isSaucesActive() { return isTabActive(sauce); }
    public boolean isFillingsActive() { return isTabActive(filling); }

    // Новые методы для неактивных
    public boolean isBunsNotActive() { return isTabNotActive(bun); }
    public boolean isSaucesNotActive() { return isTabNotActive(sauce); }
    public boolean isFillingsNotActive() { return isTabNotActive(filling); }
}

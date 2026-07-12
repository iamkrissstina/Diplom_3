import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.service.DriverService;
import java.io.File;


public class BaseTestUI {
    WebDriver driver;

    @Before
    public void startBrowser() {
        String browser = System.getProperty("browser", "chrome");
        if (browser.equals("chrome")) {
            startBrowserChrome();
        } else if (browser.equals("yandex")) {
            startBrowserYandex();
        }
    }
    public void startBrowserChrome() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }
    public void startBrowserYandex() {
        // 1. отключаем Selenium Manager —
        System.setProperty("webdriver.chromium.bypass_manager", "true");

        String driverPath = "C:/WebDriver/bin/yandexdriver.exe";
        String browserPath = "C:\\Program Files\\Yandex\\YandexBrowser\\Application\\browser.exe";

        File driverFile = new File(driverPath);
        if (!driverFile.exists()) {
            throw new IllegalStateException("YandexDriver not found at: " + driverPath);
        }

        // 2. Указываем путь к драйверу
        System.setProperty("webdriver.chrome.driver", driverPath);

        System.out.println("=== DEBUG: bypass_manager = " + System.getProperty("webdriver.chromium.bypass_manager"));
        System.out.println("=== DEBUG: webdriver.chrome.driver = " + System.getProperty("webdriver.chrome.driver"));

        ChromeOptions yandexOptions = new ChromeOptions();
        yandexOptions.setBinary(browserPath);
        yandexOptions.addArguments("--no-sandbox");
        yandexOptions.addArguments("--disable-dev-shm-usage");
        yandexOptions.addArguments("--disable-gpu");
        yandexOptions.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(yandexOptions);
        System.out.println("=== DEBUG: Yandex Browser started successfully");
    }


    @After
    public void tearDown() {
        driver.quit();
    }
}


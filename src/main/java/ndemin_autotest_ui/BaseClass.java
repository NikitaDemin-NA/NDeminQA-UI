package ndemin_autotest_ui;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static ndemin_autotest_ui.PropertiesProvider.PATH_DRIVER_MANAGER_WINDOWS;
import static ndemin_autotest_ui.PropertiesProvider.PATH_FILES;

abstract public class BaseClass {

   private final String os = System.getProperty("os.name");
   private final String pathDriverManagerMac = "src/test/resources/drivers/chromedriverMac119";
   private final String pathDriverManagerLinux = "src/test/resources/drivers/chromedriverLinux";

    @BeforeAll
    static void setupAllureReports() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @SneakyThrows
    public void setUp() {
        WebDriverManager.chromedriver().setup();
//        if (os.contains("Windows")){
//            setDriverManager(PATH_DRIVER_MANAGER_WINDOWS);
//        } else if (os.contains("Mac OS")) {
//            setDriverManager(pathDriverManagerMac);
//        } else {
//            setDriverManager(pathDriverManagerLinux);
//        }

        Configuration.browser = "chrome";
        Configuration.driverManagerEnabled = true;
        Configuration.browserSize = "1920x1080";
        Configuration.headless = false;
        Configuration.pageLoadTimeout = 20000;
        Configuration.pageLoadStrategy = "eager";
        Configuration.timeout = 20000;
        Configuration.downloadsFolder = PATH_FILES;
    }

    @BeforeEach
    public void init() {
        setUp();
    }

    @AfterEach
    public void tearDown() {
        Selenide.closeWebDriver();
    }

    private void setDriverManager(String pathWebDriverManager){
        System.setProperty("webdriver.chrome.driver", pathWebDriverManager);
    }
}
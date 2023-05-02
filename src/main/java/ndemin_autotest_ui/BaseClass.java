package ndemin_autotest_ui;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static ndemin_autotest_ui.PropertiesProvider.PATH_FILES;

abstract public class BaseClass {

    @SneakyThrows
    public void setUp() {
        WebDriverManager.chromedriver().setup();
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
}
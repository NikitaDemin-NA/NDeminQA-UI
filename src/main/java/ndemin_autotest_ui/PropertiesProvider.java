package ndemin_autotest_ui;

import lombok.SneakyThrows;

import java.util.Properties;

public interface PropertiesProvider {

    @SneakyThrows
    static Properties readProperties() {
        System.getProperties().load(ClassLoader.getSystemResourceAsStream("application.properties"));
        return System.getProperties();
    }

    String BASE_URL = readProperties().getProperty("url.base");

    String REGISTRATION_NAME = readProperties().getProperty("registration.name");
    String REGISTRATION_SURNAME = readProperties().getProperty("registration.surname");

    String REGISTRATION_NAME_TEST = readProperties().getProperty("registration.name.test");
    String REGISTRATION_SURNAME_TEST = readProperties().getProperty("registration.surname.test");

    String REGISTRATION_COMMENT = readProperties().getProperty("registration.comment");

    String PATH_FILES = readProperties().getProperty("path.files");
}
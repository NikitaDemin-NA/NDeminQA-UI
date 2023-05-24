import io.qameta.allure.Owner;
import lombok.SneakyThrows;
import ndemin_autotest_ui.BaseClass;
import ndemin_autotest_ui.pages.RegistrationPage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import static ndemin_autotest_ui.Constants.ContactsPageConstants.CONTACTS_PAGE_NAME;
import static ndemin_autotest_ui.Constants.ContactsPageConstants.MAP_OF_CONTACTS;
import static ndemin_autotest_ui.Constants.FilesPageConstants.FILES_PAGE_NAME;
import static ndemin_autotest_ui.Constants.MainPageConstants.MAIN_PAGE_HEADNAME;
import static ndemin_autotest_ui.Constants.RegistrationPageConstants.REGISTRATION_PAGE_HEADNAME;
import static ndemin_autotest_ui.Constants.RegistrationPageConstants.REGISTRATION_PAGE_NAME;
import static ndemin_autotest_ui.Constants.TextPageConstants.TEXT_PAGE_NAME;
import static ndemin_autotest_ui.Constants.TextPageConstants.TYPES_OF_TESTING_ARRAY;
import static ndemin_autotest_ui.PageManager.*;

@Tag("NDemin.QA")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Execution(ExecutionMode.CONCURRENT)
@Owner("Nikita Demin")
public class UITest extends BaseClass {

    @Test
    @DisplayName("Check MainPage")
    @Order(0)
    public void checkMainPage() {
        mainPage.open()
                .checkHeadName(MAIN_PAGE_HEADNAME)
                .checkPhoto()
                .checkQuantityPages(5);
    }

    @ParameterizedTest
    @ArgumentsSource(RegistrationPage.class)
    @DisplayName("Check Registration with different users")
    @Order(1)
    public void checkRegistrationPage(String name, String surname, String comment) {
        mainPage.open().clickPage(REGISTRATION_PAGE_NAME);

        registrationPage.checkHeadName(REGISTRATION_PAGE_HEADNAME);
        registrationPage.signUp(name, surname, comment);
        registrationPage.checkSuccessfulRegistration();
    }

    @Test
    @DisplayName("Check FilesPage")
    @SneakyThrows
    @Order(2)
    public void checkFilesPage() {
        mainPage.open().clickPage(FILES_PAGE_NAME);

        filesPage.deleteAllFiles();
        filesPage.downloadFiles();
        filesPage.checkFileTXT();
        filesPage.checkFilePNG();
        filesPage.checkFileEXCEL();
    }

    @Test
    @DisplayName("Check TextPage")
    @Order(3)
    public void checkTextPage() {
        mainPage.open().clickPage(TEXT_PAGE_NAME);

        textPage.checkTypesOfTesting(TYPES_OF_TESTING_ARRAY);
    }

    @Test
    @DisplayName("Check ContactsPage")
    @Order(4)
    public void checkContactsPage() {
        mainPage.open().clickPage(CONTACTS_PAGE_NAME);

        contactsPage.checkContacts(MAP_OF_CONTACTS);
    }
}
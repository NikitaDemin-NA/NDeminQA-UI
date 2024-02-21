import io.qameta.allure.Owner;
import lombok.SneakyThrows;
import ru.ndemin.ui.BaseClass;
import ru.ndemin.ui.pages.RegistrationPage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import static ru.ndemin.ui.constants.ContactsPageConstants.CONTACTS_PAGE_NAME;
import static ru.ndemin.ui.constants.ContactsPageConstants.MAP_OF_CONTACTS;
import static ru.ndemin.ui.constants.FilesPageConstants.FILES_PAGE_NAME;
import static ru.ndemin.ui.constants.MainPageConstants.MAIN_PAGE_HEADNAME;
import static ru.ndemin.ui.constants.RegistrationPageConstants.REGISTRATION_PAGE_HEADNAME;
import static ru.ndemin.ui.constants.RegistrationPageConstants.REGISTRATION_PAGE_NAME;
import static ru.ndemin.ui.constants.TextPageConstants.TEXT_PAGE_NAME;
import static ru.ndemin.ui.constants.TextPageConstants.TYPES_OF_TESTING_ARRAY;
import static ru.ndemin.ui.PageManager.*;

@Owner("Nikita Demin")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Execution(ExecutionMode.CONCURRENT)
@Tag("NDemin.QA")
public class UITest extends BaseClass {
    @Test
    @Order(0)
    @DisplayName("Check MainPage")
    public void checkMainPage() {
        mainPage.open()
                .checkHeadName(MAIN_PAGE_HEADNAME)
                .checkPhoto()
                .checkQuantityPages(5);
    }

    @ParameterizedTest
    @ArgumentsSource(RegistrationPage.class)
    @Order(1)
    @DisplayName("Check Registration with different users")
    public void checkRegistrationPage(String name, String surname, String comment) {
        mainPage.open().clickPage(REGISTRATION_PAGE_NAME);

        registrationPage.checkHeadName(REGISTRATION_PAGE_HEADNAME);
        registrationPage.signUp(name, surname, comment);
        registrationPage.checkSuccessfulRegistration();
    }

    @Test
    @Order(2)
    @SneakyThrows
    @DisplayName("Check FilesPage")
    public void checkFilesPage() {
        mainPage.open().clickPage(FILES_PAGE_NAME);

        filesPage.deleteAllFiles();
        filesPage.downloadFiles();
        filesPage.checkFileTXT();
        filesPage.checkFilePNG();
        filesPage.checkFileEXCEL();
    }

    @Test
    @Order(3)
    @DisplayName("Check TextPage")
    public void checkTextPage() {
        mainPage.open().clickPage(TEXT_PAGE_NAME);

        textPage.checkTypesOfTesting(TYPES_OF_TESTING_ARRAY);
    }

    @Test
    @Order(4)
    @DisplayName("Check ContactsPage")
    public void checkContactsPage() {
        mainPage.open().clickPage(CONTACTS_PAGE_NAME);

        contactsPage.checkContacts(MAP_OF_CONTACTS);
    }
}
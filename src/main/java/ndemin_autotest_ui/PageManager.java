package ndemin_autotest_ui;

import ndemin_autotest_ui.pages.*;

public interface PageManager {

    MainPage mainPage = new MainPage();
    RegistrationPage registrationPage = new RegistrationPage();
    FilesPage filesPage = new FilesPage();
    TextPage textPage = new TextPage();
    ContactsPage contactsPage = new ContactsPage();
}
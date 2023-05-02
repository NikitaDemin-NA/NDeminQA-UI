package ndemin_autotest_ui.pages;

import com.codeborne.selenide.ElementsCollection;
import io.cucumber.java.en.Given;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.$$x;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContactsPage {

    String contactsList = "//div[@class='t1028__btn__text']";
    ElementsCollection contactsListName = $$x(contactsList);
    ElementsCollection contactsListHref = $$x(contactsList + "//parent::a");

    @Given("Check Contacts")
    public void checkContacts(Map<String, String> contactsMap) {
        Map<String, String> actualContactsMap = new HashMap<>();

        for (int i = 0; i < contactsListName.size(); i++) {
            actualContactsMap.put(contactsListName.get(i).getText(), contactsListHref.get(i).getAttribute("href"));
        }

        System.out.println(actualContactsMap.entrySet());

        assertEquals(contactsMap.entrySet(), actualContactsMap.entrySet());
    }
}
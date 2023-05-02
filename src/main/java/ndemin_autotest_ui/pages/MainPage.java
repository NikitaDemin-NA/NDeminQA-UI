package ndemin_autotest_ui.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.en.Given;

import static com.codeborne.selenide.Condition.exactOwnText;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static ndemin_autotest_ui.PropertiesProvider.BASE_URL;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MainPage {

    ElementsCollection pageNames = $$x("//a[contains(@class, 't453__item_link t-menu__link-item')]");

    SelenideElement headName = $x("//*[text()='Test project of Nikita Demin']");
    SelenideElement imageNikita = $x("//img[@class='tn-atom__img t-img loaded']");

    @Given("Open site")
    public MainPage open() {
        Selenide.open(BASE_URL);
        return this;
    }

    @Given("Check head")
    public MainPage checkHeadName(String headNameResult) {
        assertEquals(headNameResult, headName.getOwnText());
        return this;
    }

    @Given("Check photo")
    public MainPage checkPhoto() {
        assertTrue(imageNikita.isImage());
        return this;
    }

    @Given("Check q-ty pages {int}")
    public MainPage checkQuantityPages(int quantityPages) {
        assertEquals(quantityPages, pageNames.size());
        return this;
    }

    @Given("Click page {string}")
    public MainPage clickPage(String pageName) {
        pageNames.findBy(exactOwnText(pageName)).click();
        return this;
    }
}
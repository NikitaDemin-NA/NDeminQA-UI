package ndemin_autotest_ui.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.en.Given;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.closeWindow;
import static ndemin_autotest_ui.Constants.RegistrationPageConstants.REGISTRATION_SUCCESS_WINDOW;
import static ndemin_autotest_ui.PropertiesProvider.*;
import static org.junit.Assert.assertEquals;

public class RegistrationPage implements ArgumentsProvider {

    SelenideElement headName = $x("//div[@field='tn_text_1470209944682']");
    SelenideElement fieldName = $x("//input[@placeholder='Name']");
    SelenideElement fieldSurname = $x("//input[@placeholder='Surname']");
    SelenideElement fieldComment = $x("//textarea[@placeholder='Comment']");
    SelenideElement buttonSend = $x("//a[@class='tn-atom' and text()='Sign Up']");
    SelenideElement successRegistationText = $x("//div[text()='You have successfully registered!']");

    @Given("Check head")
    public void checkHeadName(String headNameResult) {
        assertEquals(headNameResult, headName.getOwnText());
    }

    @Given("Fill fields for registration")
    public void makeRegistration(String name, String surname, String comment) {
        fieldName.sendKeys(name);
        fieldSurname.sendKeys(surname);
        fieldComment.sendKeys(comment);
        buttonSend.click();
    }

    @Given("Check success registration")
    public void checkSuccessRegistration() {
        Selenide.switchTo().window(REGISTRATION_SUCCESS_WINDOW);
        successRegistationText.should(exist);
        closeWindow();
    }

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
                Arguments.of(REGISTRATION_NAME, REGISTRATION_SURNAME, REGISTRATION_COMMENT),
                Arguments.of(REGISTRATION_NAME_TEST, REGISTRATION_SURNAME_TEST, REGISTRATION_COMMENT));
    }
}
package ru.ndemin.ui.pages;

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
import static ru.ndemin.ui.constants.RegistrationPageConstants.REGISTRATION_SUCCESS_WINDOW;
import static ru.ndemin.ui.PropertiesProvider.*;
import static org.junit.Assert.assertEquals;

public class RegistrationPage implements ArgumentsProvider {
    private final SelenideElement headName = $x("//div[@field='tn_text_1470209944682']");
    private final SelenideElement fieldName = $x("//input[@placeholder='Name']");
    private final SelenideElement fieldSurname = $x("//input[@placeholder='Surname']");
    private final SelenideElement fieldComment = $x("//textarea[@placeholder='Comment']");
    private final SelenideElement buttonSend = $x("//a[@class='tn-atom' and text()='Sign Up']");
    private final SelenideElement successRegistationText = $x("//div[text()='You have successfully registered!']");

    @Given("Check head")
    public void checkHeadName(String headNameResult) {
        assertEquals(headNameResult, headName.getOwnText());
    }

    @Given("Fill fields for registration")
    public void signUp(String name, String surname, String comment) {
        fieldName.sendKeys(name);
        fieldSurname.sendKeys(surname);
        fieldComment.sendKeys(comment);
        buttonSend.click();
    }

    @Given("Check success registration")
    public void checkSuccessfulRegistration() {
        Selenide.switchTo().window(REGISTRATION_SUCCESS_WINDOW);
        successRegistationText.should(exist);
        closeWindow();
    }

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
        return Stream.of(
                Arguments.of(REGISTRATION_NAME, REGISTRATION_SURNAME, REGISTRATION_COMMENT),
                Arguments.of(REGISTRATION_NAME_TEST, REGISTRATION_SURNAME_TEST, REGISTRATION_COMMENT));
    }
}
package ndemin_autotest_ui.pages;

import com.codeborne.selenide.ElementsCollection;
import io.cucumber.java.en.Given;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$$x;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TextPage {

    ElementsCollection typesOfTestingList = $$x("//p[@style='text-align: left;']");

    @Given("Check Types of Testing")
    public void checkTypesOfTesting(String[] typesOfTestingArray) {
        List<String> actualTypesOfTestingList = new LinkedList<>();

        typesOfTestingList.forEach(x -> actualTypesOfTestingList.add(x.getText()));
        assertTrue(Arrays.asList(typesOfTestingArray).containsAll(actualTypesOfTestingList));

        actualTypesOfTestingList.forEach(System.out::println);
    }
}
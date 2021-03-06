import com.codeborne.selenide.*;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;


import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class CardDeliveryTest {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    public void ShouldReplanMeeting() {
        open("http://localhost:9999");
        SelenideElement form = $("form");
        form.$("input[placeholder='Город'").setValue(DataGenerator.City.cityRandomizer());
        form.$("input.input__control[formnovalidate]").click();
        form.$("input.input__control[formnovalidate]").sendKeys("\u0001\b");
        form.$("input.input__control[formnovalidate]").sendKeys(DataGenerator.Date.dateGenerator());
        form.$("input[name='name'").setValue(DataGenerator.Person.fullNameGenerator());
        form.$("input[name='phone'").setValue(DataGenerator.Person.phoneGenerator());
        form.$("span.checkbox__box").click();
        form.$(withText("Запланировать")).click();
        $("div.notification").$(withText("Встреча успешно запланирована на")).waitUntil(visible, 15000);

        form.$("input.input__control[formnovalidate]").click();
        form.$("input.input__control[formnovalidate]").sendKeys("\u0001\b");
        form.$("input.input__control[formnovalidate]").sendKeys(DataGenerator.Date.dateGenerator());
        form.$(withText("Запланировать")).click();
        $(withText("У вас уже запланирована встреча на другую дату")).waitUntil(visible, 15000);
    }
}

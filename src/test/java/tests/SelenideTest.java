package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class SelenideTest {

  @Test
  @Feature("Issue в репозитории")
  @Story("Создание Issue")
  @Owner("AlexeyGarkusha")
  @Severity(SeverityLevel.BLOCKER)
  @Link(value = "GitHub", url = "https://github.com")
  @DisplayName("Создание Issue для автоматизированного пользователя")
  public void testIssueSearch() throws InterruptedException {
    SelenideLogger.addListener("allure", new AllureSelenide());
    open("https://github.com/");
    $(".header-search-button").click();
    $("#query-builder-test").sendKeys("Garkusha1991" + Keys.ENTER);
    Thread.sleep(10000);
    $(".prc-ActionList-ItemLabel-81ohH").shouldBe(visible).click();

    $(withText("#1")).should(Condition.exist);
  }

}

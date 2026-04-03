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
import static io.qameta.allure.Allure.attachment;
import static io.qameta.allure.Allure.step;

public class StepsAttachments {

  private final String REPOSITORY = "Garkusha1991";
  private final String ISSUE = "1";

  @Test
  @Feature("Issue в репозитории")
  @Story("Создание Issue")
  @Owner("AlexeyGarkusha")
  @Severity(SeverityLevel.BLOCKER)
  @Link(value = "GitHub", url = "https://github.com")
  @DisplayName("Создание Issue для автоматизированного пользователя")
  public void testLambdaAttachments() {
    SelenideLogger.addListener("allure", new AllureSelenide());

    step("Открываем главную страницу", () -> {
      open("https://github.com/");
    });

    step("Делаем поиск по репозиторию", () -> {
      $(".header-search-button").click();
      $("#query-builder-test").sendKeys(REPOSITORY + Keys.ENTER);
    });

    step("Переходим в раздел с Issue", () -> {
      $(".prc-ActionList-ItemLabel-81ohH").shouldBe(visible).click();
    });

    step("Ищем Issue", () -> {
      $(withText(ISSUE)).should(Condition.exist);
    });
   attachment("Source", webdriver().driver().source());

  }

  @Test
  @Feature("Issue в репозитории")
  @Story("Создание Issue")
  @Owner("AlexeyGarkusha")
  @Severity(SeverityLevel.BLOCKER)
  @Link(value = "GitHub", url = "https://github.com")
  @DisplayName("Создание Issue для автоматизированного пользователя")
  public void testAnnotatedAttachments() {
    SelenideLogger.addListener("allure", new AllureSelenide());
    WebSteps steps = new WebSteps();

    steps
      .openMainPage()
      .takeScreenshot();
  }
}

package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class WebSteps {

  @Step("Открываем главную страницу")
  public WebSteps openMainPage() {
    open("https://github.com/");

    return this;
  }

  @Step("Ищем репозиторий")
  public WebSteps finedRepository(String repository) {
    $(".header-search-button").click();
    $("#query-builder-test").sendKeys(repository + Keys.ENTER);

    return this;
  }

  @Step("Открываем таб Issue")
  public WebSteps openIssueTab() {
      $x("//*[@aria-haspopup='dialog']");
    $(".prc-ActionList-ItemLabel-81ohH").shouldBe(visible).click();

    return this;
  }

  @Step("Проверяем, что  ISSUE существует")
  public WebSteps finedIssue(String issue) {
    $(withText("#" + issue)).should(Condition.exist);

    return this;
  }

  @Attachment(value = "ScreenShot", type = "image/png", fileExtension = "png")
  public  byte[] takeScreenshot() {
    return((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
  }

}

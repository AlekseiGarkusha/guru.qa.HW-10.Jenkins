  /* Расширяющий класс для:
   * Вход в систему
   * Открытие страницы
   * Закрытие страницы
   */

  package setup;

  import com.codeborne.selenide.Configuration;
  import com.codeborne.selenide.logevents.SelenideLogger;
  import io.github.bonigarcia.wdm.WebDriverManager;
  import io.qameta.allure.selenide.AllureSelenide;
  import org.junit.jupiter.api.AfterAll;
  import org.junit.jupiter.api.BeforeAll;
  import org.junit.jupiter.api.BeforeEach;
  import org.openqa.selenium.WebDriverException;
  import org.openqa.selenium.chrome.ChromeOptions;

  import static com.codeborne.selenide.Selenide.closeWebDriver;

  public class TestBase {
    @BeforeAll
    public static void setup() {

      ChromeOptions options = new ChromeOptions();
      options.addArguments(
        "--no-sandbox",
        "--disable-dev-shm-usage"
      );

      Configuration.remote = "https://selenoid.autotests.cloud/wd/hub";
      Configuration.browser = "chrome";
      Configuration.browserVersion = "120.0";

      Configuration.browserCapabilities = options;
      Configuration.browserSize = "1920x1080";
      Configuration.timeout = 10000;
    }

    @AfterAll
    public static void tearDown() {
      closeWebDriver();
    }
  }


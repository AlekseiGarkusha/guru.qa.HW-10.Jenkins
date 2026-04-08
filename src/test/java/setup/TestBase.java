  /* Расширяющий класс для:
   * Вход в систему
   * Открытие страницы
   * Закрытие страницы
   */

  package setup;

  import com.codeborne.selenide.Configuration;
  import com.codeborne.selenide.logevents.SelenideLogger;
  import io.qameta.allure.selenide.AllureSelenide;
  import org.junit.jupiter.api.AfterAll;
  import org.junit.jupiter.api.BeforeAll;
  import org.junit.jupiter.api.BeforeEach;
  import org.openqa.selenium.chrome.ChromeOptions;

  import static com.codeborne.selenide.Selenide.closeWebDriver;

  public class TestBase {

    @BeforeAll
    public static void setup() {
      ChromeOptions options = new ChromeOptions();
      options.addArguments("--remote-allow-origins=*");

      String remote = System.getProperty("selenide.remote");

      Configuration.browser = System.getProperty("browser", "chrome");
      Configuration.browserSize = System.getProperty("resolution", "1920x1080");
      Configuration.timeout = 5000;

      if (remote != null) {
        Configuration.remote = remote;
        Configuration.browserCapabilities = options;
      } else {
        Configuration.browserCapabilities = options;
      }
    }

    @BeforeEach
    public void logger() {
      SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    public static void tearDown() {
      closeWebDriver();
    }
  }
  
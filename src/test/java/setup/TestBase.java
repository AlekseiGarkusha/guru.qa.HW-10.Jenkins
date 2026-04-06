  /* Расширяющий класс для:
   * Вход в систему
   * Открытие страницы
   * Закрытие страницы
   */

  package setup;

  import com.codeborne.selenide.Configuration;
  import com.codeborne.selenide.TextCheck;
  import io.github.bonigarcia.wdm.WebDriverManager;
  import org.junit.jupiter.api.AfterAll;
  import org.junit.jupiter.api.BeforeAll;
  import org.openqa.selenium.chrome.ChromeOptions;

  import static com.codeborne.selenide.Configuration.baseUrl;
  import static com.codeborne.selenide.Selenide.closeWebDriver;
  import static com.codeborne.selenide.Selenide.open;

  public class TestBase {

    @BeforeAll
    public static void setup() {
      WebDriverManager.chromedriver().setup();

      ChromeOptions options = new ChromeOptions();
      options.addArguments(
        "--headless=new",
        "--no-sandbox",
        "--disable-dev-shm-usage",
        "--remote-allow-origins=*"
      );

      Configuration.browser = "chrome";
      Configuration.headless = true;
//      Configuration.holdBrowserOpen = true;
      Configuration.timeout = 5000;
      Configuration.browserCapabilities = options;
      Configuration.browserSize = "1920x1080";
    }

    @AfterAll
    public static void tearDown() {
      closeWebDriver();
    }

  }


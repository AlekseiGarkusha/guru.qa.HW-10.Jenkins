  /* Расширяющий класс для:
   * Вход в систему
   * Открытие страницы
   * Закрытие страницы
   */

  package setup;

  import com.codeborne.selenide.Configuration;
  import io.github.bonigarcia.wdm.WebDriverManager;
  import org.junit.jupiter.api.AfterAll;
  import org.junit.jupiter.api.BeforeAll;
  import org.openqa.selenium.WebDriver;
  import org.openqa.selenium.WebDriverException;
  import org.openqa.selenium.chrome.ChromeOptions;

  import static com.codeborne.selenide.Selenide.closeWebDriver;

  public class TestBase {
    private static WebDriver driver;

    @BeforeAll
    public static void setup() {
      try {
        // Автоматически подбирает совместимую версию ChromeDriver
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();

        // Определяем, где запускаем тесты
        boolean isCI = System.getenv("CI") != null ||
          System.getProperty("ci") != null;

        if (isCI) {
          // Настройки для CI/Jenkins
          options.addArguments(
            "--headless=new",
            "--no-sandbox",
            "--disable-dev-shm-usage",
            "--disable-gpu",
            "--window-size=1920,1080"
          );
        } else {
          // Настройки для локального запуска
          options.addArguments("--window-size=1920,1080");
        }

        // Базовая конфигурация Selenide
        Configuration.browser = "chrome";
        Configuration.headless = isCI;
        Configuration.timeout = 10000; // Увеличен таймаут
        Configuration.pageLoadTimeout = 30000;
        Configuration.holdBrowserOpen = false;
        Configuration.browserCapabilities = options;
        Configuration.browserSize = "1920x1080";
        Configuration.screenshots = true;
        Configuration.savePageSource = true;

      } catch (WebDriverException e) {
        System.err.println("Ошибка инициализации WebDriver: " + e.getMessage());
        throw e;
      } catch (Exception e) {
        System.err.println("Критическая ошибка при настройке тестов: " + e.getMessage());
        throw new RuntimeException(e);
      }
    }

    @AfterAll
    public static void tearDown() {
      try {
        closeWebDriver();
        System.out.println("WebDriver корректно закрыт");
      } catch (Exception e) {
        System.err.println("Ошибка при закрытии WebDriver: " + e.getMessage());
      }
    }
}


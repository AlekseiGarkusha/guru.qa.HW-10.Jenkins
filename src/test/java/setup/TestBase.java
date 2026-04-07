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
      try {
        // Автоматически загружаем совместимую версию ChromeDriver
        WebDriverManager.chromedriver().forceDownload().create();

        ChromeOptions options = new ChromeOptions();

        // Настройки для CI/Jenkins
        options.addArguments(
          "--headless=new",
          "--no-sandbox",
          "--disable-dev-shm-usage",
          "--disable-gpu",
          "--window-size=1920,1080",
          "--remote-allow-origins=*"
        );

        // Конфигурация Selenide
        Configuration.browser = "chrome";
        Configuration.headless = true; // Включаем headless-режим
        Configuration.timeout = 15000; // Увеличиваем таймаут
        Configuration.pageLoadTimeout = 30000;
        Configuration.holdBrowserOpen = false;
        Configuration.screenshots = true;
        Configuration.savePageSource = true;
        Configuration.browserCapabilities = options;
        Configuration.browserSize = "1920x1080";

      } catch (WebDriverException e) {
        System.err.println("Ошибка инициализации WebDriver: " + e.getMessage());
        throw e;
      } catch (Exception e) {
        System.err.println("Критическая ошибка при настройке тестов: " + e.getMessage());
        throw new RuntimeException("Не удалось инициализировать WebDriver", e);
      }
    }

    @BeforeEach
    public void logger() {
      SelenideLogger.addListener("allure", new AllureSelenide());
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


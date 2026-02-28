package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class DriverFactory {
    private static WebDriver driver;
    public static String browser = ConfigReader.getProperty("browser");

    public static void initDriver() {
        switch (browser.toLowerCase()){
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();

                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            default:
                throw new RuntimeException("Browser not supported");

        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(
                Duration.ofSeconds(
                        Integer.parseInt(
                                ConfigReader.getProperty("implicit.wait")
                        )
                )
        );

    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void quitDriver() {
        driver.quit();
    }
}

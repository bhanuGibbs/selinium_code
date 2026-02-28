package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utilities.DriverFactory;
import utilities.ScenarioLogger;

public class Hooks {

    @Before
    public void setUp() {
        // Clear previous logs
        ScenarioLogger.clearLogs();
        // Redirect console output to ScenarioLogger
        System.setOut(ScenarioLogger.getPrintStream());
        // Initialize WebDriver
       // DriverFactory.initDriver();
    }

    @After
    public void tearDown(Scenario scenario) {
        try {
            // 1️⃣ Attach captured logs to Cucumber HTML report
            String logs = ScenarioLogger.getLogs();
            if (!logs.isEmpty()) {
                scenario.log("===== Execution Logs =====");
                scenario.log(logs);
            }

            // 2️⃣ Attach screenshot for failed scenario
            if (scenario.isFailed()) {
                byte[] screenshot = ((TakesScreenshot) DriverFactory.getDriver())
                        .getScreenshotAs(OutputType.BYTES);

                scenario.attach(screenshot, "image/png", "Failed Screenshot");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Quit WebDriver
            DriverFactory.quitDriver();
        }
    }
}
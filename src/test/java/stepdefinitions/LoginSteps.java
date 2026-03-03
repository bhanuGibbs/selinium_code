package stepdefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import pages.LoginPage;
import utilities.DriverFactory;
import utilities.ExcelUtils;
import utilities.LoggerHelper;
import org.slf4j.Logger;
import io.cucumber.java.en.*;
import utilities.ScenarioLogger;



public class LoginSteps {

    private static final Logger log = LoggerHelper.getLogger(LoginSteps.class);

    LoginPage loginPage;
   //  LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
    private Scenario scenario;

@Before
    public void before(Scenario scenario) {
        this.scenario = scenario;
        DriverFactory.initDriver();
        ScenarioLogger.clearLogs();
        loginPage = new LoginPage(DriverFactory.getDriver());
    }


    @Given("user is on login page")
    public void user_is_on_login_page() throws InterruptedException {
        loginPage.openWebsite();
        log.info("Opened URL");
      //  before(scenario.log("Navigated to login page."));
        scenario.log("Opened URL");
        System.out.println("OPEN Step executed successfully");

        Thread.sleep(10000);
    }

    @When("user enters username {string}")
    public void user_enters_username(String username) {
        loginPage.enterUsername(username);
        log.info("Entered username: " + username);
        scenario.log("Entered username: " + username);

    }

    @When("user enters password {string}")
    public void user_enters_password(String password) {
        loginPage.enterPassword(password);
        log.info("Entered password: *****"); // mask password
        scenario.log("Entered password: " + password);
    }

    @When("user clicks login button")
    public void user_clicks_login_button() {
        loginPage.clickLogin();
        log.info("Clicked login button");
        scenario.log("Click on Login button: ");
    }

    @Then("user should see home page")
    public void user_should_see_home_page() {
        log.info("Login successful");
    }
}
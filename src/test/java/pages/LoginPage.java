package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import stepdefinitions.LoginSteps;
import utilities.ConfigReader;
import utilities.LoggerHelper;

public class LoginPage {

    WebDriver driver;

    By username = By.id("user-name");
    By password = By.id("password");
    By loginBtn = By.id("login-button");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
    private static final Logger log = LoggerHelper.getLogger(LoginSteps.class);

    public void openWebsite() {
        //get url from properties file
       String url= ConfigReader.getProperty("base.url");
        log.info("user launch the url:{}", url);
        driver.get(url);
    }

    public void enterUsername(String user) {
        driver.findElement(username).sendKeys(user);
        log.info("user enter username:{}", username);
    }

    public void enterPassword(String pass) {
        driver.findElement(password).sendKeys(pass);
        log.info("user enter password:{}", password.hashCode());
    }

    public void clickLogin() {
        driver.findElement(loginBtn).click();
    }
}
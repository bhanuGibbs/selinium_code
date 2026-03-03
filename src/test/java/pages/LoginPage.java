package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import stepdefinitions.LoginSteps;
import utilities.ConfigReader;
import utilities.ExcelUtils;
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
//C:\Users\ganesh.nunna2014\Downloads\OwnProject\bhanuPrakash\TestData.xls
    public String testdatapath="C:\\Users\\ganesh.nunna2014\\Downloads\\OwnProject\\bhanuPrakash\\TestData.xlsx";

    public void openWebsite() {
        //get url from properties file
       String url= ConfigReader.getProperty("base.url");
        log.info("user launch the url:{}", url);
        driver.get(url);
        ExcelUtils.updateExcel(testdatapath,"Data","T-001","Url",url);


    }

    public void enterUsername(String user) {
        driver.findElement(username).sendKeys(user);
        log.info("user enter username:{}", username);
        ExcelUtils.updateExcel(testdatapath,"Data","T-001","EmployerUseriD",user);

    }

    public void enterPassword(String pass) {
        driver.findElement(password).sendKeys(pass);
        log.info("user enter password:{}", password.hashCode());
    }

    public void clickLogin() {
        driver.findElement(loginBtn).click();
    }
}
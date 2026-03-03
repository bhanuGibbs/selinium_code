package Runners;
import org.junit.platform.suite.api.*;
import static io.cucumber.junit.platform.engine.Constants.*;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
//if you want run specific feature file
//@SelectClasspathResource("features/login.feature")

//you want to run by Tags
@ConfigurationParameter(key = FILTER_TAGS_PROPERTY_NAME,
        value = "@login")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME,
        value = "stepdefinitions, hooks")
//@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME,
     //   value = "pretty, html:target/cucumber-report.html")

//Using Cucumber Built-in HTML Report
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME,
        value = "pretty, html:target/cucumber-reports/html-report.html")
public class TestRunner {
}
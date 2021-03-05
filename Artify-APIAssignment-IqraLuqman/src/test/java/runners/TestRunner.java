package runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = ".", 
		plugin = {"pretty","html:target/cucumber-html-report", "json:target/cucumber-json-report.json" },
		monochrome = true, 
		glue={"stepDefinition"},
		tags={"@addingItem,@QueuePositionAndWaitTime,@allEntries,@nextDelivery,@cancelOrder"})

public class TestRunner {

}

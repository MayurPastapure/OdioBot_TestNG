package testRunner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = {".//Features/DashboardFilter.feature"},
		glue = "stepDefination",
		dryRun = false,
		monochrome = true,
		tags = "@Dev",
		plugin = {"pretty", "html:target/cucumber-reports/report.html"}
		)

public class Run { 
	
	
}

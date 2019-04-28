package Runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features="../EmakinaCEE_Assessment/src/main/java/features/RoboForm.feature",
		glue="stepDefinitions",
		plugin ={"pretty" , "html:Test-Output"}
		)

public class TestRunner {

}

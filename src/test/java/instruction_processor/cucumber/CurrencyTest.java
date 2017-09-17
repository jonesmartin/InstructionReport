package instruction_processor.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;


@CucumberOptions(  monochrome = true,
        features = "src/test/resources/features/",
        format = { "pretty","html: cucumber-html-reports",
                "json: cucumber-html-reports/cucumber.json" },
        glue = "instruction_processor.cucumber" )
@RunWith(Cucumber.class)
public class CurrencyTest {
}
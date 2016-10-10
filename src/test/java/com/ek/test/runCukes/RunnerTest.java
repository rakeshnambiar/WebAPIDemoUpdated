package com.ek.test.runCukes;

import com.ek.test.framework.tags.Runner;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;


import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        format = {"pretty", "html:HTML_Report/cucumber/",
                "json:./target/target/cucumber-report.json",
                "json:HTML_Report/cucumber/cucumber-report.json"},
        features = {"src/test/resources/features/Lounge_CustomerList_fromOccupancyReport.feature"},
        glue = {"com.ek.test"}
)
@Category(Runner.class)
public class RunnerTest {


}
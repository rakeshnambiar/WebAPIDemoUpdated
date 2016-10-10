package com.ek.test.runCukes.FaceBookConnect;

import com.ek.test.framework.tags.MPWebTest;
import com.ek.test.framework.tags.Regression;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(tags = {"@TC05_MamasPapasWebTest"},
        format = {"pretty", "html:target/html_report/TC05_MamasPapasWebTest/",
                "json:target/cucumber-report/TC05_MamasPapasWebTest.json"},
        features = {"src/test/resources/features/MamasAndPapas.feature"},
        glue = {"com.ek.test"}
)
@Category({MPWebTest.class, Regression.class})
public class TC05_MamasPapasWebTest {
}
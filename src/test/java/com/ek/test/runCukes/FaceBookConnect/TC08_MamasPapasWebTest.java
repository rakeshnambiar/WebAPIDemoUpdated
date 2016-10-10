package com.ek.test.runCukes.FaceBookConnect;

import com.ek.test.framework.tags.MPWebTest;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(tags = {"@TC08_MamasPapasWebTest"},
        format = {"pretty", "html:target/html_report/TC08_MamasPapasWebTest/",
                "json:target/cucumber-report/TC08_MamasPapasWebTest.json"},
        features = {"src/test/resources/features/MamasAndPapas.feature"},
        glue = {"com.ek.test"}
)
@Category({MPWebTest.class})
public class TC08_MamasPapasWebTest {
}
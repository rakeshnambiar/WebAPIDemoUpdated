package com.ek.test.runCukes.API_GetCategories;

import com.ek.test.framework.tags.CategoryAPI;
import com.ek.test.framework.tags.MPWebTest;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(tags = {"@TC01_APICategoriesTest"},
        format = {"pretty", "html:target/html_report/TC01_APICategoriesTest/",
                "json:target/cucumber-report/TC01_APICategoriesTest.json"},
        features = {"src/test/resources/features/API_Categories.feature"},
        glue = {"com.ek.test"}
)
@Category({CategoryAPI.class})
public class TC01_APICategoriesTest {
}
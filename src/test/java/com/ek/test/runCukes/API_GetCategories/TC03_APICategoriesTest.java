package com.ek.test.runCukes.API_GetCategories;

import com.ek.test.framework.tags.CategoryAPI;
import com.ek.test.framework.tags.MPWebTest;
import com.ek.test.framework.tags.Regression;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(tags = {"@TC03_APICategoriesTest"},
        format = {"pretty", "html:target/html_report/TC03_APICategoriesTest/",
                "json:target/cucumber-report/TC03_APICategoriesTest.json"},
        features = {"src/test/resources/features/API_Categories.feature"},
        glue = {"com.ek.test"}
)
@Category({CategoryAPI.class, Regression.class})
public class TC03_APICategoriesTest {
}
package com.ek.test.runCukes.API_GetGiftWrappers;

import com.ek.test.framework.tags.MPWebTest;
import com.ek.test.framework.tags.Regression;
import com.ek.test.framework.tags.WrapperAPI;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(tags = {"@TC02_APIGiftWrappingTest"},
        format = {"pretty", "html:target/html_report/TC02_APIGiftWrappingTest/",
                "json:target/cucumber-report/TC02_APIGiftWrappingTest.json"},
        features = {"src/test/resources/features/API_GiftWrapping.feature"},
        glue = {"com.ek.test"}
)
@Category({WrapperAPI.class, Regression.class})
public class TC02_APIGiftWrappingTest {
}
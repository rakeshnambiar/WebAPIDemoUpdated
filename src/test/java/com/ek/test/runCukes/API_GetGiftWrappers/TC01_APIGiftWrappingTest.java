package com.ek.test.runCukes.API_GetGiftWrappers;

import com.ek.test.framework.tags.MPWebTest;
import com.ek.test.framework.tags.WrapperAPI;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(tags = {"@TC01_APIGiftWrappingTest"},
        format = {"pretty", "html:target/html_report/TC01_APIGiftWrappingTest/",
                "json:target/cucumber-report/TC01_APIGiftWrappingTest.json"},
        features = {"src/test/resources/features/API_GiftWrapping.feature"},
        glue = {"com.ek.test"}
)
@Category({WrapperAPI.class})
public class TC01_APIGiftWrappingTest {
}
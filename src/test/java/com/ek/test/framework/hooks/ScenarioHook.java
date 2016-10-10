package com.ek.test.framework.hooks;


import com.ek.test.pages.PageFactory;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.awt.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static com.ek.test.framework.hooks.BrowserManagement.driver;

public class ScenarioHook {
    public static Scenario scenario;
    @Before(order = 1)
    public void KeepSceario(Scenario scenario){
        this.scenario = scenario;
        this.setScenario(scenario);
    }

    public void setScenario(Scenario scenario) {
        this.scenario = scenario;
    }

    public static Scenario getScenario() {
        return scenario;
    }

    public static void takeScreenshot(){
        try {
            scenario.embed(((TakesScreenshot) PageFactory.getDriver()).getScreenshotAs(OutputType.BYTES), "image/png");
        }catch (Exception e){
            //Do nothing
        }
    }
}

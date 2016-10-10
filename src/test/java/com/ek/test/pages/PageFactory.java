package com.ek.test.pages;

import org.openqa.selenium.WebDriver;


public class PageFactory {

    private static WebDriver driver;

    public static WebDriver getDriver(){
        return driver;
    }

    public static void setDriver(WebDriver driverInstance){
        driver = driverInstance;
    }

    public static HomePage getHomePage(){
        return new HomePage();
    }

    public static FaceBookPage getFaceBookPage(){
        return new FaceBookPage();
    }

    public static SearchPage getSearchPage(){
        return new SearchPage();
    }

    public static ProductDetailPage getProductPage(){
        return new ProductDetailPage();
    }
}

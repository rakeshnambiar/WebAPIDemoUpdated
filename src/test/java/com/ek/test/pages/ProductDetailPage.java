package com.ek.test.pages;

import com.ek.test.framework.helpers.WaitHelper;
import com.ek.test.framework.helpers.WebElementHelper;
import com.ek.test.framework.hooks.ScenarioHook;
import org.apache.commons.collections.map.HashedMap;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductDetailPage extends BasePage {
    public List<WebElement>  getProductImages() throws Exception {
        List<WebElement> images = new ArrayList<WebElement>();
        try{
            WebElement thumbnail = getElement("Thumbnails");
            images = thumbnail.findElements(By.xpath("./*"));
            return images;
        }catch (Exception e){
            return images;
        }
    }

    public boolean compareImageSource() throws Exception {
        boolean matchFlag = false;
        try{
            List<String> imgSource = new ArrayList<String>();
            List<WebElement> images = getProductImages();
            for(int iterator=0; iterator < images.size(); ++ iterator){
                String imageSource = images.get(iterator).findElement(By.tagName("a")).getAttribute("href");
                imgSource.add(imageSource);
            }
            Collections.sort(imgSource);
            for(int comparator=0; comparator < images.size()-1; ++comparator){
                if(!imgSource.get(comparator).equalsIgnoreCase(imgSource.get(comparator+1))){
                    matchFlag = true;
                }
                else
                {
                    matchFlag = false;
                    break;
                }
            }
        }catch (Exception e){
            throw new Exception("ERROR: While comparing the Image sources");
        }
        return matchFlag;
    }

    public boolean isProductDetailsPageDisplayed() throws Exception {
        try{
            return WebElementHelper.isElementDisplayed(getElement("ProductDetailLabel"));
        }catch (Exception e){
            throw new Exception("ERROR: While Verifying the Product Details page");
        }
    }

    public boolean verifyProductTitle() throws Exception {
        try{
            if(WebElementHelper.isElementDisplayed(getElement("ProductTitle"))){
                ScenarioHook.getScenario().write("Title - "+WebElementHelper.getElementValue(getElement("ProductTitle")));
                return true;
            }
            else
            {
                return false;
            }
        }catch (Exception e){
            throw new Exception("ERROR: While Verifying the Product Title");
        }
    }

    public boolean verifyDefaultImageSource() throws Exception {
        try{
            String imgSource = getElement("DefaultImg").getAttribute("href");
            if(imgSource.length() > 1){
                return true;
            }
            else
            {
                return false;
            }
        }catch (Exception e){
            throw new Exception("ERROR: While Verifying the Main Image Source");

        }
    }

    public void scrollDownThumbnails() throws Exception {
        try{
            WebElement footerElement = getElement("Thumbnails");
            new Actions(PageFactory.getDriver()).moveToElement(footerElement).click().perform();
            new Actions(PageFactory.getDriver()).moveToElement(footerElement).click().perform();
        }catch (Exception e){
            //Do nothing);
        }
    }

    public void scrollDownFavButton() throws Exception {
        try{
            WebElement footerElement = getElement("AddToFav");
            new Actions(PageFactory.getDriver()).moveToElement(footerElement).perform();
            new Actions(PageFactory.getDriver()).moveToElement(footerElement).perform();
        }catch (Exception e){
            //Do nothing);
        }
    }

    public boolean isQuantityBarDisplayed() throws Exception {
        try{
            return WebElementHelper.isElementDisplayed(getElement("QuantityBar"));
        }catch (Exception e){
            throw new Exception("ERROR: While Verifying the Quantity Bar");
        }
    }

    public boolean isAddToFavouriteDisplayed() throws Exception {
        try{
            return WebElementHelper.isElementDisplayed(getElement("AddToFav"));
        }catch (Exception e){
            throw new Exception("ERROR: While Verifying the Add To Favourite Button");
        }
    }

    public void clickAddToFavouriteButton() throws Exception {
        try{
            if(WebElementHelper.getElementValue(getElement("AddToFav")).contains("REMOVE")){
                WebElementHelper.clickElement(getElement("AddToFav"));
                WaitHelper.waitFor(500);
            }
            WebElementHelper.clickElement(getElement("AddToFav"));
            WaitHelper.waitFor(500);
        }catch (Exception e){
            throw new Exception("ERROR: While Verifying the Add To Favourite Button");
        }
    }

    public boolean verifyMaximumQuantity(int maxQty) throws Exception {
        try{
            int qtyValue = Integer.parseInt(WebElementHelper.getElementValue2(getElement("Quantity")));
            if(maxQty >= qtyValue){
                return true;
            }
            else
            {
                return false;
            }
        }catch (Exception e){
            throw new Exception("ERROR: While Verifying the Maximum Quantity");
        }
    }

    public void addMaximumQuantity(int maxQty) throws Exception {
        try{
            WaitHelper.waitForPageLoad();
            int quantity = 1;
            for(int iterator=0; iterator < maxQty; ++iterator){
                WebElementHelper.clickElement(getElement("AddQtyBtn"));
                quantity = quantity+1;
            }
            ScenarioHook.getScenario().write("Currently added Quantity - "+quantity);
        }catch (Exception e){
            throw new Exception("ERROR: While Adding the Maximum Quantity");
        }
    }

    public boolean verifyToggleFunctionality(String btnPreviousVal, String btnNewVal) throws Exception {
        try{
            String currentText = WebElementHelper.getElementValue(getElement("AddToFav"));
            ScenarioHook.getScenario().write("Current Button Text - "+currentText);
            if(currentText.equalsIgnoreCase(btnNewVal)){
                if(!btnPreviousVal.equalsIgnoreCase(currentText)) {
                    return true;
                }
                else {
                    return false;
                }
            }
            else
            {
                return false;
            }

        }catch (Exception e){
            throw new Exception("ERROR: While Adding the Maximum Quantity");
        }
    }
}

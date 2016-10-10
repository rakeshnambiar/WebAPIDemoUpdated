package com.ek.test.pages;

import java.io.FileInputStream;
import java.io.IOException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;

import com.ek.test.framework.helpers.PropertyHelper;
import com.ek.test.framework.helpers.WaitHelper;
import com.ek.test.framework.helpers.WebElementHelper;


public class BasePage {
    private static final String BASE_PATH = "src/test/java/";
    private static final int WEBELEMENT_DEFAULT_TIMEOUT = 20;
    private WebDriver driver;
    private Properties properties;
    private int dateDiff = 0;

    public BasePage() {
        this.driver = PageFactory.getDriver();
        try {
            String filename = this.getClass().getName().replaceAll("\\.", "/") + ".properties";

            properties = new Properties();
            FileInputStream input = new FileInputStream(BASE_PATH + filename);
            properties.load(input);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String name) {
        return properties.getProperty(name);
    }

    public boolean verifyPage() throws Exception {
        String title = getProperty("title");
        if (title.equalsIgnoreCase(driver.getTitle())) {
            return true;
        } else {
            throw new Exception(String.format("Expected Page %s but found %s", title, driver.getTitle()));
        }
    }

    public boolean verifyPage(String element) throws Exception {
        int counter = 2*WEBELEMENT_DEFAULT_TIMEOUT;
        while (counter > 0) {
            try {
                getElement(element);
                return true;
            } catch (Exception e) {
                WaitHelper.waitFor(100);
                counter = counter - 1;
            }
        }
        throw new Exception("Page not loaded.........");
    }

    public void loadPage() throws Exception {
        try {
            driver.get(PropertyHelper.getBaseUrl() + getProperty("page.path"));
        } catch (NullPointerException e) {
            throw new Exception("Driver may not have been initialised, please check hooks been implemented " + e.getMessage());
        } catch (IOException e) {
            throw new Exception("Page could not be loaded. Error occured getting the url " + e.getMessage());
        }
    }

    public WebElement getElement(String name) throws Exception {
        String elementName = getProperty(name);
        String type = getProperty(name + "Type");
        WebElement element;
        if (elementName != null && type == null) {
            throw new Exception("Element type cannot be must be provided in the page properties file");
        }
        try {
            element = getWebElement(elementName, type);
            if (element == null) {
                throw new Exception();
            }
        } catch (Exception e) {
            throw new Exception(String.format("Element %s is not found in the page", name));
        }
        return element;
    }

    public WebElement getElement(String elementName, String type) throws Exception {
        WebElement element;
        if (elementName != null && type == null) {
            throw new Exception("Element type cannot be must be provided in the page properties file");
        }
        element = getWebElement(elementName, type);
        return element;
    }

    public WebElement getWebElement(String elementName, String type) throws Exception {
        WebElement element = null;
        //driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS); 
        int seconds = WEBELEMENT_DEFAULT_TIMEOUT;
        long time = 1000 * seconds;
        boolean timeout = false;
        while (!timeout && time > 0) {
            try {
                if (type.equalsIgnoreCase("id")) {
                    element = driver.findElement(By.id(elementName));
                } else if (type.equalsIgnoreCase("css")) {
                    element = driver.findElement(By.cssSelector(elementName));
                } else if (type.equalsIgnoreCase("class")) {
                    element = driver.findElement(By.className(elementName));
                } else if (type.equalsIgnoreCase("partialLink")) {
                    element = driver.findElement(By.partialLinkText(elementName));
                } else if (type.equalsIgnoreCase("xpath")) {
                    element = driver.findElement(By.xpath(elementName));
                } else if (type.equalsIgnoreCase("name")) {
                    element = driver.findElement(By.name(elementName));
                } else if (type.equalsIgnoreCase("tagname")) {
                    element = driver.findElement(By.tagName(elementName));
                } else {
                    throw new Exception(String.format("Element type %s is not supported at the moment", type));
                }
                timeout = true;
            } catch (Exception e) {
                timeout = false;
                Thread.sleep(100);
                time = time - 100;
            }
        }
        if (!timeout) {
            throw new Exception("Element not clickable at the moment");
        }
        return element;
    }	
	
	public List<WebElement> getElements(String name) throws Exception {
		String elementName = getProperty(name);
		String type = getProperty(name + "Type");
		List<WebElement> elementList = new ArrayList<WebElement>();
		try {
			elementList = getWebElements(elementName, type);
			if (elementList.size() == 0) {
				throw new Exception();
			}
		} catch (Exception e) {
			throw new Exception(String.format(
					"Element %s is not found in the page", name));
		}
		return elementList;
	}

	public List<WebElement> getListOptions(String locator) throws Exception {
		int seconds = WEBELEMENT_DEFAULT_TIMEOUT;
		long time = 1000 * seconds;
		List<WebElement> listOptions = null;
		while (time > 0){
			listOptions = getElements(locator);
			if(listOptions!=null && listOptions.size()>0){
				break;
			}else{
				Thread.sleep(100);
				time = time - 100;
			}
		}
		return listOptions;
		
	}

	public List<WebElement> getWebElements(String locator, String type)
			throws Exception {
		int seconds = WEBELEMENT_DEFAULT_TIMEOUT;
		//driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		long time = 1000 * seconds;
		boolean timeout = false;
		List<WebElement> elementList = new ArrayList<WebElement>();
		while (!timeout && time > 0) {
			try {
				if (type.equalsIgnoreCase("id")) {
					elementList = driver.findElements(By.id(locator));
				} else if (type.equalsIgnoreCase("css")) {
					elementList = driver.findElements(By.cssSelector(locator));
				} else if (type.equalsIgnoreCase("class")) {
					elementList = driver.findElements(By.className(locator));
				} else if (type.equalsIgnoreCase("partialLink")) {
					elementList = driver.findElements(By
							.partialLinkText(locator));
				} else if (type.equalsIgnoreCase("xpath")) {
					elementList = driver.findElements(By.xpath(locator));
				} else if (type.equalsIgnoreCase("name")) {
					elementList = driver.findElements(By.name(locator));
				} else if (type.equalsIgnoreCase("tagname")) {
					elementList = driver.findElements(By.tagName(locator));
				} else {
					throw new Exception(String.format(
							"Element type %s is not supported at the moment",
							type));
				}
				timeout = true;
			} catch (Exception e) {
				timeout = false;
				Thread.sleep(100);
				time = time - 100;
			}
		}
		if (!timeout) {
			throw new Exception(String.format(
					"Element %s could not be found in the page", locator));
		}
		return elementList;

    }


    public boolean closeMenuLink() throws Exception {
      try {
          WebElementHelper.clickElement(getElement("menuclose"));
          WaitHelper.waitForJStoLoad();
          return true;
      } catch (Exception e) {
    	  throw new Exception("Unable to click the Close of App Menu Link " + e.getMessage());
      }
  }
    
    
//    public void setLounge(String loungeCode) throws Exception {
//      WebElementHelper.clickElement(getElement("loungeName"));
//      try {
//          boolean found = Boolean.FALSE;
//          List<WebElement> allTierElemnts =getListOptions("allWebElements"); 
//          for (WebElement webitems : allTierElemnts) {
//              String val = WebElementHelper.getText(webitems);
//              if (!val.equalsIgnoreCase("") && val.equalsIgnoreCase(loungeCode)) {
//                  WebElementHelper.clickElement(webitems);
//                  found = Boolean.TRUE;
//                  break;
//              }
//          }
//          if (!found) {
//              throw new Exception("Lounge " + loungeCode + " not Found!");
//          }
//      } catch (Exception e) {
//          throw new Exception("Lounge is not set");
//      }
//  }

    public boolean openAppMenu() throws Exception {
		try {
			WaitHelper.waitUntilVisible(getElement("appMenuButton"));
			WebElementHelper.clickElement(getElement("appMenuButton"));
			return true;
		} catch (Exception e) {
			throw new Exception("Unable to Open the Menu"+e.getMessage());
		}
	}

	
	public void focus(String element){
		try{
			WebElementHelper.clickElement(getElement(element));
		}catch(Exception e){
			//Ignore the exception
		}
		
	}
	
	public WebElement getWebElementByCssSelector(String elementName)
			throws Exception {
		List<WebElement> element = null;
		//driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		int seconds = WEBELEMENT_DEFAULT_TIMEOUT;
		long time = 1000 * seconds;
		boolean timeout = false;
		while (!timeout && time > 0) {
			try {
				
					element = driver.findElements(By.cssSelector("*["+ elementName+ "]"));
				
					timeout = true;
			} catch (Exception e) {
				timeout = false;
				Thread.sleep(100);
				time = time - 100;
			}
		}
		if (!timeout) {
			throw new Exception("Element not clickable at the moment");
		}
		return element.get(0);
	}
	
	public String getElementText(String locator) throws Exception {
		int seconds = WEBELEMENT_DEFAULT_TIMEOUT;
		long time = 1000 * seconds;
		String elementText = null;
		while (time > 0) {
			try {
				elementText =  WebElementHelper.getAttributeValue(getElement(locator), "value");
						//.getElementValue(getElement(locator)); 
						//WebElementHelper
						//.getElementValue(getElement(locator));
				if (!StringUtils.isEmpty(elementText)) {
					return elementText;
				} else {
					Thread.sleep(100);
					time = time - 100;
				}
			} catch (Exception e) {
				Thread.sleep(100);
				time = time - 100;
			}
		}
		return elementText;
	}

   public String getCalendarSelectedDate(WebElement CalendarElement) throws Exception {
       String selectedDate = null;
       try{
            if(CalendarElement.isDisplayed()) {
                WebElementHelper.clickElement(CalendarElement);
            }
            String dateClassName = null;
            List<WebElement> availableDate = getCalendarDateItems();
            for(int iterator=availableDate.size()-1;iterator > 0; --iterator ){
                dateClassName = availableDate.get(iterator).getAttribute("class");
                if(dateClassName.contains("md-calendar-selected-date")){
                    selectedDate = availableDate.get(iterator).getAttribute("aria-label");
                    break;
                }
            }
       }catch (Exception e){
           throw new Exception("ERROR: While Clicking on the Calendar Control");
       }
       return selectedDate;
   }



  public List<WebElement> getCalendarDateItems() throws Exception {
      List<WebElement> availableDate = getElements("availableDate");
      return availableDate;
  }

  public void setCalendarDate(WebElement CalendarElement, Date dateValue) throws Exception {
      String dateClassName = null;
      List<WebElement> dtValues;
      DateFormat dateFormat = new SimpleDateFormat("EEEE MMMM d yyyy");
      DateFormat dateFormatDefault = new SimpleDateFormat("dd-MMM-yyyy");
      String expectedVal = dateFormat.format(dateValue);
      String[] dateComponent = expectedVal.toString().split(" ");
      try {
          WebElementHelper.clickElement(CalendarElement);
          int year = Integer.parseInt(dateComponent[dateComponent.length - 1]);
          if ((year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0))) {
              dateDiff = -366;
          } else {
              dateDiff = -365;
          }
          Date date = new Date();
          Date pastOneYear = addDays(date, dateDiff);
          boolean dateFlag = true;
          String currentDt;
          while (dateFlag == true) {
              dtValues = getCalendarDateItems();
              currentDt = dtValues.get(0).getAttribute("aria-label");
              DateFormat format = new SimpleDateFormat("EEEE MMMM d yyyy", Locale.ENGLISH);
              Date minDate = format.parse(currentDt);
              if ((dateValue.compareTo(minDate) < 0) || (minDate.compareTo(pastOneYear)<0)) {
                  dtValues.get(0).click();
                  WebElementHelper.clickElement(CalendarElement);
              } else {
                  for (int iterator = dtValues.size() - 1; iterator > 0; --iterator) {
                      dateClassName = dtValues.get(iterator).getAttribute("aria-label");
                      if (dateClassName.equalsIgnoreCase(expectedVal)) {
                          dtValues.get(iterator).click();
                          dateFlag = false;
                          break;
                      }
                  }
              }
          }
      }catch (Exception e){
          throw new Exception("ERROR: When Selecting the Value :"+dateValue);
      }
  }

   public static Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }



    public void waitUntilDisplayed(String element) throws Exception{
        long time = 1000 * WEBELEMENT_DEFAULT_TIMEOUT;
        while(time>0){
          try{
                getElement(element);
                if(element!=null){
                  break;
                }
          }catch(Exception e){
            Thread.sleep(100);
            time = time - 100;
          }
        }
    }
}

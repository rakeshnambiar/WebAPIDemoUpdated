package com.ek.test.framework.ServiceImplementation;


import com.ek.test.framework.helpers.PropertyHelper;
import com.ek.test.framework.hooks.ScenarioHook;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jayway.restassured.response.Response;

import com.jayway.restassured.specification.RequestSpecification;


import java.util.*;

import static com.jayway.restassured.RestAssured.given;

public class GiftWrappingImpl {

    private static Response response;
    private static JsonArray itemArrayObj = null;
    public static void callRESTAPIGiftWrapper(String header1, String header2, String header3, String header4) throws Exception {
        try{
            RequestSpecification header = given().header("Accept","application/json").and()
                    .header("content-Type","application/json");
            header.auth().oauth(header1,header2,header3,header4);
            response = header.when().get(PropertyHelper.getProperty("giftWrapperServiceURL"));
            ScenarioHook.scenario.write(response.prettyPrint());
            String responseStr = response.asString();
            JsonObject objResponseJson = new JsonParser().parse(responseStr).getAsJsonObject();
            itemArrayObj = objResponseJson.get("items").getAsJsonArray();
        }catch (Exception e){
            throw new Exception("ERROR: While hitting the Gift Wrapper REST API");
        }
    }

    public static int getItemsCount() throws Exception {
        int count = 0;
        try{
            count = itemArrayObj.size();
        }catch (Exception e){
            throw new Exception("ERROR: While Getting the Item Count from Gift Wrapper REST API");
        }
        return count;
    }

    public static boolean verifyServiceStatusCode(int expCode) throws Exception {
        boolean statusFlag = false;
        try{
            if(response.getStatusCode()==expCode){
                statusFlag = true;
            }
            else
            {
                ScenarioHook.scenario.write("Actual Status Code - "+response.getStatusCode());
            }
        }catch (Exception e){
            throw new Exception("ERROR: While retrieving the Response Code");
        }
        return statusFlag;
    }

    //itemArrayObj.get(0).getAsJsonObject().get("base_currency_code")

    public static boolean verifyBaseCurrency() throws Exception {
        boolean currencyFlag = false;
        try{
            int items = getItemsCount();
            for(int iterator=0; iterator < items; ++iterator){
                String currency = itemArrayObj.get(iterator).getAsJsonObject().get("base_currency_code").toString();
                if(currency.length()>0){
                    ScenarioHook.scenario.write("Item - "+(iterator+1)+" having the Currency Value :"+currency);
                    currencyFlag= true;
                }
                else
                {
                    iterator = iterator+1;
                    ScenarioHook.scenario.write("Item -"+iterator+" having blank value" );
                    currencyFlag = false;
                    break;
                }
            }
        }catch (Exception e){
            throw new Exception("ERROR: While retrieving the Base Currency Code");
        }
        return currencyFlag;
    }

    public static boolean verifyBasePrice() throws Exception {
        boolean priceFlag = false;
        try{
            int items = getItemsCount();
            for(int iterator=0; iterator < items; ++iterator){
                String currency = itemArrayObj.get(iterator).getAsJsonObject().get("base_price").toString();
                if(currency.length()>0){
                    ScenarioHook.scenario.write("Item -"+(iterator+1)+" having the Price Value :"+currency);
                    priceFlag= true;
                }
                else
                {
                    iterator = iterator+1;
                    ScenarioHook.scenario.write("Item -"+iterator+" having blank value" );
                    priceFlag = false;
                    break;
                }
            }
        }catch (Exception e){
            throw new Exception("ERROR: While retrieving the Base Price");
        }
        return priceFlag;
    }
}

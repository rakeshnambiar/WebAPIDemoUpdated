package com.ek.test.framework.ServiceImplementation;

import com.ek.test.framework.helpers.PropertyHelper;
import com.ek.test.framework.hooks.ScenarioHook;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;


import static com.jayway.restassured.RestAssured.given;


public class GetCategories {
    private static Response response = null;
    private static JsonObject objResponseJson = null;

    public static void callRESTAPIGetCategories() throws Exception {
        try{
            RequestSpecification header = given().header("content-Type","application/json");
            response = header.when().get(PropertyHelper.getProperty("categoryServiceURL"));
            ScenarioHook.getScenario().write(response.prettyPrint());
            String responseStr = response.asString();
            objResponseJson = new JsonParser().parse(responseStr).getAsJsonObject();
        }catch (Exception e){
            throw new Exception("ERROR: While hiting the REST Service - getCategories");
        }
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

    public static int getCategoryCount() throws Exception {
        try{
            int actualCount = objResponseJson.get("children_data").getAsJsonArray().size();
            ScenarioHook.scenario.write("Available Categories Count - "+actualCount);
            return actualCount;
        }catch (Exception e){
            throw new Exception("ERROR: While retrieving the Actual Categories count");
        }
    }

    public static boolean verifyIdTagAvailable() throws Exception {
        boolean idFlag = false;
        try{
            JsonObject childNode = null;
            int ctgCount = getCategoryCount();
            for(int iterator=0; iterator < ctgCount; ++iterator){
                childNode = objResponseJson.get("children_data").getAsJsonArray().get(0).getAsJsonObject().get("children_data").getAsJsonArray().get(0).getAsJsonObject().get("children_data").getAsJsonArray().get(iterator).getAsJsonObject();
                if(childNode.get("id").toString().length() > 0){
                    ScenarioHook.scenario.write("Category -"+(iterator+1)+" having the ID Value :"+childNode.get("id").toString());
                    idFlag= true;
                }
                else
                {
                    iterator = iterator+1;
                    ScenarioHook.scenario.write("Category -"+iterator+" having blank value" );
                    idFlag = false;
                    break;
                }
            }
        }catch (Exception e){
            throw new Exception("ERROR: While verifying the ID tag");
        }
        return idFlag;
    }

    public static boolean verifyNameTagAvailable() throws Exception {
        boolean nameFlag = false;
        try{
            JsonObject childNode = null;
            int ctgCount = getCategoryCount();
            for(int iterator=0; iterator < ctgCount; ++iterator){
                childNode = objResponseJson.get("children_data").getAsJsonArray().get(0).getAsJsonObject().get("children_data").getAsJsonArray().get(0).getAsJsonObject().get("children_data").getAsJsonArray().get(iterator).getAsJsonObject();
                if(childNode.get("name").toString().length() > 0){
                    ScenarioHook.scenario.write("Category -"+(iterator+1)+" having the NAME Value :"+childNode.get("name").toString());
                    nameFlag= true;
                }
                else
                {
                    iterator = iterator+1;
                    ScenarioHook.scenario.write("Category -"+iterator+" having blank value" );
                    nameFlag = false;
                    break;
                }
            }
        }catch (Exception e){
            throw new Exception("ERROR: While verifying the NAME tag");
        }
        return nameFlag;
    }
}

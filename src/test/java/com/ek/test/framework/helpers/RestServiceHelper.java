package com.ek.test.framework.helpers;


import com.ek.test.framework.hooks.ScenarioHook;
import com.jayway.restassured.response.Response;

import static com.jayway.restassured.RestAssured.basePath;
import static com.jayway.restassured.RestAssured.given;

public class RestServiceHelper {
    public static boolean getRESTserviceResponse(String ServiceName){
        int ResponseCode = 0;
        boolean serviceState = false;
        try {
            Response response = given().relaxedHTTPSValidation("TLSv1").when().post(ServiceName).then().extract().response();
            if(response.getStatusCode()!=200 && response.getStatusCode()!=405){
                response =
                        given().get(ServiceName)
                                .then().extract().response();
            }
            ResponseCode = response.getStatusCode();
            if(ResponseCode==200){
                serviceState = true;
            }
        }catch (Exception e){
            e.printStackTrace();
            ScenarioHook.getScenario().write("I/O Exception occured while connecting to service");
        }
        return serviceState;
    }
}

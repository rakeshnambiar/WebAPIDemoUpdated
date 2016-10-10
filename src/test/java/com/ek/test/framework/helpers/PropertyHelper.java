package com.ek.test.framework.helpers;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class PropertyHelper {
    private static final String PROPERTY_FILE_PATH = "src/test/resources/Config.properties";
    private static PropertyHelper propertyHelper;
    private static Properties properties;

    private PropertyHelper() throws Exception {
        properties = new Properties();
        FileInputStream input = new FileInputStream(PROPERTY_FILE_PATH);
        try {
            properties.load(input);
        } catch (IOException e) {
            throw new Exception("There was a problem in reading the property file \n" + e.getMessage());
        }
        properties.putAll(System.getProperties());
    }

    public static String getProperty(String property) throws Exception {
        if(properties == null){
            new PropertyHelper();
        }
        return properties.getProperty(property);
    }

    public static String getBaseUrl() throws Exception {
        return getProperty("applicationURL");
    }

}

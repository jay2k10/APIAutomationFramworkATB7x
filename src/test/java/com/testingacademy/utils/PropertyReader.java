package com.testingacademy.utils;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertyReader {
    //The Responsibility of this class is to give the value of the key.
    public static String readkey(String key) {
        Properties properties = new Properties();
        //Legacy 1.2 JDK - Old

        try {
            //Read the data from the properties file and give the key
            FileInputStream fileInputStream = new FileInputStream("src/test/java/resources/data.properties");
            properties.load(fileInputStream);


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return String.valueOf(properties.getProperty(key));

    }
}

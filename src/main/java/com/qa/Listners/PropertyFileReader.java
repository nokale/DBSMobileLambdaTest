package com.qa.Listners;

import com.qa.Utils.TestUtils;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileReader {

    private Properties prop;
    TestUtils test ;

    public PropertyFileReader(String filePath) {
        prop = new Properties();
        loadProperties(filePath);
    }

    private void loadProperties(String filePath) {
        try (FileReader reader = new FileReader(filePath)) {
            prop.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
            test.log().info("Load Properties: " + prop);
            throw new RuntimeException("Error loading properties file: " + e.getMessage());
        }
    }

    public String getProperty(String key) {
        return prop.getProperty(key);
    }

    public Properties getProperties() {
        return prop;
    }

    public String getSheetName() {
        return getProperty("excel.sheetName");
    }
    public String getExcel() {
        return getProperty("excel.path");
    }

}

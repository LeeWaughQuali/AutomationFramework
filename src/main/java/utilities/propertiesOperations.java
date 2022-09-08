package utilities;

import org.apache.commons.lang3.StringUtils;

import java.io.FileInputStream;
import java.util.Properties;

public class propertiesOperations {

    static Properties prop = new Properties();

    public static String getPropertyValueByKey(String key) throws Exception {

        String propFilePath = System.getProperty("user.dir") + "/src/test/resources/config.properties";
        FileInputStream fis;
        try {
            fis = new FileInputStream(propFilePath);
            prop.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String value = prop.get(key).toString();

        if (StringUtils.isEmpty(value)) {
            try {
                throw new Exception("Value is not specified for key: " + key + " in properties file.");
            } catch (Exception e) {
            }
        }

            return value;
        }
    }

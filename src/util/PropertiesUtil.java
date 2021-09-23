package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Properties;

public class PropertiesUtil {

    public static Properties getProperty(String fileName) {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    public static String getValueString(String fileName, String key) {
        return getProperty(fileName).getProperty(key);
    }

    public static int getValueInt(String fileName, String key) {
        return Integer.parseInt(getProperty(fileName).getProperty(key));
    }

    public static String getValueFormattedString(String fileName, String key, Object[] obj) {
        return MessageFormat.format(getProperty(fileName).getProperty(key), obj);
    }
}

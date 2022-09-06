package Shared.Utility;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class ConfigUtils {

    public String getConfig(String key) throws IOException {
        FileInputStream file = new FileInputStream(System.getProperty("user.dir") + "/shared/config/config.properties");
        Properties prop = new Properties();
        prop.load(file);
        return prop.getProperty(key);
    }

    public void getConfig(String key, String value) throws IOException {
        FileInputStream file = new FileInputStream(System.getProperty("user.dir") + "/shared/config/config.properties");
        Properties prop = new Properties();
        prop.load(file);
        prop.setProperty(key, value);
        FileWriter writer = new FileWriter(System.getProperty("user.dir") + "/shared/config/config.properties");
        prop.store(writer, "update config");
        writer.close();
    }

}

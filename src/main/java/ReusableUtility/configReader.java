package ReusableUtility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class configReader {
    private static final Properties prop = new Properties();

    static {
        try {
            FileInputStream fis = new FileInputStream("src/main/resources/Data.properties");
            prop.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties file", e);
        }
    }

    public static String getProperty(String key) {
        return prop.getProperty(key);
    }
}

package db.ext;

import java.io.FileReader;
import java.io.Reader;
import java.util.Properties;

public class PropertyProvider {

    public static PropertyProvider instance;
    private Properties properties;

    private PropertyProvider() {

    }

    public static PropertyProvider getInstance() {
        if (instance == null) {
            instance = new PropertyProvider();
            instance.loadProperties();
        }

        return instance;
    }

    public Properties getProperties()
    {
        return properties;
    }

    private void loadProperties() {
        properties = new Properties();
        String env = System.getProperty("env", "prod");
        try {
            properties.load(new FileReader("src/main/resources/" + env + ".properties"));
        } catch (Exception exception) {
            System.err.println(exception.getMessage());
        }
    }
}
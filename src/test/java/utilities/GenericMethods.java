package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GenericMethods {

    //This method is getting value from the config properties file
    public static String getProperty(String key) throws IOException {
        InputStream input = new FileInputStream("configProperties");
        Properties prop = new Properties();
        prop.load(input);
        return prop.getProperty(key);
    }
}

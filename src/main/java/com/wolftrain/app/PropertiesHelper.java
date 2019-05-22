package main.java.com.wolftrain.app;

import java.io.*;
import java.net.URL;
import java.util.Objects;
import java.util.Properties;

public class PropertiesHelper {

	private Properties prop;
	
	public PropertiesHelper() throws IOException {
		prop = new Properties();
		String fileName = "config.properties";
        ClassLoader classLoader = PropertiesHelper.class.getClassLoader();
        
        // Make sure that the configuration file exists
        URL res = Objects.requireNonNull(classLoader.getResource(fileName),
            "Can't find configuration file config.properties");

        InputStream is = new FileInputStream(res.getFile());

        // load the properties file
        prop.load(is);
	}
	
	public Properties getProperties() {
		return this.prop;
	}	
}

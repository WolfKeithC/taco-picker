package main.java.com.wolftrain.app;

import java.io.*;
import java.net.URL;
import java.util.Enumeration;
import java.util.Objects;
import java.util.Properties;

public class PropertiesHelper {

	private Properties prop;
	
	public PropertiesHelper() throws IOException {
		prop = new Properties();
		/*
		Enumeration<URL> urls = PropertiesHelper.class.getClassLoader().getResources(".");
		URL u = urls.nextElement();
		System.out.println( u );
		*/
		
		/*
		for(URL u : urls){
    		System.out.println( u );
		}
		*/
		
		String fileName = "config.properties";
		InputStream is = PropertiesHelper.class.getClassLoader().getResourceAsStream(fileName);
        prop.load(is);
	}
	
	public Properties getProperties() {
		return this.prop;
	}	
}
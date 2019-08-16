package main.java.com.wolftrain.app;

import java.util.LinkedList;
import java.util.Properties;

/**
 * Hello world!
 *
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

public class App 
{
    public static void main( String[] args ) throws Exception
    {
        //System.out.println( "Hello World!" );
    	//SpringApplication.run(TacoController.class, args);
    	
    	String current = new java.io.File( "." ).getCanonicalPath();
        System.out.println("Current dir:" + current);
        String currentDir = System.getProperty("user.dir");
        System.out.println("Current dir using System:" + currentDir);
        /* 
		PropertiesHelper pHelp = new PropertiesHelper();
		Properties prop = pHelp.getProperties();
		System.out.println(prop.getProperty("azure.connectionstring"));
		System.out.println(prop.getProperty("azure.containername"));
		*/
        try 
        {
	    	AzureStorage azure = new AzureStorage();
	    	String[] files = azure.ListAllFiles("App_Data");
	    	for(String f : files){
	    		System.out.println( f );
	    	}
	    	System.out.println("---------------------");
	    	LinkedList<TacoEntry> orders = TacoHelper.LoadTacoData("5");
	    	for(TacoEntry t : orders) {
	    		System.out.println( t.getName() + " " + t.getTacos().size());
	    	}
        } catch (Exception e) {
        	System.out.println("Error: ");
            e.printStackTrace();
        }
    }
}

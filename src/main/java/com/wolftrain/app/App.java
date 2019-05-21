package main.java.com.wolftrain.app;

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
    	
    	AzureStorage azure = new AzureStorage();
    	
    	String[] files = azure.ListAllFiles("appdata01");
    	
    	for(String f : files){
    		System.out.println( f );
    	}
    }
}

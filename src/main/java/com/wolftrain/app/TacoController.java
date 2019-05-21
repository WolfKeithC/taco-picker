package main.java.com.wolftrain.app;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TacoController {
	
	private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }
    
    
    @RequestMapping("/dropdowns")
    public String Dropdowns()
    {
    	String connStr = "DefaultEndpointsProtocol=https;AccountName=tacodata001;AccountKey=rARzp0A5to2FL2mQN6vVT6OrXVuAVJNbtUs8Z/hPwWdx79WhFQk6yqgdECl4N/N5McOnRmtepmX+9kFnNW4IJg==;BlobEndpoint=https://tacodata001.blob.core.windows.net/;QueueEndpoint=https://tacodata001.queue.core.windows.net/;TableEndpoint=https://tacodata001.table.core.windows.net/;FileEndpoint=https://tacodata001.file.core.windows.net/;";
    			
    	// Parse the connection string and create a blob client to interact with Blob storage
    	CloudStorageAccount storageAccount = CloudStorageAccount.parse(storageConnectionString);
    	blobClient = storageAccount.createCloudBlobClient();
    	container = blobClient.getContainerReference("quickstartcontainer");

    	// Create the container if it does not exist with public access.
    	System.out.println("Creating container: " + container.getName());
    	container.createIfNotExists(BlobContainerPublicAccessType.CONTAINER, new BlobRequestOptions(), new OperationContext());
    			
    	
    	
    	
    	//Listing contents of container
    	for (ListBlobItem blobItem : container.listBlobs()) {
    	    System.out.println("URI of blob is: " + blobItem.getUri());
    	}
    	
    	
    	
    	return "help";
    }
    
    /*
    private void loadDropdowns(Dictionary<String, Object> jsonData)
    {
        foreach (string key in jsonData.Keys)
        {
            switch (key)
            {
                case "tacos":
                    tacos = JsonConvert.DeserializeObject<List<TacoTypes>>(jsonData[key].ToString());
                    break;

                case "tortilla":
                    tortillas = JsonConvert.DeserializeObject<List<string>>(jsonData[key].ToString());
                    break;
            }
        }
    }
    */
}

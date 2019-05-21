package main.java.com.wolftrain.app;

import com.microsoft.azure.storage.*;
import com.microsoft.azure.storage.blob.*;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.Scanner;
import java.util.stream.Stream;

/* *************************************************************************************************************************
* Summary: This application demonstrates how to use the Blob Storage service.
* It does so by creating a container, creating a file, then uploading that file, listing all files in a container, 
* and downloading the file. Then it deletes all the resources it created
* 
* Documentation References:
* Associated Article - https://docs.microsoft.com/en-us/azure/storage/blobs/storage-quickstart-blobs-java
* What is a Storage Account - http://azure.microsoft.com/en-us/documentation/articles/storage-whatis-account/
* Getting Started with Blobs - http://azure.microsoft.com/en-us/documentation/articles/storage-dotnet-how-to-use-blobs/
* Blob Service Concepts - http://msdn.microsoft.com/en-us/library/dd179376.aspx 
* Blob Service REST API - http://msdn.microsoft.com/en-us/library/dd135733.aspx
* *************************************************************************************************************************
*/

public class AzureStorage {
	
	private CloudBlobContainer mainContainer = null;
	
	/* *************************************************************************************************************************
	* Instructions: Update the storageConnectionString variable with your AccountName and Key and then run the sample.
	* *************************************************************************************************************************
	*/
	private final String storageConnectionString =
			"DefaultEndpointsProtocol=https;AccountName=tacodata001;AccountKey=rARzp0A5to2FL2mQN6vVT6OrXVuAVJNbtUs8Z/hPwWdx79WhFQk6yqgdECl4N/N5McOnRmtepmX+9kFnNW4IJg==;BlobEndpoint=https://tacodata001.blob.core.windows.net/;QueueEndpoint=https://tacodata001.queue.core.windows.net/;TableEndpoint=https://tacodata001.table.core.windows.net/;FileEndpoint=https://tacodata001.file.core.windows.net/;";
	
	private final String storageContainerName =
			"tacodata001";
	
	public AzureStorage() {
		
		// Parse the connection string and create a blob client to interact with Blob storage
		CloudStorageAccount mainContainer = CloudStorageAccount.parse(this.storageConnectionString);
		CloudBlobClient blobClient = mainContainer.createCloudBlobClient();
		mainContainer = blobClient.getContainerReference(this.storageContainerName);
	}
	
	public boolean Loaded() { return (this.mainContainer != null ); }
	
	public void AddFile(String intakeId, String fileName, InputStream stream) throws Exception {
		
		//grab a folder reference directly from the container
        CloudBlobDirectory folder = mainContainer.getDirectoryReference(intakeId);
        
        // Retrieve reference to a blob named "myblob".
        CloudBlockBlob blockBlob = folder.getBlockBlobReference(fileName);
        long length = (long)stream.available();
        blockBlob.upload(stream, length);
	}

    public String[] ListAllFiles(String intakeId) throws Exception {
    	
    	//grab a folder reference directly from the container
        CloudBlobDirectory folder = mainContainer.getDirectoryReference(intakeId);

        Iterable<ListBlobItem> blobItems = folder.listBlobs();
        
        int counter = 0;
        for (Object i : blobItems) {
            counter++;
        }

        int i = 0;//, count = mainContainer.listBlobs().;
        String[] files = new String[counter];
        
		//Listing contents of container
		for (ListBlobItem blobItem : mainContainer.listBlobs()) {
			String path = blobItem.getUri().getQuery();
            files[i] = path.substring(path.lastIndexOf('/') + 1);
            i++;
        }

        return files;
    }    

    public byte[] GetFile(String intakeId, String fileName) throws Exception {
    	ByteArrayOutputStream mem = new ByteArrayOutputStream();
    	
    	try {
	    	//grab a folder reference directly from the container
	        CloudBlobDirectory folder = mainContainer.getDirectoryReference(intakeId);
	        CloudBlockBlob blob = folder.getBlockBlobReference(fileName);
	
	        if (!blob.exists()) {
	        	throw new Exception("ERROR: " + intakeId + "\\" + fileName + " does not exist.");
	        }
        
	        blob.download(mem);
	        mem.flush();
		
    	} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
    	}
        return mem.toByteArray();    	
    }

    public void RemoveUploadFolder(String intakeId, String fileName) throws Exception {
    	mainContainer.getDirectoryReference(intakeId).getPageBlobReference(fileName).delete();
    }

    public void RemoveUploadFile(String intakeId, String fileName) throws Exception {
    	mainContainer.getBlockBlobReference(intakeId).deleteIfExists();
    }

    public Date GetFileLastWriteTime(String intakeId, String fileName) throws Exception {
    	CloudBlobDirectory folder = mainContainer.getDirectoryReference(intakeId);
        CloudBlob blob = folder.getPageBlobReference(fileName);
        if (blob.exists())
            return blob.getProperties().getLastModified();
        return new Date(Long.MIN_VALUE);
    }

    public Boolean Exists(String intakeId, String fileName) throws Exception {
    	CloudBlobDirectory folder = mainContainer.getDirectoryReference(intakeId);
        CloudBlob blob = folder.getPageBlobReference(fileName);
        if (blob != null)
            return blob.exists();
        return false;
    }

    public Boolean Exists(String intakeId) throws Exception {
        CloudBlobDirectory folder = mainContainer.getDirectoryReference(intakeId);
        if (folder != null)
            return true;
        return false;
    }
}

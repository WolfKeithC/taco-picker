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
import java.util.Properties;
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
	private String storageConnectionString = "";
	private String storageContainerName = "";
	
	public AzureStorage() throws URISyntaxException, Exception {
		
		PropertiesHelper pHelp = new PropertiesHelper();
		Properties prop = pHelp.getProperties();
		this.storageConnectionString = prop.getProperty("azure.connectionstring");
		this.storageContainerName = prop.getProperty("azure.containername");
		
		// Parse the connection string and create a blob client to interact with Blob storage
		CloudStorageAccount storageAccount = CloudStorageAccount.parse(this.storageConnectionString);
		CloudBlobClient blobClient = storageAccount.createCloudBlobClient();
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
        
        int counter = 0, i = 0;
        for (ListBlobItem blobItem : blobItems) {
            counter++;
        }
        
		//Listing contents of container
        String[] files = new String[counter];
		for (ListBlobItem blobItem : blobItems) {
			String path = blobItem.getUri().toString();
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
    	CloudBlockBlob blob = folder.getBlockBlobReference(fileName);
        if (blob.exists())
            return blob.getProperties().getLastModified();
        return new Date(Long.MIN_VALUE);
    }

    public Boolean Exists(String intakeId, String fileName) throws Exception {
    	CloudBlobDirectory folder = mainContainer.getDirectoryReference(intakeId);
    	CloudBlockBlob blob = folder.getBlockBlobReference(fileName);
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

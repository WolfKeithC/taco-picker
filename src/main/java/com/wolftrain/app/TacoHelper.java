package main.java.com.wolftrain.app;

import java.util.LinkedList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

public class TacoHelper {
	public static LinkedList<TacoEntry> LoadTacoData(String id) throws JsonSyntaxException, Exception
	{
		LinkedList<TacoEntry> orders = new LinkedList<TacoEntry>();

		AzureStorage azure = new AzureStorage();
		
        if (azure.Exists("App_Data", String.format("order_{0}.json", id)))
        {
            String strJson = new String(azure.GetFile("App_Data", String.format("order_{0}.json", id)));
            Gson gson = new GsonBuilder().create();
            orders = gson.fromJson(strJson, new TypeToken<LinkedList<TacoEntry>>(){}.getType());
        }

        return orders;
	}
}

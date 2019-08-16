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
    public String Dropdowns() throws Exception
    {
    	AzureStorage a = new AzureStorage();
    	return new String(a.GetFile("Tacos", "tacosdata.json"));
        //string strJson = Helpers.ByteStreamToString(fileCommands);
        //Dictionary<String, Object> jsonData = JsonConvert.DeserializeObject<Dictionary<String, Object>>(strJson);
        //loadDropdowns(jsonData);
        //object data = Newtonsoft.Json.JsonConvert.DeserializeObject(strJson);
        //return retVal;
    }
    
    @RequestMapping("/details")
    public String Details(string id)
    {
        List<TacoEntry> orders = loadTacoData(id);
        return Json(new { data = orders }, JsonRequestBehavior.AllowGet);
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

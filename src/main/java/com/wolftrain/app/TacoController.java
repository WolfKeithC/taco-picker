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
    
    /*
    @RequestMapping("/dropdowns")
    public String Dropdowns()
    {
    	
    	
    	
    }
    

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

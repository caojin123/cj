package com.k12ct.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Event;
import com.dianping.cat.message.Transaction;  

@Controller
@RequestMapping("/hello")
public class HelloController {
 
    @RequestMapping("/hello")
    public String hello(){    
    	
    	String pageName = "helloworld";
    	String serverIp = "192.168.1.222";
    	double amount = 0;
    	
    	Transaction t = Cat.newTransaction("URL", pageName);
    	
    	try {
    		
			Cat.logEvent("URL.Server", serverIp, Event.SUCCESS, "ip="+ serverIp + "&...");
			
			Cat.logMetricForCount("PayCount");
			
			Cat.logMetricForSum("PayAmont", amount);
			
			t.setStatus(Transaction.SUCCESS);
			
			//Thread.sleep(3000);
//			String abc = null;
//			abc.length();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			t.setStatus(e);
		} finally {
			t.complete();
		}
    	
    	
        return "hello";
    }
}

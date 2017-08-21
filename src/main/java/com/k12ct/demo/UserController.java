package com.k12ct.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Event;
import com.dianping.cat.message.Transaction;


@Controller
@RequestMapping("/user")
public class UserController {

	
	
    @RequestMapping("/query")
    public String query(){    
    	
    	String pageName = "helloworld";
    	String serverIp = "127.0.0.1";
    	double amount = 0;
    /**
     *  用transaction记录一段代码的执行时间，这里的时间是指从创建至complete之间的耗时。
     *  Event记录一个事件，如：一次远程调用的地址。
     *  Metric记录一个业务指标。
     */
    	
    	Transaction t = Cat.newTransaction("URL", pageName);
    	
    	try {
    		
    		 //记录一个事件
			Cat.logEvent("URL.Server", serverIp, Event.SUCCESS, "ip="+ serverIp + "&...");
			//记录一个业务指标，记录次数
			Cat.logMetricForCount("PayCount");
			//
			Cat.logMetricForSum("PayAmont", amount);
			
			t.setStatus(Transaction.SUCCESS);//设置状态
			
			//业务代码
		} catch (Exception e) {
			Cat.getProducer().logError(e);
			t.setStatus(e);
		} finally {
			t.complete();
		}
        return "hello";
    }
}

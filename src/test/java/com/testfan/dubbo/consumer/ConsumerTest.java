package com.testfan.dubbo.consumer;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.testfan.service.inf.ComputeService;

public class ConsumerTest {
	
	   public static void main(String[] args) throws IOException {
	        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext(new String[] {"dubbo-consumer.xml"});
	        ac.start();
	        ComputeService ta = (ComputeService) ac.getBean("computeService");
	         System.out.println(ta.add(1, 2));
	    }

}

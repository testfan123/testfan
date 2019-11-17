/** 
 * Project Name:dubbo-consumer 
 * File Name:JmeDemoAction.java 
 * Package Name:com.ustc.demo.consumer 
 * Date:2016年12月3日下午10:12:10 
 * Copyright (c) 2016, pleasant.work@yahoo.com All Rights Reserved. 
 * 
*/  
  
package com.testfan.dubbo.consumer;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.testfan.service.inf.ComputeService;


public class JmeDubboAction extends AbstractJavaSamplerClient{
	
	
	ClassPathXmlApplicationContext springcontext = new ClassPathXmlApplicationContext(
			new String[] { "dubbo-consumer.xml" });
	
	ComputeService computeService;
	  
	@Override
	public void setupTest(JavaSamplerContext args) {
		computeService = (ComputeService) springcontext.getBean("computeService");
	}
	
	@Override
	public Arguments getDefaultParameters() {
		    Arguments params = new Arguments();  
	        params.addArgument("arg1", "1"); // 定义一个参数，显示到Jmeter的参数列表中，第一个参数为参数默认的显示名称，第二个参数为默认值 
	        params.addArgument("arg2", "2");
	        return params;  
	}

	public SampleResult runTest(JavaSamplerContext context) {
		SampleResult sr = new SampleResult();
		try {	
			sr.sampleStart();
			springcontext.start();
			String arg1 = context.getParameter("arg1"); 
			String arg2 = context.getParameter("arg2"); 
			Integer result = computeService.add(Integer.parseInt(arg1),Integer.parseInt(arg2));
			sr.setSampleLabel("dubbo test");
	        sr.setResponseData(result+" from provider:"+" test dubbo", "UTF-8");
			sr.setDataType(SampleResult.TEXT);
			sr.setSuccessful(true);
		} catch (Exception e) {
			sr.setSuccessful(false);
			e.printStackTrace();
		}finally {
            sr.sampleEnd();// jmeter 结束统计响应时间标记
        }
		return sr;
	}

}
  
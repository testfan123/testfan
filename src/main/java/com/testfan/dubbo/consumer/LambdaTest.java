package com.testfan.dubbo.consumer;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.Stream.Builder;

public class LambdaTest {
	
	public static void main(String[] args) {
		//传统代码
		new Thread(new Runnable() {  
		    @Override  
		    public void run() {  
		        System.out.println("Hello world !");  
		    }  
		}).start(); 
		//Lambda 表达式    语法特点  () ->   脑瓜子疼 
		new Thread(() -> System.out.println("Hello world !")).start();  
		
		String[] str = new String[] { "Lambdas", "Lambdas", "Default Method", "Stream API", "Date and Time API" };
		List<String> strList = Arrays.asList(str);
		//Lambda forEach
		System.out.println("forEach    ---------------------------->");
		strList.stream().forEach(System.out::println);
		//Lambda map
		System.out.println("map    ---------------------------->");
		strList.stream().map(item -> item + "1").forEach(System.out::println);
		
		System.out.println("map   toUpperCase ---------------------------->");
		strList.stream().map(String::toUpperCase).forEach(System.out::println);
		
		//Lambda filter
		System.out.println("filter    ---------------------------->");
		strList.stream().filter(item -> item.contains("API")).forEach(System.out::println);
		
		System.out.println("distinct    ---------------------------->");
        strList.stream().distinct().forEach(System.out::println);
        
        System.out.println("skip    ---------------------------->");
        // 丢弃原Stream的前N个元素后剩下元素组成的新Stream
        strList.stream().skip(3).forEach(System.out::println);
      
        System.out.println("flatMap    ---------------------------->");
        // flatMap：和map类似，不同的是其每个元素转换得到的是Stream对象，会把子Stream中的元素压缩到父集合
        strList.stream().flatMap(item -> getCharacter(item)).forEach(System.out::println);
 
	}
	
	 public static Stream<String> getCharacter(String s) {
	        Builder<String> builder = Stream.builder();
	        builder.add(s);
	        builder.accept("1");
	        return builder.build();
	    }

}

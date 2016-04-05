package com.gop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootWebApplication /*extends SpringBootServletInitializer*/ {
	
	/*
	 * This method is when using outside embedded container
	 */
	
	/*@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(SpringBootWebApplication.class);
	}*/
	
	public static void main(String[] args){
		SpringApplication.run(SpringBootWebApplication.class, args);
	}
}

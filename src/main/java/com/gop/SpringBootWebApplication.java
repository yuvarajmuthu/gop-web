package com.gop;

import java.util.Collections;
import java.util.List;

import org.apache.http.client.HttpClient;
import com.gop.controller.LocationController;
import com.gop.util.HttpClientFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@Configuration
@ComponentScan(basePackageClasses = LocationController.class)
public class SpringBootWebApplication /*extends SpringBootServletInitializer*/ {
	
	/*
	 * (non-Javadoc)
	 * @see org.springframework.boot.context.web.SpringBootServletInitializer#configure(org.springframework.boot.builder.SpringApplicationBuilder)
	 * 
	 * This method is when using outside embedded container
	 */
	
	/*@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(SpringBootWebApplication.class);
	}*/
	
	public static void main(String[] args){
		SpringApplication.run(SpringBootWebApplication.class, args);
	}
	
	@Bean
	public HttpComponentsClientHttpRequestFactory HttpComponentsClientHttpRequestFactory() throws Exception{
		return new HttpComponentsClientHttpRequestFactory(httpClient());
	}
	
	@Bean
	public HttpClient httpClient() throws Exception{
		HttpClientFactoryBean factory = new HttpClientFactoryBean();
		return factory.createInstance();
	}

	@Bean
	public RestTemplate restTemplate() throws Exception {
		RestTemplate restTemplate = new RestTemplate(HttpComponentsClientHttpRequestFactory());
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setSupportedMediaTypes(Collections.singletonList(MediaType.TEXT_HTML));
		List<HttpMessageConverter<?>> converters = restTemplate.getMessageConverters();
		if(converters!=null){
			converters.add(converter);
			restTemplate.setMessageConverters(converters);
		}
	    return restTemplate;
	}
}

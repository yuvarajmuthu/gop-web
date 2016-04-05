package com.gop;

import java.util.Collections;
import java.util.List;

import org.apache.http.client.HttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.gop.util.HttpClientFactoryBean;

@Configuration
public class MvcConfiguration {

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

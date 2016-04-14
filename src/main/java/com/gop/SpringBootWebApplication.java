package com.gop;

import java.util.Collections;
import java.util.List;

import org.apache.http.client.HttpClient;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;

import com.gop.util.HttpClientFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.server.Neo4jServer;
import org.springframework.data.neo4j.server.RemoteServer;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableNeo4jRepositories("com.gop.repository")
@Configuration
@ComponentScan("com.gop")

public class SpringBootWebApplication extends Neo4jConfiguration {
	
public static final int NEO4J_PORT = 7474;
	
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

	 @Override
	    public SessionFactory getSessionFactory() {
	        return new SessionFactory("com.gop.domain");
	    }
	 
	  @Bean
	    public Neo4jServer neo4jServer() {
		  return new RemoteServer("http://localhost:" + NEO4J_PORT);
	    }
	  
	  @Override
	    @Bean
	    @Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
	    public Session getSession() throws Exception {
	        return super.getSession();
	    }
}

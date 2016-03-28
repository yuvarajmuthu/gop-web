package org.gop.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.net.URL;

import com.gop.SpringBootWebApplication;
import com.gop.controller.LocationController;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=SpringBootWebApplication.class)
@WebAppConfiguration
@IntegrationTest({"server.port=0"})
public class LocationControllerIntegrationTest {

	@Value("${local.server.port}")
	private int port;
	private URL base;
	private RestTemplate template;
	
	@Autowired
	private LocationController locationController;
	
	@Before
	public void setUp()throws Exception {
		this.base = new URL("http://localhost:"+port+"/");
		template = new TestRestTemplate();
	}
	
	@Test
	public void testGetDistricts(){
		ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);
		//assertThat(response.getBody(), equalTo(""));
	}
	
	@Test
	public void testGetDistrictNameUsingLatAndLong(){
		
	}
}

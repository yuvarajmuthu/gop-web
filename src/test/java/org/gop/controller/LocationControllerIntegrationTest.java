package org.gop.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.net.URL;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestOperations;

import com.gop.SpringBootWebApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=SpringBootWebApplication.class)
@WebAppConfiguration
@IntegrationTest({"server.port=0"})
public class LocationControllerIntegrationTest {

	@Value("${local.server.port}")
	private int port;
	private URL base;
	private RestOperations template;
	
	
	@Before
	public void setUp()throws Exception {
		this.base = new URL("http://localhost:"+port);
		template = new TestRestTemplate();
	}
	
	@Test
	public void testGetDistrictNameUsingLatAndLong(){
		ResponseEntity<String> response = template.getForEntity(base.toString().concat("/getDistrict"), String.class);
		assertThat(response.getBody(), equalTo("SC-5"));
	}
	
	@Test
	public void testGetLatAndLongUsingDistrictName(){
		ResponseEntity<String> response = template.getForEntity(base.toString().concat("/getLatAndLong"), String.class);
		assertThat(response.getBody(), equalTo("[-81.0195321341162,34.53854793419508]"));
	}
}

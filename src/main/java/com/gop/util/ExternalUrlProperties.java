package com.gop.util;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(locations="classpath:rest_calls.properties")
public class ExternalUrlProperties {

	@NotBlank
	private String location_url;
	
	@NotBlank
	private String sunlight_url;
	
	public String getSunlight_url() {
		return sunlight_url;
	}

	public void setSunlight_url(String sunlight_url) {
		this.sunlight_url = sunlight_url;
	}

	public String getLocation_url() {
		return location_url;
	}

	public void setLocation_url(String location_url) {
		this.location_url = location_url;
	}
}

package com.gop.util;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(locations="classpath:rest_calls.properties")
public class ExternalUrlProperties {

	@NotBlank
	private String location_url;

	public String getLocation_url() {
		return location_url;
	}

	public void setLocation_url(String location_url) {
		this.location_url = location_url;
	}
}

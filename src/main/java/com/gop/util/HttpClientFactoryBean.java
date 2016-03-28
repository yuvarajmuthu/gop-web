package com.gop.util;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.config.AbstractFactoryBean;

public class HttpClientFactoryBean extends AbstractFactoryBean<HttpClient> {

	@Override
    public Class<?> getObjectType() {
        return HttpClient.class;
    }

    @Override
	public HttpClient createInstance() throws Exception {
         CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(null).build();
         return httpClient;
    }

}

package br.com.cit.logistica.rest.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Assert;
import org.junit.Before;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Teste {
	
	private final static String FILE_CONFIG = "/rest-test.properties";
	
	private final static String SERVER_URL_CONF = "server.url";

	private String propertyConfig;

	
	@Before
	public void loadProperties() throws IOException {
		Properties properties = new Properties();
		
		InputStream resourceAsStream = this.getClass().getResourceAsStream(FILE_CONFIG);
		properties.load(resourceAsStream);
		
		resourceAsStream.close();
		
		Assert.assertFalse(properties.isEmpty());
		
		propertyConfig = properties.getProperty(SERVER_URL_CONF);
		
		Assert.assertNotNull(propertyConfig);

		Assert.assertNotEquals(propertyConfig, "");
	}
	
	protected WebTarget createTarget(String urlService) {
		
		Client client = ClientBuilder.newClient();
		WebTarget targetServer = client.target(propertyConfig);
		
		WebTarget target = targetServer.path(urlService);
		
		return target;
	}
	
	protected <T> void proccessPostTest(String url, T parameter) {
		Response response = createTarget(url).request(MediaType.APPLICATION_JSON).post(Entity.entity(parameter, MediaType.APPLICATION_JSON));
		
		Assert.assertEquals(Status.OK.getStatusCode(), response.getStatus());
	}
	
	protected <T, R> R proccessPostTest(String url, T parameter, Class<R> clazz) {
		Response response = createTarget(url).request(MediaType.APPLICATION_JSON).post(Entity.entity(parameter, MediaType.APPLICATION_JSON));
		
		Assert.assertEquals(Status.OK.getStatusCode(), response.getStatus());
		
		return response.readEntity(clazz);
	}
	
	protected <T> Response proccessGetTest(String url) {
		
		Response response = createTarget(url).request(MediaType.APPLICATION_JSON).get();
		
		Assert.assertEquals(Status.OK.getStatusCode(), response.getStatus());
		
		return response;
		
	}	
}

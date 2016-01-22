package booklibrary.restclient;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;

public class RestClient {
	private static RestClient restClientInstance = null;
	
	private RestClient() {
	}
	
	public static RestClient getInstance() {
		if (restClientInstance == null) {
			restClientInstance = new RestClient();
		}
		
		return restClientInstance;
	}

	public WebTarget getRestWebTarget() {
		ClientConfig config = new ClientConfig();

		Client client = ClientBuilder.newClient(config);
		
		return client.target(getBaseURI());
	}

	private URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost:9721/workshop/").build();
	}
}

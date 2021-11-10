package com.permanentwaves.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.permanentwaves.demo.daos.Product;
import com.permanentwaves.demo.daos.ProductResponse;

@Service
public class ProductSvc {

	private final RestTemplate restTemplate;
	private final HttpHeaders headers;
	private final HttpEntity<String> httpEntity;
	private String baseAstraUrl;
	
	@Autowired
	public ProductSvc(RestTemplateBuilder builder) {
        // build header
		headers = new HttpHeaders();
	    headers.set("x-cassandra-token", System.getenv("ASTRA_DB_APPLICATION_TOKEN"));
	    httpEntity = new HttpEntity<String>(headers);

	    // build Astra URL
	    StringBuilder urlBuilder = new StringBuilder("https://");
	    urlBuilder.append(System.getenv("ASTRA_DB_ID"));
	    urlBuilder.append("-");
	    urlBuilder.append(System.getenv("ASTRA_DB_REGION"));
	    urlBuilder.append(".apps.astra.datastax.com/api/rest/v2/keyspaces/");
	    urlBuilder.append(System.getenv("ASTRA_DB_KEYSPACE"));
	    baseAstraUrl = urlBuilder.toString();
	    
	    // initalize RestTemplate
		restTemplate = builder
				.rootUri(baseAstraUrl)
				.build();
	}
	
	public Product getProduct(String productId) {

		String astraUrl = "/product/" + productId;
	    
		ProductResponse resp = restTemplate.exchange(
			astraUrl,
    	    HttpMethod.GET,
    	    httpEntity,
    	    ProductResponse.class
        ).getBody();

		// only needed for debugging
		//System.out.println("url = " + baseAstraUrl + astraUrl);
		
		Product product = new Product();
		int count = resp.getCount();
		if (count > 0) {
			Product[] products = resp.getData();
			// should only be one item
			product = products[0];
		} 

		return product;
	}
}

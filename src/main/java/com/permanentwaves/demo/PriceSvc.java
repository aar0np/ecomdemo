package com.permanentwaves.demo;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpEntity;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class PriceSvc {

	private final RestTemplate restTemplate;
	private final HttpHeaders headers;
	private final HttpEntity<String> httpEntity;
	private String baseAstraUrl;
	
	@Autowired
	public PriceSvc(RestTemplateBuilder builder) {
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
	
	public Price getPrice(String productId, String storeId) {

		String astraUrl = "/price/" + productId + "/" + storeId;
	    
		PriceResponse resp = restTemplate.exchange(
			astraUrl,
    	    HttpMethod.GET,
    	    httpEntity,
    	    PriceResponse.class
        ).getBody();

		// only needed for debugging
		//System.out.println("url = " + baseAstraUrl + astraUrl);
		
		Price price = new Price();
		int count = resp.getCount();
		if (count > 0) {
			Price[] prices = resp.getData();
			// should only be one item
			price = prices[0];
		} 

		return price;
	}

}

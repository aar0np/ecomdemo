package com.permanentwaves.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import com.permanentwaves.demo.daos.Category;
import com.permanentwaves.demo.daos.CategoryResponse;

public class CategorySvc {

	private final RestTemplate restTemplate;
	private final HttpHeaders headers;
	private final HttpEntity<String> httpEntity;
	private String baseAstraUrl;
	
	@Autowired
	public CategorySvc(RestTemplateBuilder builder) {
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
	
	public Category[] getCategories(String parent) {
		
		String astraUrl = "/category/" + parent;
		
		CategoryResponse resp = restTemplate.exchange(
				astraUrl,
	    	    HttpMethod.GET,
	    	    httpEntity,
	    	    CategoryResponse.class
	        ).getBody();
		
		Category categories[] = resp.getData();
		
		return categories;
	}
	
	public Category getCategory(String parent, String name) {
		
		String astraUrl = "/category/" + parent + "/" + name;
		
		CategoryResponse resp = restTemplate.exchange(
				astraUrl,
	    	    HttpMethod.GET,
	    	    httpEntity,
	    	    CategoryResponse.class
	        ).getBody();
		
		Category category = new Category();
		int count = resp.getCount();
		if (count > 0) {
			Category categories[] = resp.getData();
			category = categories[0];
		}
		
		return category;
	}

}

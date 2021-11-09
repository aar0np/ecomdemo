package com.permanentwaves.demo;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.boot.web.client.RestTemplateBuilder;

@RestController
public class EcomController {
	private final PriceSvc priceSvc;
	
	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();
	
	public EcomController() {
		this.priceSvc = new PriceSvc(new RestTemplateBuilder());
	}
	
	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}

	@GetMapping("/price/{productid}")
	public Price price(@PathVariable(value = "productid") String productId) {
		String storeId = "web";
		return priceSvc.getPrice(productId,storeId);
	}
}

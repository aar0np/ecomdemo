package com.permanentwaves.demo;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.permanentwaves.demo.daos.Greeting;
import com.permanentwaves.demo.daos.Price;
import com.permanentwaves.demo.daos.Product;
import com.permanentwaves.demo.services.PriceSvc;
import com.permanentwaves.demo.services.ProductSvc;

import org.springframework.boot.web.client.RestTemplateBuilder;

@RestController
public class EcomController {
	private final PriceSvc priceSvc;
	private final ProductSvc productSvc;
	
	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();
	
	public EcomController() {
		this.priceSvc = new PriceSvc(new RestTemplateBuilder());
		this.productSvc = new ProductSvc(new RestTemplateBuilder());
	}
	
	// example from: https://spring.io/guides/gs/rest-service/
	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}

	@GetMapping("/price/{productid}")
	public Price price(@PathVariable(value = "productid") String productId) {
		// forcing a store_id of "web" if not provided
		String storeId = "web";
		return priceSvc.getPrice(productId,storeId);
	}
	
	@GetMapping("/price/{productid}/{storeid}")
	public Price price(@PathVariable(value = "productid") String productId, @PathVariable(value = "storeid") String storeId) {
		return priceSvc.getPrice(productId,storeId);
	}

	@GetMapping("/product/{productid}")
	public Product product(@PathVariable(value = "productid") String productId) {
		return productSvc.getProduct(productId);
	}
}

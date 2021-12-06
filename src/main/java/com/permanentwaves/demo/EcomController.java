package com.permanentwaves.demo;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.datastax.tutorials.service.category.Category;
import com.permanentwaves.demo.services.CategorySvc;

@RestController
public class EcomController {
	private final CategorySvc categorySvc;
	
	public EcomController() {
		this.categorySvc = new CategorySvc(new RestTemplateBuilder());
	}
	
	@GetMapping("/category/{parent}")
	public Category[] categories(@PathVariable(value = "parent") String parent) {
		return categorySvc.getCategories(parent);
	}

	@GetMapping("/category/{parent}/{category}")
	public Category categories(
	        @PathVariable(value = "parent") String parent, 
	        @PathVariable(value = "category") String category) {
		return categorySvc.getCategory(parent,category);
	}
}

package com.datastax.tutorials.service.category;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Category {
    private String name;
    private UUID id;
    private String image;
    private String parent;
    private List<String> children;
    private List<String> products;   
    
    public String getName() {
    	return this.name;
	}

    public void setName(String _name) {
		this.name = _name;
	}

    public UUID getId() {
    	return this.id;
	}

    public void setId(UUID _id) {
		this.id = _id;
	}

    public String getImage() {
		return this.image;
	}

    public void setImage(String _image) {
		this.image = _image;
	}

    public String getParent() {
		return this.parent;
	}

    public void setParent(String _parent) {
		this.parent = _parent;
	}

    public List<String> getChildren() {
		return this.children;
	}
	
    public void setChildren(List<String> _children) {
		this.children = _children;
	}
	
    public List<String> getProducts() {
		return this.products;
	}
	
    public void setProducts(List<String> _products) {
		this.products = _products;
	}
	
}

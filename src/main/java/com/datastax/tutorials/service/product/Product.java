package com.permanentwaves.demo.daos;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {

	@JsonProperty("product_id")
	private String productId;
	@JsonProperty("product_group")
	private String productGroup;
	private String name;
	private String brand;
	@JsonProperty("model_number")
	private String modelNumber;
	@JsonProperty("short_desc")
	private String shortDesc;
	@JsonProperty("long_desc")
	private String longDesc;
	private List<Map<String,String>> specifications;
	@JsonProperty("linked_documents")
	private List<Map<String,String>> linkedDocuments;
	private Set<String> images;
	
	public String getProductId() {
		return this.productId;
	}
	
	public void setProductId(String _productId) {
		this.productId = _productId;
	}

	public String getProductGroup() {
		return this.productGroup;
	}
	
	public void setProductGroup(String _productGroup) {
		this.productGroup = _productGroup;
	}

	public String getName() {
		return this.name;
	}
	
	public void setName(String _name) {
		this.name = _name;
	}
	
	public String getBrand() {
		return this.brand;
	}
	
	public void setBrand(String _brand) {
		this.brand = _brand;
	}
	
	public String getModelNumber() {
		return this.modelNumber;
	}
	
	public void setModelNumber(String _modelNumber) {
		this.modelNumber = _modelNumber;
	}
	
	public String getShortDesc() {
		return this.shortDesc;
	}
	
	public void setShortDesc(String _shortDesc) {
		this.shortDesc = _shortDesc;
	}
	
	public String getLongDesc() {
		return this.longDesc;
	}
	
	public void setLongDesc(String _longDesc) {
		this.longDesc = _longDesc;
	}

	// Astra DB returns Map<x,y> as a List<Map<x,y>>
	public List<Map<String,String>> getSpecifications() {
		return this.specifications;
	}
	
	// Astra DB returns Maps<x,y> as a List<Map<x,y>>
	public void setSpecifications(List<Map<String,String>> _specifications) {
		this.specifications = _specifications;
	}
	
	// Astra DB returns Maps<x,y> as a List<Map<x,y>>
	public List<Map<String,String>> getLinkedDocuments() {
		return this.linkedDocuments;
	}
	
	// Astra DB returns Maps<x,y> as a List<Map<x,y>>
	public void setLinkedDocuments(List<Map<String,String>> _linkedDocuments) {
		this.linkedDocuments = _linkedDocuments;
	}
	
	public Set<String> getImages() {
		return this.images;
	}
	
	public void setImages(Set<String> _images) {
		this.images = _images;
	}
}

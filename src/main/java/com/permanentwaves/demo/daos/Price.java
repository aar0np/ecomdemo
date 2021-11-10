package com.permanentwaves.demo.daos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Price {
	
	@JsonProperty("product_id")
	private String productId;
	@JsonProperty("store_id")
	private String storeId;
	private BigDecimal value;
	
	public String getProductId() {
		return this.productId;
	}
	
	public void setProductId(String _productId) {
		this.productId = _productId;
	}
	
	public String getStoreId() {
		return this.storeId;
	}
	
	public void setStoreId(String _storeId) {
		this.storeId = _storeId;
	}
	
	public BigDecimal getValue() {
		return this.value;
	}
	
	public void setValue(BigDecimal _value) {
		this.value = _value;
	}
}

package com.permanentwaves.demo.daos;

public class ProductResponse {
    private int count;
    private Product[] data;
    
    public int getCount() {
    	return this.count;
    }
    
    public void setCount(int _count) {
    	this.count = _count;
    }
    
    public Product[] getData() {
    	return this.data;
    }
    
    public void setData(Product[] _data) {
    	this.data = _data;
    }
}

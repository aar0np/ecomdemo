package com.permanentwaves.demo;

public class PriceResponse {
    private int count;
    private Price[] data;
    
    public int getCount() {
    	return this.count;
    }
    
    public void setCount(int _count) {
    	this.count = _count;
    }
    
    public Price[] getData() {
    	return this.data;
    }
    
    public void setData(Price[] _data) {
    	this.data = _data;
    }
}

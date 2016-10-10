package com.ek.test.framework.data;

public enum WebElementAttributes {
	
	VALUE("value"), 
	CLASS("class"),
	TYPE("type");
	
	private final String attribute;
	
	private WebElementAttributes(String attribute){
		this.attribute = attribute;
	}
	
	public String getAttributeValue(){
		return this.attribute;
	}
	

}

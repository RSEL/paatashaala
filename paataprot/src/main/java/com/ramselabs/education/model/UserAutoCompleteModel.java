package com.ramselabs.education.model;

public class UserAutoCompleteModel {
	private String name;
	private String address;
    private String pic;
    public UserAutoCompleteModel(String name,String address,String pic){
    	this.name=name;
    	this.address=address;
    	this.pic=pic;
    }
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
    @Override
    public String toString(){
    	return getName();
    }
}

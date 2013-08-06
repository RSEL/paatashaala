package com.ramselabs.education.model;

public class Person {
	private String personPic;
	private String description;
	public Person(String personPic,String description){
		this.personPic=personPic;
		this.description=description;
	}
	public String getPersonPic() {
		return personPic;
	}
	public void setPersonPic(String personPic) {
		this.personPic = personPic;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}

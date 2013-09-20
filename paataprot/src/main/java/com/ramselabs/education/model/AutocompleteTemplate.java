package com.ramselabs.education.model;

public class AutocompleteTemplate {
	private String displayName;
	private String imagePath;
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	@Override
	public String toString(){
		return displayName;
	}

}

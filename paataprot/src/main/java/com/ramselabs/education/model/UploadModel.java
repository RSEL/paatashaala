package com.ramselabs.education.model;

public class UploadModel {
	private String displayName;
	private String groupName;
    private String userName;
    private String password;
    private String grade;
	private String area;
	private String moderateOptions;
	private String groupDescription;

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getModerateOptions() {
		return moderateOptions;
	}

	public void setModerateOptions(String moderateOptions) {
		this.moderateOptions = moderateOptions;
	}

	public String getGroupDescription() {
		return groupDescription;
	}

	public void setGroupDescription(String groupDescription) {
		this.groupDescription = groupDescription;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
   @Override
   public String toString(){
	   return displayName;
   }
}

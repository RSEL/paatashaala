package com.ramselabs.education.model;

public class GroupUserUploadModel extends UploadModel{
	private String groupName;
	private String userName;
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
    @Override
    public String toString(){
    	return groupName;
    }
}

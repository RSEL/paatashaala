package com.ramselabs.education.model;

import com.ramselabs.education.entity.Group;

public class GroupUploadModel extends UploadModel{
	private String displayName;
	private String grade;
	private String area;
	private String moderateOptions;
	private String groupDescription;
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
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
	public static Group getGroupEntity(GroupUploadModel grpUpModel){
		Group group=new Group();
		group.setDisplayName(grpUpModel.getDisplayName());
		group.setGrade(grpUpModel.getGrade());
		group.setArea(grpUpModel.getArea());
		group.setModerateOptions(grpUpModel.getModerateOptions());
		group.setGroupDescription(grpUpModel.getGroupDescription());
		return group;
	}
	@Override
	public String toString(){
		return displayName;
	}

}

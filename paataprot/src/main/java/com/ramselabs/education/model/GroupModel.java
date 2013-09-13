package com.ramselabs.education.model;

import java.util.List;

public class GroupModel{
	private String groupName;
	private String grade;
	private String area;
	private List<String> groupModerationOption;
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
	public List<String> getGroupModerationOption() {
		return groupModerationOption;
	}
	public void setGroupModerationOption(List<String> groupModerationOption) {
		this.groupModerationOption = groupModerationOption;
	}
	public String getGroupDescription() {
		return groupDescription;
	}
	public void setGroupDescription(String groupDescription) {
		this.groupDescription = groupDescription;
	}

}

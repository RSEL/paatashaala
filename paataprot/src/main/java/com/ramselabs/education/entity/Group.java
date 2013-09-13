package com.ramselabs.education.entity;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="groups")
public class Group {
	@Id @GeneratedValue
	@Column(name="id")
	private String groupId;
	
	@Column(name="group_name")
	private String groupName;
	
	@Column(name="grade")
	private String grade;
	
	@Column(name="area")
	private String area;
	
	@Column(name="moderation_options")
	private String moderateOptions;
	
	@Column(name="group_desc")
	private String groupDescription;
	
	@ManyToMany
	private Collection<UserProfile> groupUsers=new ArrayList<UserProfile>();
	
	@ManyToMany
	private Collection<Post> groupPosts=new ArrayList<Post>();
	
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
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
	public Collection<UserProfile> getGroupUsers() {
		return groupUsers;
	}
	public void setGroupUsers(Collection<UserProfile> groupUsers) {
		this.groupUsers = groupUsers;
	}
	public Collection<Post> getGroupPosts() {
		return groupPosts;
	}
	public void setGroupPosts(Collection<Post> groupPosts) {
		this.groupPosts = groupPosts;
	}
	
}

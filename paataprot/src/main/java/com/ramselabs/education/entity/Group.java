package com.ramselabs.education.entity;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.ramselabs.education.model.AutocompleteTemplate;

@Entity
@Table(name="groups")
public class Group extends AutocompleteTemplate{
	@Id @GeneratedValue
	@Column(name="id")
	private int groupId;
	
	@Column(name="group_name",unique=true)
	private String displayName;
	
	@Column(name="grade")
	private String grade;
	
	@Column(name="area")
	private String area;
	
	@Column(name="moderation_options")
	private String moderateOptions;
	
	@Column(name="group_desc")
	private String groupDescription;
	
	@Column(name="image_path")
	private String imagePath;
	
	@ManyToMany
	@JoinTable(name="group_users",joinColumns=@JoinColumn(name="group_id"),inverseJoinColumns=@JoinColumn(name="user_id"))
	private Collection<UserProfile> groupUsers=new ArrayList<UserProfile>();
	
	@ManyToMany(mappedBy="groups")
	private Collection<Post> groupPosts=new ArrayList<Post>();
	
	@OneToMany(mappedBy="shareGroup")
	private Collection<PostShare> groupShares=new ArrayList<PostShare>();
	
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	
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
	public Collection<PostShare> getGroupShares() {
		return groupShares;
	}
	public void setGroupShares(Collection<PostShare> groupShares) {
		this.groupShares = groupShares;
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

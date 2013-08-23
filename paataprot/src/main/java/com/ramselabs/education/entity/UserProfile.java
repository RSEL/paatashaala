package com.ramselabs.education.entity;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="userprofiles")
public class UserProfile{
	@Id @GeneratedValue
	@Column(name="id")
    private int userId;
	@Column(name="user_name")
	private String username;
	@Column(name="password")
	private String password;
	@Column(name="display_name")
	private String displayName;
	@Column(name="image_path")
	private String imagePath;
	@OneToMany(mappedBy="postUser")
	private Collection<Post> post=new ArrayList<Post>();
	@OneToMany(mappedBy="postShareUser")
	private Collection<PostShare> userPostShare=new ArrayList<PostShare>();
	
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	public Collection<Post> getPost() {
		return post;
	}
	public void setPost(Collection<Post> post) {
		this.post = post;
	}
	public Collection<PostShare> getUserPostShare() {
		return userPostShare;
	}
	public void setUserPostShare(Collection<PostShare> userPostShare) {
		this.userPostShare = userPostShare;
	}
	@Override
	public String toString(){
		return displayName;
	}
}

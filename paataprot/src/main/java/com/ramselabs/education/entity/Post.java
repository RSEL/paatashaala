package com.ramselabs.education.entity;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name="Post_Table")
public class Post{
    
	@Id @GeneratedValue
	private int postId;
	@Type(type="text")
	private String description;
	@ManyToOne
	@JoinColumn(name="user_Id")
	UserProfile postUser;
	@OneToMany(mappedBy="post")
	private Collection<PostShare> post_share=new ArrayList<PostShare>();
	private int posterId;
	public Collection<PostShare> getPost_share() {
		return post_share;
	}
	public void setPost_share(Collection<PostShare> post_share) {
		this.post_share = post_share;
	}
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public UserProfile getPostUser() {
		return postUser;
	}
	public void setPostUser(UserProfile postUser) {
		this.postUser = postUser;
	}
	public int getPosterId() {
		return posterId;
	}
	public void setPosterId(int posterId) {
		this.posterId = posterId;
	}
	
	
	
}

package com.ramselabs.education.entity;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name="posts")
public class Post{
    
	@Id @GeneratedValue
	@Column(name="id")
	private int postId;
	
	@Type(type="text")
	@Column(name="description")
	private String description;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	UserProfile postUser;
	
	@OneToMany(mappedBy="post")
	private Collection<PostShare> postShare=new ArrayList<PostShare>();
	
	@OneToOne(mappedBy="approvalPost")
	private MessageApproval postApproval;
	
	@Column(name="poster_id")
	private int posterId;
	
	@Column(name="message_type")
	private String messageType;
	

	@ManyToMany(mappedBy="groupPosts")
	private Collection<Group> groups=new ArrayList<Group>();
	
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	public Collection<PostShare> getPostShare() {
		return postShare;
	}
	public void setPostShare(Collection<PostShare> post_share) {
		this.postShare = post_share;
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
	public MessageApproval getPostApproval() {
		return postApproval;
	}
	public void setPostApproval(MessageApproval postApproval) {
		this.postApproval = postApproval;
	}
	public Collection<Group> getGroups() {
		return groups;
	}
	public void setGroups(Collection<Group> groups) {
		this.groups = groups;
	}
	
}

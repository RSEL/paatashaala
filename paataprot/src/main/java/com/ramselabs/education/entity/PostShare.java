package com.ramselabs.education.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class PostShare {
	
	@Id @GeneratedValue
	private int idPostShare;
	@ManyToOne
	@JoinColumn(name="post_Id")
	private Post post;
	@ManyToOne
	@JoinColumn(name="user_Id")
	private UserProfile postShareUser;
    private String userType;
    @Temporal(TemporalType.TIMESTAMP)
    private Date postDate;
	
	public int getIdPostShare() {
		return idPostShare;
	}
	public void setIdPostShare(int idPostShare) {
		this.idPostShare = idPostShare;
	}
	
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	
	public UserProfile getPostShareUser() {
		return postShareUser;
	}
	public void setPostShareUser(UserProfile postShareUser) {
		this.postShareUser = postShareUser;
	}
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
	}
	public Date getPostDate() {
		return postDate;
	}
	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}
	
}

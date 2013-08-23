package com.ramselabs.education.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="postshares")
public class PostShare {
	
	@Id @GeneratedValue
	@Column(name="id")
	private int idPostShare;
	@ManyToOne
	@JoinColumn(name="post_id")
	private Post post;
	@ManyToOne
	@JoinColumn(name="user_id")
	private UserProfile postShareUser;
	@Column(name="user_type")
    private String userType;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="post_date")
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

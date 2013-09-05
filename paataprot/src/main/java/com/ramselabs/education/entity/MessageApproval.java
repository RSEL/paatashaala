package com.ramselabs.education.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="MessageApprovals")
public class MessageApproval {
	@Id @GeneratedValue
	@Column(name="id")
	private int approvalId;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private UserProfile userApproval;
	
	@Column(name="message_status")
	private String status;
	
	@OneToOne
	@JoinColumn(name="post_id")
	private Post approvalPost;
	
	@Column(name="reject_reason")
	private String rejectReason;
	
	public int getApprovalId() {
		return approvalId;
	}
	public void setApprovalId(int approvalId) {
		this.approvalId = approvalId;
	}
	
	public UserProfile getUserApproval() {
		return userApproval;
	}
	public void setUserApproval(UserProfile userApproval) {
		this.userApproval = userApproval;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public Post getApprovalPost() {
		return approvalPost;
	}
	public void setApprovalPost(Post approvalPost) {
		this.approvalPost = approvalPost;
	}
	
	public String getRejectReason() {
		return rejectReason;
	}
	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}
	@Override
	public String toString(){
		return "userId:"+getUserApproval().getUserId()+",status:"+status;
	}

}

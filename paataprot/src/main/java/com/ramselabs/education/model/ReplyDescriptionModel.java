package com.ramselabs.education.model;

import java.util.Comparator;
import java.util.Date;

public class ReplyDescriptionModel implements Comparator<ReplyDescriptionModel>{
	private int postId;
	private String postDescription;
	private Date sentDate;
	private String imagePath;
	private String posterName;
	
	public String getPosterName() {
		return posterName;
	}
	public void setPosterName(String posterName) {
		this.posterName = posterName;
	}
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public String getPostDescription() {
		return postDescription;
	}
	public void setPostDescription(String postDescription) {
		this.postDescription = postDescription;
	}
	public Date getSentDate() {
		return sentDate;
	}
	public void setSentDate(Date sentDate) {
		this.sentDate = sentDate;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
    @Override
    public String toString(){
    	return "postId:"+postId+"postDesc:"+postDescription;
    }
	@Override
	public int compare(ReplyDescriptionModel o1, ReplyDescriptionModel o2) {
		return o2.getSentDate().compareTo(o1.getSentDate());
	}
}

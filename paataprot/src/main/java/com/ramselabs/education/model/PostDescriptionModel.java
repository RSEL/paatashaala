package com.ramselabs.education.model;

import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.ramselabs.education.entity.SharedFile;

public class PostDescriptionModel implements Comparator<PostDescriptionModel>{
	private String personName;
	private String postDescription;
	private String userType;
	private Date dateOfPosting;
	private String userImage;
	private String messageType;
	private String shareToName;
	private int approvalId;
	private String rejectStatus;
	private String rejectReason;
	private String shareToImage;
	private String displayType;
	private List<SharedFile> listOfSharedFiles=new ArrayList<SharedFile>();
	
	public List<SharedFile> getListOfSharedFiles() {
		return listOfSharedFiles;
	}
	public void setListOfSharedFiles(List<SharedFile> listOfSharedFiles) {
		this.listOfSharedFiles = listOfSharedFiles;
	}
	public String getDisplayType() {
		return displayType;
	}
	public void setDisplayType(String displayType) {
		this.displayType = displayType;
	}
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	public String getUserImage() {
		return userImage;
	}
	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public Date getDateOfPosting() {
		return dateOfPosting;
	}
	public void setDateOfPosting(Date dateOfPosting) {
		this.dateOfPosting = dateOfPosting;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public String getPostDescription() {
		return postDescription;
	}
	public void setPostDescription(String postDescription) {
		this.postDescription = postDescription;
	}
	
	public String getShareToName() {
		return shareToName;
	}
	public void setShareToName(String shareToName) {
		this.shareToName = shareToName;
	}
	
	public int getApprovalId() {
		return approvalId;
	}
	public void setApprovalId(int approvalId) {
		this.approvalId = approvalId;
	}
	public String getRejectStatus() {
		return rejectStatus;
	}
	public void setRejectStatus(String rejectStatus) {
		this.rejectStatus = rejectStatus;
	}
	public String getRejectReason() {
		return rejectReason;
	}
	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}
	
	public String getShareToImage() {
		return shareToImage;
	}
	public void setShareToImage(String shareToImage) {
		this.shareToImage = shareToImage;
	}
	@Override
	public int compare(PostDescriptionModel o1, PostDescriptionModel o2) {
		return o2.getDateOfPosting().compareTo(o1.getDateOfPosting());
	}
	@Override
	public String toString(){
		DateFormat format=new SimpleDateFormat("dd/MM");
		String fomatedDate=format.format(dateOfPosting);
		if(rejectReason==null)
			rejectReason="";
		Object [] messages={personName,fomatedDate,
				           shareToName,rejectStatus,rejectReason
				           };
		String message=MessageFormat.format("{0} on {1} shared to: {2} status: {3} {4}",messages);
		return message;
	}
}

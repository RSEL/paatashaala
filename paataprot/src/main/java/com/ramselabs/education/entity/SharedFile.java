package com.ramselabs.education.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="sharedfiles")
public class SharedFile {
	
	@Id @GeneratedValue
	@Column(name="id")
	private int sharedFileId;
	
	@Column(name="meta_data")
	private String metaData;
	
	@Column(name="link")
	private String link;
	
	@ManyToOne
	@JoinColumn(name="post_id")
	private Post shredFilePost;

	@Transient
	private String fileName;
	@Transient
	private String extention;
	@Transient
	private String iconPath;


	public int getSharedFileId() {
		return sharedFileId;
	}

	public void setSharedFileId(int sharedFileId) {
		this.sharedFileId = sharedFileId;
	}

	public String getMetaData() {
		String [] strs=metaData.split(";");
		fileName=strs[0];
		extention=strs[1].toUpperCase();
		iconPath=strs[2];
		return metaData;
	}

	public void setMetaData(String metaData) {
		this.metaData = metaData;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Post getShredFilePost() {
		return shredFilePost;
	}

	public void setShredFilePost(Post shredFilePost) {
		this.shredFilePost = shredFilePost;
	}
	

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getExtention() {
		return extention;
	}

	public void setExtention(String extention) {
		this.extention = extention;
	}

	public String getIconPath() {
		return iconPath;
	}

	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
	}

	@Override
	public String toString(){
		return link;
	}
}

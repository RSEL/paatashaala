package com.ramselabs.education.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Share implements Serializable{

	private static final long serialVersionUID = -1040795626367116578L;
	@Id @GeneratedValue
	private int shareId;
	private String name;
	private int userId;
	public int getShareId() {
		return shareId;
	}
	public void setShareId(int shareId) {
		this.shareId = shareId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	@Override
	public String toString(){
		return getName();
	}
}

package com.ramselabs.education.entity;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.ramselabs.education.enums.RoleEnum;

@Entity
@Table(name="roles")
public class Role {
	@Id @GeneratedValue
	@Column(name="id")
	private int roleId;
	
	@Column(name="role_name")
	private String roleName;

	@ManyToMany
	@JoinTable(name="user_role",joinColumns=@JoinColumn(name="role_id"),inverseJoinColumns=@JoinColumn(name="user_id"))
	private Collection<UserProfile> roleUsers=new ArrayList<UserProfile>();
	@Transient
	private RoleEnum roleEnum;
	
	public RoleEnum getRoleEnum() {
		return roleEnum;
	}

	public void setRoleEnum(RoleEnum roleEnum) {
		this.roleEnum = roleEnum;
	}

	public Collection<UserProfile> getRoleUsers() {
		return roleUsers;
	}

	public void setRoleUsers(Collection<UserProfile> roleUsers) {
		this.roleUsers = roleUsers;
	}

	public int getRoleId() {
		return roleId;
	}
    
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	@SuppressWarnings("static-access")
	@Override
	public boolean equals(Object obj){
		RoleEnum roleEnum=(RoleEnum)obj;
		boolean status=false;
		for(RoleEnum list : roleEnum.values()){
		     if(this.roleName.equalsIgnoreCase(list.toString())){
		    	 status=true;
		     }
		}
		return status;
	}
	@Override
	public String toString(){
		return roleName;
	}
}

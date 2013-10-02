package com.ramselabs.education.serviceImpl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.ramselabs.education.dao.service.GroupDAO;
import com.ramselabs.education.entity.Group;
import com.ramselabs.education.entity.UserProfile;
import com.ramselabs.education.managedbean.ManagedLoginBean;
import com.ramselabs.education.model.AutocompleteTemplate;
import com.ramselabs.education.model.GroupUserUploadModel;
import com.ramselabs.education.model.PostDescriptionModel;
import com.ramselabs.education.service.GroupService;

@Named
@Scope("session")
public  class GroupServiceImpl implements GroupService {
	
	@Inject
	private GroupDAO groupDao;  
	public void setGroupDao(GroupDAO groupDao) {
		this.groupDao = groupDao;
	}

	@Override
	public int insertGroups(Group group) {
		return groupDao.createGroup(group);
	}

	@Override
	public UserProfile getUserProfile(ManagedLoginBean login) {
		
		return groupDao.getUserProfile(login);
	}

	@Override
	public Group getGroup(int groupId) {
		return groupDao.getGroupEntity(groupId);
	}

	@Override
	public boolean groupNameVerifyResponse(String value) {
		return groupDao.groupNameVerifyResponse(value);
	}

	@Override
	public List<Group> getAllGrpsForCurrentUser(int userId) {
		return groupDao.getAllGroupsForCurrentUser(userId);
	}

	@Override
	public List<AutocompleteTemplate> getAllGroupForAutocomplete() {
		
		return groupDao.getAllGroupForAutocomplete();
	}

	@Override
	public int updateGroupImage(int groupId,String imagePath) {
		return groupDao.updateImage(groupId,imagePath);
	}

	@Override
	public List<PostDescriptionModel> getAllPostsForGroup(int groupId) {
		return groupDao.getAllMessagesForGroup(groupId);
	}

	@Override
	public int insertGroupUser(GroupUserUploadModel gruopUser) {
		return groupDao.createGroupUser(gruopUser);
	}

	
}

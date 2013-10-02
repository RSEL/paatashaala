package com.ramselabs.education.service;

import java.util.List;

import com.ramselabs.education.entity.Group;
import com.ramselabs.education.entity.UserProfile;
import com.ramselabs.education.managedbean.ManagedLoginBean;
import com.ramselabs.education.model.AutocompleteTemplate;
import com.ramselabs.education.model.GroupUserUploadModel;
import com.ramselabs.education.model.PostDescriptionModel;

public interface GroupService {
	public int insertGroups(Group group);
    public UserProfile getUserProfile(ManagedLoginBean login);
    public Group getGroup(int groupId);
    public boolean groupNameVerifyResponse(String value);
    public List<Group> getAllGrpsForCurrentUser(int userId);
    public List<AutocompleteTemplate> getAllGroupForAutocomplete();
    public int updateGroupImage(int groupId,String imagePath);
    public List<PostDescriptionModel> getAllPostsForGroup(int groupId);
    public int insertGroupUser(GroupUserUploadModel gruopUser);
}

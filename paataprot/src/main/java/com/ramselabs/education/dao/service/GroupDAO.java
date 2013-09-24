package com.ramselabs.education.dao.service;
import java.util.List;

import com.ramselabs.education.entity.Group;
import com.ramselabs.education.entity.UserProfile;
import com.ramselabs.education.managedbean.ManagedLoginBean;
import com.ramselabs.education.model.AutocompleteTemplate;
import com.ramselabs.education.model.PostDescriptionModel;

public interface GroupDAO {
	public int createGroup(Group group);
    public UserProfile getUserProfile(ManagedLoginBean login);
    public Group getGroupEntity(int groupId);
    public boolean groupNameVerifyResponse(String value);
    public List<Group> getAllGroupsForCurrentUser(int userId);
    public List<AutocompleteTemplate> getAllGroupForAutocomplete();
    public int updateImage(int groupId,String imagePath);
    public List<PostDescriptionModel> getAllMessagesForGroup(int groupId);
}

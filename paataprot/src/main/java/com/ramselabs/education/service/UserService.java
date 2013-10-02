package com.ramselabs.education.service;

import java.util.List;
import java.util.Map;

import com.ramselabs.education.entity.UserProfile;
import com.ramselabs.education.model.AutocompleteTemplate;
import com.ramselabs.education.model.UploadModel;


public interface UserService {
	public String doLogin(UserProfile user);
	public List<AutocompleteTemplate> getAutocompleteUserList(UserProfile user);
	public UserProfile getPersistentUser(UserProfile user);
	public int getUserId(UserProfile user);
	public UserProfile getUserProfile(UserProfile user);
	public int updateUserImage(UserProfile user);
    public List<UploadModel> getExcelModel(String sheetName);
    public int insertUser(UserProfile user);
    public Map<String,String> getSheetMaps();
}

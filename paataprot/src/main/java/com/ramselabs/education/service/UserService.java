package com.ramselabs.education.service;

import java.util.List;

import com.ramselabs.education.entity.Post;
import com.ramselabs.education.entity.Post_Share;
import com.ramselabs.education.entity.Share;
import com.ramselabs.education.entity.User;

public interface UserService {
	public boolean doLogin(User user);
	public List<Share> getAutocompleteUserList(User user);
	public int inserPost(Post post);
	public int isertPost_Share(Post_Share post_share);

}

package com.ramselabs.education.serviceImpl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.ramselabs.education.dao.service.AlertDAO;
import com.ramselabs.education.entity.MessageApproval;
import com.ramselabs.education.entity.Post;
import com.ramselabs.education.entity.PostShare;
import com.ramselabs.education.entity.UserProfile;
import com.ramselabs.education.model.PostDescriptionModel;
import com.ramselabs.education.service.AlertService;

@Named
@Scope("session")
public class AlertServiceImpl implements AlertService {

	@Inject
	private AlertDAO alertDAO;
	
	public void setAlertDAO(AlertDAO alertDAO) {
		this.alertDAO = alertDAO;
	}

	@Override
	public List<PostDescriptionModel> getAlerts(UserProfile user) {
		
		return alertDAO.getAllAlert(user);
	}

	@Override
	public int insertAlert(Post post, PostShare pShare ,MessageApproval approval) {
		return alertDAO.insertAlerts(post, pShare,approval);
	}

	@Override
	public int getUserId(UserProfile user) {
		return alertDAO.getUserId(user);
	}

	
}

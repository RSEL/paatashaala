package com.ramselabs.education.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

import org.primefaces.event.FlowEvent;
import org.springframework.context.annotation.Scope;

import com.ramselabs.education.model.GroupModel;

@Named
@Scope
public class GroupWizardController implements Serializable{

	private static final long serialVersionUID = -1709808322997537939L;
	private Map<String,String> grades;
	private Map<String,String> areas;
	private Map<String,String> moderateGroupOption;
	private GroupModel group=new GroupModel();  
    private static Logger logger = Logger.getLogger(GroupWizardController.class.getName());  
        
    public GroupWizardController() {
    	grades=new HashMap<String, String>();
    	grades.put("None", "no");
    	grades.put("Prekindergarten", "pkg");
    	grades.put("Kindergarten", "kg");
    	grades.put("1st", "1");
    	grades.put("2nd", "2");
    	grades.put("3rd", "3");
    	grades.put("4th", "4");
    	grades.put("5th", "5");
    	grades.put("6th", "6");
    	grades.put("7th", "7");
    	grades.put("8th", "8");
    	grades.put("9th", "9");
    	grades.put("10th", "10");
    	grades.put("11th", "11");
    	grades.put("12th", "12");
    	grades.put("Higher Education", "he");
    	
    	areas=new HashMap<String, String>();
    	areas.put("All","all");
    	areas.put("Computer Technology","compTech");
    	areas.put("Mathemetics","mth");
    	areas.put("Science","science");
    	areas.put("Art","art");
    	areas.put("World Language","wl");
    	areas.put("Professional Developement","pd");
    	areas.put("Social Studies","ss");
    	areas.put("Health/P.E.","hl");
    	
    	moderateGroupOption=new HashMap<String, String>();
    	moderateGroupOption.put("Default all new members to read-only", "readOnly");
    	moderateGroupOption.put("Moderate all Posts and Replies","moderate");
	}

	public GroupModel getGroup() {
		return group;
	}

	public void setGroup(GroupModel group) {
		this.group = group;
	}

	public Map<String, String> getGrades() {
		return grades;
	}

	public Map<String, String> getAreas() {
		return areas;
	}

	public Map<String, String> getModerateGroupOption() {
		return moderateGroupOption;
	}

	public void save(ActionEvent actionEvent) {  
        //Persist user  
          
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Group is Created" ,null);  
        FacesContext.getCurrentInstance().addMessage("groupStatus", msg);  
    }  
      
    public String onFlowProcess(FlowEvent event) {  
        logger.info("Current wizard step:" + event.getOldStep());  
        logger.info("Next step:" + event.getNewStep());  
        
            return event.getNewStep();  
          
    }  
    
    @Override
    public String toString(){
    	return "name:"+group.getGroupName()+",grade:"+group.getGrade()+",area:"+group.getArea()+",moderate list:"+group.getGroupModerationOption()+",description:"+group.getGroupDescription();
    }
}

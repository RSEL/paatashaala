package com.ramselabs.education.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.ramselabs.education.model.UploadModel;
import com.ramselabs.education.service.UserService;

@Named
@Scope("session")
public class DialogueDataFeedController implements Serializable,ActionListener{

	private static final long serialVersionUID = 7699306471623587837L;
	Map<String, String> sheetNames;
	private String sheetName;
	private String tableDisplay="none";
	private String tableGroup="none";
	private String tableGroupUser="none";
	private String commandButtDispl="none";
	@Inject
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	private List<UploadModel> listModel;
	
	public List<UploadModel> getListModel() {
		return listModel;
	}

	public void setListModel(List<UploadModel> listModel) {
		this.listModel = listModel;
	}
	
    
	public Map<String, String> getSheetNames() {
		return sheetNames;
	}

	public void setSheetNames(Map<String, String> sheetNames) {
		this.sheetNames = sheetNames;
	}

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}
    
	public String getTableDisplay() {
		return tableDisplay;
	}

	public void setTableDisplay(String tableDisplay) {
		this.tableDisplay = tableDisplay;
	}
    
	public String getTableGroup() {
		return tableGroup;
	}

	public void setTableGroup(String tableGroup) {
		this.tableGroup = tableGroup;
	}

	public String getCommandButtDispl() {
		return commandButtDispl;
	}

	public void setCommandButtDispl(String commandButtDispl) {
		this.commandButtDispl = commandButtDispl;
	}

	public String getTableGroupUser() {
		return tableGroupUser;
	}

	public void setTableGroupUser(String tableGroupUser) {
		this.tableGroupUser = tableGroupUser;
	}

	@PostConstruct
	public void getDataInObjectFromExcel(){
		//listModel=userService.getExcelModel();
		sheetNames=userService.getSheetMaps();
		System.out.println(listModel);
	}

	@Override
	public void processAction(ActionEvent arg0) throws AbortProcessingException {
		
		sheetNames=userService.getSheetMaps();
	}
	public void getExcelData(){
		if(sheetName.equalsIgnoreCase("Users")){
			 tableGroup="none";
			 tableGroupUser="none";
		     tableDisplay="block";
		}
		else if(sheetName.equalsIgnoreCase("Groups")){
			  tableDisplay="none";
			  tableGroupUser="none";
			  tableGroup="block";
		}
		else if(sheetName.equalsIgnoreCase("GroupUsers")){
			  tableDisplay="none";
			  tableGroup="none";
			  tableGroupUser="block";
		}
		commandButtDispl="block";
		listModel=userService.getExcelModel(sheetName);
	}

}

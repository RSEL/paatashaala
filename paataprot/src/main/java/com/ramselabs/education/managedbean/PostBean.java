package com.ramselabs.education.managedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.UploadedFile;
import org.springframework.context.annotation.Scope;

import com.ramselabs.education.entity.Post;
import com.ramselabs.education.entity.SharedFile;

@Named
@Scope("session")
public class PostBean implements Serializable{

	private static final long serialVersionUID = 1592538150710683174L;
    private String description;
    private List<UploadedFile> uplFiles=new ArrayList<UploadedFile>();
    
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public List<UploadedFile> getUplFiles() {
		return uplFiles;
	}
	public void setUplFiles(List<UploadedFile> uplFiles) {
		this.uplFiles = uplFiles;
	}
	public static Post mapToPost(PostBean postBean){
		Post post=new Post();
		post.setDescription(postBean.getDescription());
		return post;
	}
	public static Collection<SharedFile> getAllSharedFiles(PostBean postBean){
		List<SharedFile> listShareFiles=new ArrayList<SharedFile>();
		for(UploadedFile file:postBean.getUplFiles()){
			SharedFile sharedFile=new SharedFile();
			int i=file.getFileName().lastIndexOf('.');
			String extention=null,name=null,iconPath=null;
			name=file.getFileName();
			if(i>0){
			    extention = file.getFileName().substring(i+1);
			    if(extention.equalsIgnoreCase("txt"))
			    	iconPath="/resources/img/file-icon/text-icon.png";
			    if(extention.equalsIgnoreCase("doc") || extention.equalsIgnoreCase("docx"))
			    	iconPath="/resources/img/file-icon/docx-icon.png";
			    if(extention.equalsIgnoreCase("pdf"))
			    	iconPath="/resources/img/file-icon/pdf-icon.png";
			    if(extention.equalsIgnoreCase("xlsx") || extention.equalsIgnoreCase("xls"))
			    	iconPath="/resources/img/file-icon/excel-icon.png";
			    if(extention.equalsIgnoreCase("jpg") || extention.equalsIgnoreCase("jpeg") || extention.equalsIgnoreCase("png") || extention.equalsIgnoreCase("gif"))
			    	iconPath="/resources/img/file-icon/image-icon.png";
			}
			List<String> metaDataStr=new ArrayList<String>();
			metaDataStr.add(name);
			metaDataStr.add(extention);
			metaDataStr.add(iconPath);
			sharedFile.setLink("classes/sharedFiles/"+file.getFileName());
			sharedFile.setMetaData(StringUtils.join(metaDataStr, ";"));
			listShareFiles.add(sharedFile);
		}
		return listShareFiles;
	}
}

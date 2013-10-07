package com.ramselabs.education.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletOutputStream;

import org.springframework.context.annotation.Scope;

import com.ramselabs.education.model.SharedFileModel;
import com.ramselabs.education.service.PostService;

@Named
@Scope("session")
public class ContentRenderController implements Serializable {

	private static final long serialVersionUID = -7145407124462389612L;

	@Inject
	private PostService postService;

	public void setPostService(PostService postService) {
		this.postService = postService;
	}

	public void renderContent() throws IOException {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		byte[] b = new byte[(int) 8000000];
		Map<String, String> params = ec.getRequestParameterMap();
		String sharedFileId = params.get("sharedFileId"); 
		int sharedId = Integer.parseInt(sharedFileId);
		SharedFileModel sharedFile = postService.getSharedFileModel(sharedId);

		InputStream is = ContentRenderController.class
				.getResourceAsStream("/../" + sharedFile.getLink());
		if (sharedFile.getExtention().equalsIgnoreCase("txt")) {
			ec.setResponseContentType("text/html");
			ec.setResponseCharacterEncoding("UTF-8");
			PrintWriter out = (PrintWriter) ec.getResponseOutputWriter();
            BufferedReader br = new BufferedReader((new InputStreamReader(is)));
			String word;
			while ((word = br.readLine()) != null) {
				out.println(word + "<br>");
			}
		}
		if(sharedFile.getExtention().equalsIgnoreCase("jpeg") || sharedFile.getExtention().equalsIgnoreCase("jpg")){
			is.read(b);
			ec.setResponseContentType("image/jpg");
			ec.setResponseCharacterEncoding("UTF-8");
            ServletOutputStream out = (ServletOutputStream) ec
					.getResponseOutputStream();
			out.write(b);	
		}
		if(sharedFile.getExtention().equalsIgnoreCase("png")){
			is.read(b);
			ec.setResponseContentType("image/png");
			ec.setResponseCharacterEncoding("UTF-8");
            ServletOutputStream out = (ServletOutputStream) ec
					.getResponseOutputStream();
			out.write(b);	
		}
		if(sharedFile.getExtention().equalsIgnoreCase("gif")){
			is.read(b);
			ec.setResponseContentType("image/gif");
			ec.setResponseCharacterEncoding("UTF-8");
            ServletOutputStream out = (ServletOutputStream) ec
					.getResponseOutputStream();
			out.write(b);	
		}
		if(sharedFile.getExtention().equalsIgnoreCase("pdf")){
		is.read(b);
		ec.setResponseContentType("application/pdf");
		ec.setResponseCharacterEncoding("UTF-8");

		ServletOutputStream response = (ServletOutputStream) ec
				.getResponseOutputStream();
		response.write(b);
		}
		if(sharedFile.getExtention().equalsIgnoreCase("docx")||sharedFile.getExtention().equalsIgnoreCase("doc")){
			is.read(b);
			ec.setResponseContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
			ec.setResponseCharacterEncoding("UTF-8");

			ServletOutputStream response = (ServletOutputStream) ec
					.getResponseOutputStream();
			response.write(b);
			}
		if(sharedFile.getExtention().equalsIgnoreCase("xls")){
			is.read(b);
			ec.setResponseContentType("application/vnd.ms-excel");
			ec.setResponseCharacterEncoding("UTF-8");

			ServletOutputStream response = (ServletOutputStream) ec
					.getResponseOutputStream();
			response.write(b);
		}
		if(sharedFile.getExtention().equalsIgnoreCase("xlsx")){
			is.read(b);
			ec.setResponseContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
			ec.setResponseCharacterEncoding("UTF-8");

			ServletOutputStream response = (ServletOutputStream) ec
					.getResponseOutputStream();
			response.write(b);
		}
		if(sharedFile.getExtention().equalsIgnoreCase("pptx")){
			is.read(b);
			ec.setResponseContentType("application/vnd.openxmlformats-officedocument.presentationml.presentation");
			ec.setResponseCharacterEncoding("UTF-8");

			ServletOutputStream response = (ServletOutputStream) ec
					.getResponseOutputStream();
			response.write(b);
		}
		fc.responseComplete(); // Important! Prevents JSF from proceeding to
								// render HTML.
	}

}

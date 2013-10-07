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
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

@Named
@Scope("session")
public class ContentRenderController implements Serializable{
	
	private static final long serialVersionUID = -7145407124462389612L;

	public void renderContent()throws IOException{
		 FacesContext fc = FacesContext.getCurrentInstance();
		    ExternalContext ec = fc.getExternalContext();
		    Map<String, String> params = ec.getRequestParameterMap();
		    String sharedFileId = params.get("sharedFileId"); // Returns request parameter with name "foo".
		    // ...
            InputStream is=ContentRenderController.class.getResourceAsStream("/../"+"classes/sharedFiles/gridlink.txt");
		    ec.setResponseContentType("application/pdf");
		    ec.setResponseCharacterEncoding("UTF-8");
		    PrintWriter out=(PrintWriter)ec.getResponseOutputWriter();
		    BufferedReader br = new BufferedReader((new InputStreamReader(is)));
		    String word;
		    while((word= br.readLine())!= null)
		    {
		    out.println(word+"<br>");
		    }
		    out.write("Some text content"+sharedFileId);
		    // ...

		    fc.responseComplete(); // Important! Prevents JSF from proceeding to render HTML.
	}

}

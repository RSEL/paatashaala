package com.ramselabs.paataprot.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="my Bean")
@SessionScoped
public class Managed {

private String pageToDisplay = "home.xhtml";

  public String getPageToDisplay(){
      return this.pageToDisplay;
  }

  public void setPageToDisplay(String pageToDisplay){
     this.pageToDisplay = pageToDisplay;
  }
}
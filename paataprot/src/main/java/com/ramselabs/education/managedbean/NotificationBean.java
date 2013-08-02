package com.ramselabs.education.managedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;

@Named
@Scope("session")
public class NotificationBean implements Serializable{

	private static final long serialVersionUID = -8625067741691197615L;
    private List<NotificationText> notif=new ArrayList<NotificationText>();
    public NotificationBean(){
    	NotificationText t1=new NotificationText();
    	t1.setText("Hello");
    	NotificationText t2=new NotificationText();
    	t1.setText("Hi!");
    	NotificationText t3=new NotificationText();
    	t1.setText("RamLabs");
    	notif.add(t1);
    	notif.add(t2);
    	notif.add(t3);
    }
	public List<NotificationText> getNotif() {
		return notif;
	}
	class NotificationText{
		public String text;

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}
		
	}
}

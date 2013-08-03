package com.ramselabs.education.managedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.ramselabs.education.model.NotificationContent;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

@Named
@Scope("session")
public class NotificationBean implements Serializable{

	private static final long serialVersionUID = -8625067741691197615L;
    private List<NotificationContent> notif=new ArrayList<NotificationContent>();
    public NotificationBean(){
    	NotificationContent t1=new NotificationContent();
    	t1.setText("Hello");
    	NotificationContent t2=new NotificationContent();
    	t2.setText("Hi!");
    	NotificationContent t3=new NotificationContent();
    	t3.setText("RamLabs");
    	notif.add(t1);
    	notif.add(t2);
    	notif.add(t3);
    }
    
	public void setNotif(List<NotificationContent> notif) {
		this.notif = notif;
	}

	public List<NotificationContent> getNotif() {
		return notif;
	}
	
}

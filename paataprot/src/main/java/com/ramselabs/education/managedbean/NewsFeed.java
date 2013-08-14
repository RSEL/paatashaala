package com.ramselabs.education.managedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.ramselabs.education.model.PostDescriptionModel;

@Named
@Scope("session")
public class NewsFeed implements Serializable{
	private static final long serialVersionUID = -9132004618821124996L;
    
	private List<PostDescriptionModel> persons=new ArrayList<PostDescriptionModel>();
	
	/*public NewsFeed(){
		persons.add(new Person("Golf","This is 1987 Golf Model"));
		persons.add(new Person("Jetta","This is Jetta 1999"));
		persons.add(new Person("Passat","This is Passat 1979"));
		persons.add(new Person("Polo","This is Polo 1989"));
	}*/

	public List<PostDescriptionModel> getPersons() {
		return persons;
	}

	public void setPersons(List<PostDescriptionModel> persons) {
		this.persons = persons;
	}
	
}

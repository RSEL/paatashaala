package com.ramselabs.education.managedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.ramselabs.education.model.Person;

@Named
@Scope("session")
public class NewsFeed implements Serializable{
	private static final long serialVersionUID = -9132004618821124996L;
    
	private List<Person> persons=new ArrayList<Person>();
	
	public NewsFeed(){
		persons.add(new Person("Golf","This is 1987 Golf Model"));
		persons.add(new Person("Jetta","This is Jetta 1999"));
		persons.add(new Person("Passat","This is Passat 1979"));
		persons.add(new Person("Polo","This is Polo 1989"));
	}

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}
	
}

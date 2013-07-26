package com.ramselabs.education.parser;



import java.io.InputStream;
import java.util.HashMap;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.ramselabs.education.util.HibernateProperty;

public class ConfigReader extends DefaultHandler {
	private HashMap<String,HibernateProperty>hibernateProperties=new HashMap<String,HibernateProperty>();
	private HibernateProperty hiberProps;
	private String content;
	private String propName;
	public ConfigReader(InputStream is)throws Exception {
		SAXParserFactory sax=SAXParserFactory.newInstance();
		SAXParser sp=sax.newSAXParser();
		sp.parse(is, this);
	}
	@Override
	public void startElement(String uri,String localName,String qName,Attributes attributes)throws SAXException{
		if("config-hibernate".equals(qName)){
			hiberProps=new HibernateProperty();
		}
		
		if("prop".equals(qName)){
			propName=attributes.getValue("name");
		}
		if("props".equals(qName)){
			
			propName=attributes.getValue("name");
		}
	}
	@Override
	public void characters(char[]ch,int start,int length) throws SAXException{
		content=new String(ch,start,length);
		content=content.trim();
	}
   @Override
   public void endElement(String uri,String localName,String qName)throws SAXException{
	   if("config-hibernate".equals(qName)){
		   hibernateProperties.put("hibernateProps", hiberProps);
	   }
	   
	   if("prop".equals(qName)){
		    	
		   hiberProps.putProp(propName, content);
	   }
	  
	   if("value".equals(qName)){
		   hiberProps.setAnnotatedClass(content);
	   }
   }
   public HibernateProperty getConfigHibernate(String name){
	   return hibernateProperties.get(name);
   }
   
}

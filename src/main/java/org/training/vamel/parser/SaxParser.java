package org.training.vamel.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.training.vamel.App;
import org.training.vamel.model.Employee;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxParser extends DefaultHandler {
	private List<Employee> employees;
	private Employee tempEmployee;
	private String tempValue;
	public SaxParser() {
		this.employees = new ArrayList<Employee>();
	}
	
	public void runParser() {
		parseDocument();
		printData();
	}
 	
	private void parseDocument() {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			SAXParser parser = factory.newSAXParser();
			parser.parse(App.XML_FILE, this);
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void printData() {
		System.out.println("from SAX parser:\nNumber of employees: " + employees.size());
		Iterator<Employee> iterator = employees.iterator();
		while(iterator.hasNext()) {
			System.out.println(iterator.next().toString());
		}
	}
	
	public void startElement(String uri, String localName, String qName, Attributes attributes) 
			throws SAXException {
		tempValue = "";
		if(qName.equalsIgnoreCase("Employee")) {
			tempEmployee = new Employee();
			tempEmployee.setType(attributes.getValue("type"));
		}
	}
	public void characters(char[] ch, int start, int length) 
			throws SAXException {
		tempValue = new String(ch,start,length);
	}
	public void endElement(String uri, String localName, String qName)
			throws SAXException {

		if(qName.equalsIgnoreCase("Employee")) {
			employees.add(tempEmployee);
		} else if (qName.equalsIgnoreCase("Name")) {
			tempEmployee.setName(tempValue);
		} else if (qName.equalsIgnoreCase("Id")) {
			tempEmployee.setId(Integer.parseInt(tempValue));
		} else if (qName.equalsIgnoreCase("Age")) {
			tempEmployee.setAge(Integer.parseInt(tempValue));
		}
	}
}

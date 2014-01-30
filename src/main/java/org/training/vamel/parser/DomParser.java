package org.training.vamel.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.training.vamel.model.Employee;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomParser {
	private List<Employee> employeeList;
	Document dom;

	public DomParser() {
		employeeList = new ArrayList<Employee>();
	}
	public void runParser() {
		this.parseXmlFile();
		this.parseDocument();
		this.printData();
	}
	public static void main(String[] args) {
		DomParser parser = new DomParser();
		parser.runParser();
	}
	private void parseXmlFile() {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = dbFactory.newDocumentBuilder();
			dom = builder.parse("employees.xml");
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void parseDocument() {
		Element docElement = dom.getDocumentElement();
		System.out.println("get ns: " + docElement.getAttribute("xmlns:employees")); 
		NodeList nodeList = docElement.getElementsByTagName("Employee");
		if (nodeList != null && nodeList.getLength() > 0) {
			for (int i = 0; i < nodeList.getLength(); i++) {
				Element element = (Element) nodeList.item(i);
				Employee employee = getEmployee(element);
				employeeList.add(employee);
			}
		}

	}

	private void printData() {
		System.out.println("Number of Employees '" + employeeList.size() + "'.");
		Iterator<Employee> it = employeeList.iterator();
		while (it.hasNext()) {
			System.out.println(it.next().toString());
		}
	}

	private Employee getEmployee(Element element) {
		int id = getIntValue(element, "Id");
		String name = getTextValue(element, "Name");
		int age = getIntValue(element, "Age");
		String type = element.getAttribute("type");
		Employee employee = new Employee(id, name, age, type);
		return employee;
	}

	private String getTextValue(Element element, String tagName) {
		String textValue = null;
		NodeList nodeList = element.getElementsByTagName(tagName);
		if (nodeList != null && nodeList.getLength() > 0) {
			Element elementN = (Element) nodeList.item(0);
			textValue = elementN.getFirstChild().getNodeValue();
		}
		return textValue;
	}

	private int getIntValue(Element element, String tagName) {
		int intValue;
		try {
			intValue = Integer.parseInt(getTextValue(element, tagName));
		} catch (NumberFormatException e) {
			e.printStackTrace();
			intValue = -1;
		}
		return intValue;
	}
}

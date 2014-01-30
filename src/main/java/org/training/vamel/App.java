package org.training.vamel;

import org.training.vamel.validator.XmlValidator;

public class App 
{
	public static final String XML_FILE = "employees.xml";
	public static final String XSD_FILE = "employees.xsd";
	public static final String DTD_FILE = "employees.dtd";
    public static void main( String[] args )
    {
        XmlValidator validator = new XmlValidator();
        boolean xsdValid = validator.validateXMLSchema(XSD_FILE, XML_FILE);
        boolean dtdValid = validator.validateDTD(DTD_FILE, XML_FILE);
        System.out.println("Validation with XSD: " + xsdValid);
        System.out.println("Validation with DTD: " + dtdValid);
    }
}

package org.training.vamel.validator;

import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.w3c.dom.Document;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;

public class XmlValidator {
	public XmlValidator() {
	}

	public boolean validateXMLSchema(String xsdFileName, String xmlFileName) {
		File xsdFile = new File(xsdFileName);
		File xmlFile = new File(xmlFileName);
		StreamSource source = new StreamSource(xmlFile);
		try {
			SchemaFactory factory = SchemaFactory
					.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = factory.newSchema(xsdFile);
			Validator validator = schema.newValidator();
			validator.validate(source);
		} catch (SAXException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean validateDTD(String dtdFileName, String xmlFileName) {
		boolean isValid = true;
		File xmlFile = new File(xmlFileName);
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			factory.setValidating(true);
			DocumentBuilder builder = factory.newDocumentBuilder();
			ValidationErrorHandler errorHandler = new ValidationErrorHandler();
			builder.setErrorHandler(errorHandler);
			Document document = builder.parse(xmlFile);
			isValid = errorHandler.isValid();
		} catch (ParserConfigurationException e) {
			System.out.println(e.toString());
			isValid = false;
		} catch (SAXException e) {
			System.out.println(e.toString());
			isValid = false;
		} catch (IOException e) {
			System.out.println(e.toString());
			isValid = false;
		}
		return isValid;
	}

}

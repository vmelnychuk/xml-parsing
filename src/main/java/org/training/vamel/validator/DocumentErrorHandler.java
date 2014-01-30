package org.training.vamel.validator;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class DocumentErrorHandler implements ErrorHandler {

	public void warning(SAXParseException exception) throws SAXException {
		System.out.println("Warning: ");
		printInfo(exception);

	}

	public void error(SAXParseException exception) throws SAXException {
		System.out.println("Error: ");
		printInfo(exception);

	}

	public void fatalError(SAXParseException exception) throws SAXException {
		System.out.println("Fattal error: ");
		printInfo(exception);

	}

	private void printInfo(SAXParseException e) {
		System.out.println("   Public ID: " + e.getPublicId());
		System.out.println("   System ID: " + e.getSystemId());
		System.out.println("   Line number: " + e.getLineNumber());
		System.out.println("   Column number: " + e.getColumnNumber());
		System.out.println("   Message: " + e.getMessage());
	}
}

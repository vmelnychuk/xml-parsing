package org.training.vamel.validator;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class ValidationErrorHandler implements ErrorHandler {
	private boolean valid = true;
	public boolean isValid() {
		return this.valid;
	}

	public void warning(SAXParseException exception) throws SAXException {
		System.out.println("Warning: ");
		printInfo(exception);

	}

	public void error(SAXParseException exception) throws SAXException {
		System.out.println("Error: ");
		printInfo(exception);
		this.valid = false;

	}

	public void fatalError(SAXParseException exception) throws SAXException {
		System.out.println("Fatal error: ");
		printInfo(exception);
		this.valid = false;
	}

	private void printInfo(SAXParseException exception) {
		System.out.println("  line number: " + exception.getLineNumber());
		System.out.println("  message: " + exception.getMessage());
	}
}

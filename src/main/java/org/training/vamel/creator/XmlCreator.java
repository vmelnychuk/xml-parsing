package org.training.vamel.creator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.training.vamel.model.Book;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class XmlCreator {
	private List<Book> books;
	private Document document;

	public XmlCreator() {
		books = new ArrayList<Book>();
		loadData();
		createDocument();
	}

	private void loadData() {
		books.add(new Book("Head First Java", "Kathy Sierra .. etc", "Java 1.5"));
		books.add(new Book("Head First Design Patterns", "Kathy Sierra .. etc",
				"Java Architect"));
	}

	private void createDocument() {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			document = builder.newDocument();
		} catch (ParserConfigurationException e) {
			System.out
					.println("Error while trying to instantiate DocumentBuilder "
							+ e);
		}
	}

	private void createDOMTree() {
		Element rootElement = document.createElement("Books");
		document.appendChild(rootElement);
		Iterator<Book> iterator = books.iterator();
		while (iterator.hasNext()) {
			Book book = iterator.next();
			Element bookElement = createBookElement(book);
			rootElement.appendChild(bookElement);
		}
	}

	private Element createBookElement(Book book) {
		Element bookElement = document.createElement("Book");
		bookElement.setAttribute("Subject", book.getSubject());
		
		Element authorElement = document.createElement("Author");
		Text authorText = document.createTextNode(book.getAuthor());
		authorElement.appendChild(authorText);
		bookElement.appendChild(authorElement);
		
		Element titleElement = document.createElement("Title");
		Text titleText = document.createTextNode(book.getTitle());
		titleElement.appendChild(titleText);
		bookElement.appendChild(titleElement);
		
		return bookElement;

	}
	private void printToFile(){

		try
		{
			//print
			OutputFormat format = new OutputFormat(dom);
			format.setIndenting(true);

			//to generate output to console use this serializer
			//XMLSerializer serializer = new XMLSerializer(System.out, format);


			//to generate a file output use fileoutputstream instead of system.out
			XMLSerializer serializer = new XMLSerializer(
			new FileOutputStream(new File("book.xml")), format);

			serializer.serialize(dom);

		} catch(IOException ie) {
		    ie.printStackTrace();
		}
	}

}

package org.training.vamel.creator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.training.vamel.model.Book;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSException;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;

public class XmlCreator {
	private List<Book> books;
	private Document document;
	private File xmlFile;

	public XmlCreator() {
		books = new ArrayList<Book>();
		loadData();
		createDocument();
	}
	public void runCreator(){
		System.out.println("Started .. ");
		createDOMTree();
		printToFile();
		System.out.println("Generated file successfully.");
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

	private void printToFile() {
		FileOutputStream fos = null;
		try {
			xmlFile = new File("books.xml");
			fos = new FileOutputStream(xmlFile);
			DOMImplementationRegistry reg = DOMImplementationRegistry
					.newInstance();
			DOMImplementationLS impl = (DOMImplementationLS) reg
					.getDOMImplementation("LS");
			LSSerializer serializer = impl.createLSSerializer();
			serializer.getDomConfig().setParameter("format-pretty-print", Boolean.TRUE);
			LSOutput lso = impl.createLSOutput();
			lso.setByteStream(fos);
			serializer.write(document, lso);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassCastException e) {
			e.printStackTrace();
		} catch (DOMException e) {
			e.printStackTrace();
		} catch (LSException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	public static void main(String[] args) {
		XmlCreator creator = new XmlCreator();
		creator.runCreator();
	}
}

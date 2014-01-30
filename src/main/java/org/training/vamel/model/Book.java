package org.training.vamel.model;

public class Book {
	private String title;
	private String author;
	private String subject;
	public Book(String title, String author, String subject) {
		this.title = title;
		this.author = author;
		this.subject = subject;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	@Override
	public String toString() {
		return "Book [title=" + title + ", author=" + author + ", subject="
				+ subject + "]";
	}
}

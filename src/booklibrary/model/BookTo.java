package booklibrary.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class BookTo {
	private Long id;
	private String title;
	private String authors;
	private String comment;
	
	//TODO RSmolka clean up delete unnescesary comments and stuff
//	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

	public BookTo(Long id, String title, String authors) {
		this.id = id;
		this.title = title;
		this.authors = authors;
		this.comment = "";
	}

	public BookTo(String bookTitle, String bookAuthors) {
		this.title = bookTitle;
		this.authors = bookAuthors;
	}

//	public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
//		propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
//	}
//
//	public void removePropertyChangeListener(PropertyChangeListener listener) {
//		propertyChangeSupport.removePropertyChangeListener(listener);
//	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
//		propertyChangeSupport.firePropertyChange("bookId", this.id, this.id = id);
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
//		propertyChangeSupport.firePropertyChange("bookTitle", this.title, this.title = title);
		 this.title = title;
	}

	public String getAuthors() {
		return authors;
	}

	public void setAuthors(String authors) {
//		propertyChangeSupport.firePropertyChange("bookAuthors", this.authors, this.authors = authors);
		this.authors = authors;
	}
	
	@Override
	public String toString() {
		return "Book no. " + id + ", " + title + ", " + authors;
	}
}

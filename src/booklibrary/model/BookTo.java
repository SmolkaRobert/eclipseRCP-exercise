package booklibrary.model;

public class BookTo extends ModelPropertyObject {
	private Long id;
	private String title;
	private String authors;

	public BookTo() {
		
	}

	public BookTo(Long id, String title, String authors) {
		this.id = id;
		this.title = title;
		this.authors = authors;
	}

	public BookTo(String bookTitle, String bookAuthors) {
		this.title = bookTitle;
		this.authors = bookAuthors;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
//		firePropertyChange("id", this.id, this.id = id);
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
//		firePropertyChange("title", this.title, this.title = title);
		this.title = title;
	}

	public String getAuthors() {
		return authors;
	}

	public void setAuthors(String authors) {
//		firePropertyChange("authors", this.authors, this.authors = authors);
		this.authors = authors;
	}

	@Override
	public String toString() {
		return "Book no. " + id + ", " + title + ", " + authors;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authors == null) ? 0 : authors.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookTo other = (BookTo) obj;
		if (authors == null) {
			if (other.authors != null)
				return false;
		} else if (!authors.equals(other.authors))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
}

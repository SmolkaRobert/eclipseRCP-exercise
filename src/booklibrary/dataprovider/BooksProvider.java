package booklibrary.dataprovider;

import java.util.List;

import booklibrary.model.BookTo;

public interface BooksProvider {
	public List<BookTo> getAllBooks();

	public void addBook(BookTo bookToAdd);

	public void updateBook(BookTo bookToChange);

	public void deleteBook(BookTo bookToDelete);
}

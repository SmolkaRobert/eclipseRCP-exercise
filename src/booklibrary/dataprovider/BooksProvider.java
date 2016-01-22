package booklibrary.dataprovider;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import booklibrary.model.BookTo;

public interface BooksProvider {
	public Collection<BookTo> getAllBooks() throws JsonParseException, JsonMappingException, IOException;

	public void addBook(BookTo bookToAdd) throws JsonProcessingException;

	public void updateBook(BookTo bookToChange) throws JsonProcessingException;

	public void deleteBook(BookTo bookToDelete) throws JsonProcessingException;
	
}

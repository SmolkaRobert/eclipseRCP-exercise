package booklibrary.dataprovider.impl;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import booklibrary.dataprovider.BooksProvider;
import booklibrary.model.BookTo;
import booklibrary.restclient.RestClient;

public class BooksProviderImpl implements BooksProvider {

	private static final String ALL_BOOKS = "*";
	private static WebTarget webTarget;
	private static ObjectMapper jsonConverter;
	private static BooksProviderImpl booksProviderImplInstance = null;

	private BooksProviderImpl() {
		webTarget = RestClient.getInstance().getRestWebTarget();
		jsonConverter = new ObjectMapper();
	}

	public static BooksProviderImpl getInstance() {
		if (booksProviderImplInstance == null) {
			booksProviderImplInstance = new BooksProviderImpl();
		}
		
		return booksProviderImplInstance;
	}

	public Collection<BookTo> getAllBooks() throws JsonParseException, JsonMappingException, IOException {

		WebTarget allBooksTarget = webTarget.path("books-by-title").queryParam("titlePrefix", ALL_BOOKS);

		String jsonBooks = allBooksTarget.request().accept(MediaType.APPLICATION_JSON).get(Response.class)
				.readEntity(String.class);
		
		List<BookTo> booksList = Arrays.asList(jsonConverter.readValue(jsonBooks, BookTo[].class)) ;
		return booksList;
	}

	@Override
	public void addBook(BookTo bookToAdd) throws JsonProcessingException {
		WebTarget addBookTarget = webTarget.path("/book");
		String jsonBook = jsonConverter.writeValueAsString(bookToAdd);
		addBookTarget.request().accept(MediaType.APPLICATION_JSON).post(Entity.json(jsonBook));
	}

	@Override
	public void updateBook(BookTo bookToChange) throws JsonProcessingException {
		WebTarget addBookTarget = webTarget.path("/book");
		String jsonBook = jsonConverter.writeValueAsString(bookToChange);
		addBookTarget.request().accept(MediaType.APPLICATION_JSON).put(Entity.json(jsonBook));
	}

	@Override
	public void deleteBook(BookTo bookToDelete) throws JsonProcessingException {
		WebTarget addBookTarget = webTarget.path("/bookDelete");
		String jsonBook = jsonConverter.writeValueAsString(bookToDelete);
		//using post because delete rest in glassfish is ugly
		addBookTarget.request().accept(MediaType.APPLICATION_JSON).post(Entity.json(jsonBook));
	}

}

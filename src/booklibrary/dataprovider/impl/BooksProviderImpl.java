package booklibrary.dataprovider.impl;

import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import booklibrary.dataprovider.BooksProvider;
import booklibrary.model.BookTo;
import booklibrary.restclient.RestClient;

public class BooksProviderImpl implements BooksProvider {

	private static final String ALL_BOOKS = "*";
	private static WebTarget webTarget;
	private static Gson jsonConverter;
	private static BooksProviderImpl booksProviderImplInstance = null;

	private BooksProviderImpl() {
	}

	public static BooksProviderImpl getInstance() {
		if (booksProviderImplInstance == null) {
			booksProviderImplInstance = new BooksProviderImpl();
		}
		
		webTarget = RestClient.getInstance().getRestWebTarget();
		jsonConverter = new Gson();
		return booksProviderImplInstance;
	}

	public List<BookTo> getAllBooks() {

		WebTarget allBooksTarget = webTarget.path("books-by-title").queryParam("titlePrefix", ALL_BOOKS);

		String jsonBooks = allBooksTarget.request().accept(MediaType.APPLICATION_JSON).get(Response.class)
				.readEntity(String.class);
		List<BookTo> booksList = jsonConverter.fromJson(jsonBooks, new TypeToken<List<BookTo>>() {
		}.getType());
		return booksList;
	}

	@Override
	public void addBook(BookTo bookToAdd) {
		WebTarget addBookTarget = webTarget.path("/book");
		String jsonBook = jsonConverter.toJson(bookToAdd);
		addBookTarget.request().accept(MediaType.APPLICATION_JSON).post(Entity.json(jsonBook));
	}

	@Override
	public void updateBook(BookTo bookToChange) {
		WebTarget addBookTarget = webTarget.path("/book");
		String jsonBook = jsonConverter.toJson(bookToChange);
		addBookTarget.request().accept(MediaType.APPLICATION_JSON).put(Entity.json(jsonBook));
	}

	@Override
	public void deleteBook(BookTo bookToDelete) {
		WebTarget addBookTarget = webTarget.path("/bookDelete");
		String jsonBook = jsonConverter.toJson(bookToDelete);
		//using post because delete rest in glassfish is ugly
		addBookTarget.request().accept(MediaType.APPLICATION_JSON).post(Entity.json(jsonBook));
	}

}

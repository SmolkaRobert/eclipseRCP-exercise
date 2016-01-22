package booklibrary.tableinputprovider;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.databinding.observable.list.WritableList;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import booklibrary.dataprovider.BooksProvider;
import booklibrary.dataprovider.impl.BooksProviderImpl;
import booklibrary.model.BookTo;

public class TableInputProvider {
	private static TableInputProvider tableInputProviderInstance = null;
	private static WritableList booksInput;
	private BooksProvider booksProvider;

	private TableInputProvider() throws JsonParseException, JsonMappingException, IOException {
		booksProvider = BooksProviderImpl.getInstance();
		booksInput = new WritableList(booksProvider.getAllBooks(), BookTo.class);
	}

	public void updateBooks() throws JsonParseException, JsonMappingException, IOException {
		Collection<BookTo> booksUpdate = booksProvider.getAllBooks();
		booksInput.clear();
		booksInput.addAll(booksUpdate);
	}

	public static TableInputProvider getInstance() throws JsonParseException, JsonMappingException, IOException {
		if (tableInputProviderInstance == null) {
			tableInputProviderInstance = new TableInputProvider();
		}

		return tableInputProviderInstance;
	}

	public WritableList getAllBooks() {
		return booksInput;
	}
}

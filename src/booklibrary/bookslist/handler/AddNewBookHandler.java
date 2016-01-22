package booklibrary.bookslist.handler;

import java.io.IOException;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.PlatformUI;

import com.fasterxml.jackson.core.JsonProcessingException;

import booklibrary.bookslist.dialog.AddNewBookDialog;
import booklibrary.dataprovider.BooksProvider;
import booklibrary.dataprovider.impl.BooksProviderImpl;
import booklibrary.model.BookTo;
import booklibrary.tableinputprovider.TableInputProvider;

public class AddNewBookHandler extends AbstractHandler{
	

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		AddNewBookDialog dialog = new AddNewBookDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());
	    
	    // get the new values from the dialog
	    if (dialog.open() == Window.OK) {
	    	BookTo bookToAdd = new BookTo(dialog.getBookTitle(), dialog.getBookAuthors());
	    	BooksProvider booksProvider = BooksProviderImpl.getInstance();
			try {
				booksProvider.addBook(bookToAdd);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			
			TableInputProvider inputProvider;
			try {
				inputProvider = TableInputProvider.getInstance();
				inputProvider.updateBooks();
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
		return null;
	}

}

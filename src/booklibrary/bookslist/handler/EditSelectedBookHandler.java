package booklibrary.bookslist.handler;

import java.io.IOException;
import java.util.Iterator;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import booklibrary.bookslist.dialog.EditSelectedBookDialog;
import booklibrary.dataprovider.BooksProvider;
import booklibrary.dataprovider.impl.BooksProviderImpl;
import booklibrary.model.BookTo;
import booklibrary.tableinputprovider.TableInputProvider;

public class EditSelectedBookHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ISelection selection = HandlerUtil.getCurrentSelection(event);
		if (selection != null & selection instanceof IStructuredSelection) {

			IStructuredSelection structuredSelection = (IStructuredSelection) selection;

			try {
				TableInputProvider inputProvider;
				BooksProvider booksProvider = BooksProviderImpl.getInstance();
				inputProvider = TableInputProvider.getInstance();
				for (Iterator<BookTo> iterator = structuredSelection.iterator(); iterator.hasNext();) {
					BookTo bookToChange = iterator.next();

					Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
					EditSelectedBookDialog dialog = new EditSelectedBookDialog(shell, bookToChange);

					if (dialog.open() == Window.OK) {
						bookToChange.setTitle(dialog.getBookTitle());
						bookToChange.setAuthors(dialog.getBookAuthors());

						booksProvider.updateBook(bookToChange);
					}

				}

				inputProvider.updateBooks();
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

}

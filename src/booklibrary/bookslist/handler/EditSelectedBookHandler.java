package booklibrary.bookslist.handler;

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

import booklibrary.bookslist.dialog.EditSelectedBookDialog;
import booklibrary.dataprovider.BooksProvider;
import booklibrary.dataprovider.impl.BooksProviderImpl;
import booklibrary.model.BookTo;

public class EditSelectedBookHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ISelection selection = HandlerUtil.getCurrentSelection(event);
		if (selection != null & selection instanceof IStructuredSelection) {
			
			BooksProvider booksProvider = BooksProviderImpl.getInstance();
			
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			for (Iterator<BookTo> iterator = structuredSelection.iterator(); iterator.hasNext();) {
				BookTo bookToChange = iterator.next();

				Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
				EditSelectedBookDialog dialog = new EditSelectedBookDialog(shell, bookToChange);

				if (dialog.open() == Window.OK) {
					//TODO RSmolka change to data binding; not necesary to set dialog and then set booktoChange fields
					bookToChange.setTitle(dialog.getBookTitle());
					bookToChange.setAuthors(dialog.getBookAuthors());
					
					booksProvider.updateBook(bookToChange);
				}
			}
		}
		return null;
	}

}

package booklibrary.bookslist.handler;

import java.io.IOException;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import booklibrary.bookslist.dialog.BookDeletionConfirmationDialog;
import booklibrary.dataprovider.BooksProvider;
import booklibrary.dataprovider.impl.BooksProviderImpl;
import booklibrary.model.BookTo;
import booklibrary.tableinputprovider.TableInputProvider;

public class DeleteSelectedBookHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow activeWorkbenchWindow = HandlerUtil.getActiveWorkbenchWindow(event);
		ISelection selection = activeWorkbenchWindow.getActivePage().getSelection();
		if (selection != null & selection instanceof IStructuredSelection) {
			BooksProvider booksProvider = BooksProviderImpl.getInstance();

			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			List<BookTo> booksToBeDeleted = structuredSelection.toList();

			Shell shell = activeWorkbenchWindow.getShell();
			BookDeletionConfirmationDialog messageDialog = new BookDeletionConfirmationDialog(shell, booksToBeDeleted);

			if (messageDialog.open() == Window.OK) {
				TableInputProvider inputProvider;
				try {
					inputProvider = TableInputProvider.getInstance();
					for (BookTo singleBookToDelete : booksToBeDeleted) {
						booksProvider.deleteBook(singleBookToDelete);
						inputProvider.updateBooks();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
	}
}
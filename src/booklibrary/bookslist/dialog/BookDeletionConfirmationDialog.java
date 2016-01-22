package booklibrary.bookslist.dialog;

import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import booklibrary.model.BookTo;

public class BookDeletionConfirmationDialog extends Dialog {

	private List<BookTo> booksToBeDeleted;
	
	public BookDeletionConfirmationDialog(Shell parentShell, List<BookTo> booksToBeDeleted) {
		super(parentShell);
		this.booksToBeDeleted = booksToBeDeleted;
	}
	
	@Override
	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		shell.setText("Book deletion confirmation");
	};

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		GridLayout layout = new GridLayout(2, false);
		layout.marginRight = 5;
		layout.marginLeft = 10;
		container.setLayout(layout);
		
		String deleteConfirmationMessage = "Are you sure you want to delete the following books: ";
		for (BookTo singleBookToDelete : booksToBeDeleted) {
			deleteConfirmationMessage += "\n-" + singleBookToDelete.getTitle();
		}

		Label booksDeletionInformation = new Label(container, SWT.NONE);
		booksDeletionInformation.setText(deleteConfirmationMessage);
		
		return container;
	}

	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
		createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
	}

	@Override
	protected Point getInitialSize() {
		return new Point(450, 400);
	}
	
	@Override
	protected void okPressed() {
		super.okPressed();
	}

}

package booklibrary.bookslist.view;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;

import booklibrary.model.BookTo;

public class BookDetailsView extends ViewPart {

	private ISelectionListener selectionListerner;

	@Override
	public void createPartControl(Composite parent) {
		// create a Label to map to
		Label detailsLabel = new Label(parent, SWT.BORDER);
		// parent has a GridLayout assigned
		detailsLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

		selectionListerner = new ISelectionListener() {
			public void selectionChanged(IWorkbenchPart part, ISelection selection) {
				if (!(selection instanceof IStructuredSelection))
					return;
				IStructuredSelection structuredSelection = (IStructuredSelection) selection;
				Object object = structuredSelection.getFirstElement();
				if (object instanceof BookTo) {
					BookTo book = (BookTo) object;
					detailsLabel.setText("Author " + book.getAuthors() + " wrote a book " + book.getTitle());
				}
			}
		};
		getSite().getPage().addSelectionListener(selectionListerner);
	}

	public void dispose() {
		getSite().getPage().removeSelectionListener(selectionListerner);
	}

	@Override
	public void setFocus() {

	}

}

package booklibrary.bookslist.sorter;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;

import booklibrary.model.BookTo;

public class BooksTableViewerComparator extends ViewerComparator {
	private static final int DEFAULT_COLUMN_NUMBER = 0;
	private static final int DESCENDING = 1;
	private int direction = DESCENDING;
	private int propertyIndex;

	public BooksTableViewerComparator() {
		this.propertyIndex = DEFAULT_COLUMN_NUMBER;
		this.direction = DESCENDING;
	}

	public int getDirection() {
		return direction == 1 ? SWT.DOWN : SWT.UP;
	}

	public void setColumn(int column) {
		if (column == this.propertyIndex) {
			// Same column as last sort; toggle the direction
			direction = 1 - direction;
		} else {
			// New column; do an ascending sort
			this.propertyIndex = column;
			direction = DESCENDING;
		}
	}

	@Override
	public int compare(Viewer viewer, Object bookElement1, Object bookElement2) {
		BookTo book1 = (BookTo) bookElement1;
		BookTo book2 = (BookTo) bookElement2;
		int comparisonResult = 0;
		switch (propertyIndex) {
		case 0:
			comparisonResult = book1.getId().compareTo(book2.getId());
			break;
		case 1:
			comparisonResult = book1.getTitle().compareTo(book2.getTitle());
			break;
		case 2:
			comparisonResult = book1.getAuthors().compareTo(book2.getAuthors());
			break;
		default:
			comparisonResult = 0;
		}
		// If descending order, flip the direction
		if (direction == DESCENDING) {
			comparisonResult = -comparisonResult;
		}
		return comparisonResult;
	}

}
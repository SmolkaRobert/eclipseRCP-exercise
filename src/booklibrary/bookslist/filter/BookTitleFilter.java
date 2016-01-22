package booklibrary.bookslist.filter;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import booklibrary.model.BookTo;

public class BookTitleFilter extends ViewerFilter {

	private String searchedTitle;

	public void setSearchText(String s) {
		// ensure that the value can be used for matching
		this.searchedTitle = ".*" + s + ".*";
	}

	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		if (searchedTitle == null || searchedTitle.length() == 0) {
			return true;
		}
		BookTo book = (BookTo) element;
		if (book.getTitle().toLowerCase().matches(searchedTitle.toLowerCase())) {
			return true;
		}

		return false;
	}
}

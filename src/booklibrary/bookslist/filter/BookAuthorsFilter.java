package booklibrary.bookslist.filter;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import booklibrary.model.BookTo;

public class BookAuthorsFilter extends ViewerFilter {

	private String searchedAuthors;

	public void setSearchText(String s) {
		// ensure that the value can be used for matching
		this.searchedAuthors = ".*" + s + ".*";
	}

	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		if (searchedAuthors == null || searchedAuthors.length() == 0) {
			return true;
		}
		BookTo book = (BookTo) element;
		if (book.getAuthors().matches(searchedAuthors)) {
			return true;
		}

		return false;
	}
}

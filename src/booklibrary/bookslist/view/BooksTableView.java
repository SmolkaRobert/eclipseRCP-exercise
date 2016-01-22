package booklibrary.bookslist.view;

import java.io.IOException;

import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.databinding.viewers.ViewerSupport;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import booklibrary.bookslist.filter.BookTitleFilter;
import booklibrary.bookslist.sorter.BooksTableViewerComparator;
import booklibrary.model.BookTo;
import booklibrary.tableinputprovider.TableInputProvider;

public class BooksTableView extends ViewPart {

	private TableViewer booksTableViewer;

	private BooksTableViewerComparator booksTableComparator;
	private BookTitleFilter bookTitleFilter;

	public BooksTableView() {
	}

	public void createPartControl(Composite parent) {
		GridLayout layout = new GridLayout(2, false);
		parent.setLayout(layout);

		Label searchLabel = new Label(parent, SWT.NONE);
		searchLabel.setText("Filter: ");

		final Text searchText = new Text(parent, SWT.BORDER | SWT.SEARCH);
		searchText.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL));

		try {
			createViewer(parent);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		// Set the sorter for the table
		booksTableComparator = new BooksTableViewerComparator();
		booksTableViewer.setComparator(booksTableComparator);

		searchText.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(org.eclipse.swt.events.KeyEvent e) {

			}

			@Override
			public void keyReleased(org.eclipse.swt.events.KeyEvent e) {
				bookTitleFilter.setSearchText(searchText.getText());
				booksTableViewer.refresh();
			}
		});

		bookTitleFilter = new BookTitleFilter();
		booksTableViewer.addFilter(bookTitleFilter);
	}

	private void createViewer(Composite parent) throws JsonParseException, JsonMappingException, IOException {
		booksTableViewer = new TableViewer(parent,
				SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);

		createColumns(parent, booksTableViewer);

		final Table table = booksTableViewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		booksTableViewer.getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		booksTableViewer.getTable().setHeaderVisible(true);

		ViewerSupport.bind(booksTableViewer, TableInputProvider.getInstance().getAllBooks(), BeanProperties.values(new String[] { "id", "title", "authors" }));

		// Create a menu manager and create context menu
		MenuManager menuManager = new MenuManager();
		Menu menu = menuManager.createContextMenu(booksTableViewer.getTable());
		// set the menu on the SWT widget
		booksTableViewer.getTable().setMenu(menu);
		// register the menu with the framework
		getSite().registerContextMenu(menuManager, booksTableViewer);

		// make the selection available to other views
		getSite().setSelectionProvider(booksTableViewer);
		// set the sorter for the table

		// define layout for the viewer
		GridData gridData = new GridData();
		gridData.verticalAlignment = GridData.FILL;
		gridData.horizontalSpan = 2;
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		gridData.horizontalAlignment = GridData.FILL;
		booksTableViewer.getControl().setLayoutData(gridData);
	}

	public TableViewer getViewer() {
		return booksTableViewer;
	}

	// create the columns for the table
	private void createColumns(final Composite parent, final TableViewer viewer) {
		String[] titles = { "Id", "Title", "Author" };
		int[] bounds = { 50, 200, 150 };

		// first column for the id
		TableViewerColumn col = createTableViewerColumn(titles[0], bounds[0], 0);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				BookTo book = (BookTo) element;
				return book.getId().toString();
			}
		});

		// second column for title
		col = createTableViewerColumn(titles[1], bounds[1], 1);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				BookTo book = (BookTo) element;
				return book.getTitle();
			}
		});

		// third column for authors
		col = createTableViewerColumn(titles[2], bounds[2], 2);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				BookTo book = (BookTo) element;
				return book.getAuthors();
			}
		});

	}

	private TableViewerColumn createTableViewerColumn(String columnTitle, int bound, final int columnNumber) {
		final TableViewerColumn viewerColumn = new TableViewerColumn(booksTableViewer, SWT.NONE);
		final TableColumn column = viewerColumn.getColumn();
		column.setText(columnTitle);
		column.setWidth(bound);
		column.setResizable(true);
		column.setMoveable(true);
		column.addSelectionListener(getSelectionAdapter(column, columnNumber));
		return viewerColumn;
	}

	private SelectionAdapter getSelectionAdapter(TableColumn column, int columnIndex) {
		SelectionAdapter selectionAdapter = new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				booksTableComparator.setColumn(columnIndex);
				int sortDirection = booksTableComparator.getDirection();
				booksTableViewer.getTable().setSortDirection(sortDirection);
				booksTableViewer.getTable().setSortColumn(column);
				booksTableViewer.refresh();
			}
		};
		return selectionAdapter;
	}

	public void setFocus() {
		booksTableViewer.getControl().setFocus();
	}
}
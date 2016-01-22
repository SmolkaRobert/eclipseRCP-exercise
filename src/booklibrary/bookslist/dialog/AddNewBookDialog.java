package booklibrary.bookslist.dialog;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class AddNewBookDialog extends Dialog {
	private Text bookTitleText;
	private Text bookAuthorsText;
	private String bookTitle = "";
	private String bookAuthors = "";

	public AddNewBookDialog(Shell parentShell) {
		super(parentShell);
	}
	
	@Override
	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		shell.setText("New book addition");
	};

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		GridLayout layout = new GridLayout(2, false);
		layout.marginRight = 5;
		layout.marginLeft = 10;
		container.setLayout(layout);

		Label bookTitleLabel = new Label(container, SWT.NONE);
		bookTitleLabel.setText("Title:");

		bookTitleText = new Text(container, SWT.BORDER);
		bookTitleText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		bookTitleText.setText(bookTitle);
		bookTitleText.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				Text textWidget = (Text) e.getSource();
				bookTitle = textWidget.getText();
			}
		});

		Label bookAuthorsLabel = new Label(container, SWT.NONE);
		GridData gd_lblNewLabel = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblNewLabel.horizontalIndent = 1;
		bookAuthorsLabel.setLayoutData(gd_lblNewLabel);
		bookAuthorsLabel.setText("Authors:");

		bookAuthorsText = new Text(container, SWT.BORDER);
		bookAuthorsText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		bookAuthorsText.setText(bookAuthors);
		bookAuthorsText.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				Text textWidget = (Text) e.getSource();
				bookAuthors = textWidget.getText();
			}
		});
		return container;
	}

	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, "Add book", true);
		createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
	}

	@Override
	protected Point getInitialSize() {
		return new Point(450, 150);
	}

	@Override
	protected void okPressed() {
		bookTitle = bookTitleText.getText();
		bookAuthors = bookAuthorsText.getText();
		super.okPressed();
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getBookAuthors() {
		return bookAuthors;
	}

	public void setBookAuthors(String bookAuthors) {
		this.bookAuthors = bookAuthors;
	}

}

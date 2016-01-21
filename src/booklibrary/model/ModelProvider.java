package booklibrary.model;

import java.util.ArrayList;
import java.util.List;

public enum ModelProvider {
  INSTANCE;

  private List<BookTo> books;

  private ModelProvider() {
    books = new ArrayList<BookTo>();
    // Image here some fancy database access to read the persons and to
    // put them into the model
    books.add(new BookTo(1L, "Zew Cthulhu", "H. P. Lovecraft"));
    books.add(new BookTo(2L, "Bały kieł", "Jack London"));
    books.add(new BookTo(3L, "Smok Jego Królewskiej Mości", "Naomi Novik"));
    books.add(new BookTo(4L, "Vertical", "Rafał Kosik"));
    books.add(new BookTo(5L, "Samsara", "Tomek Michniewicz"));
  }

  public List<BookTo> getAllBooks() {
    return books;
  }

} 



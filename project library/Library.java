import java.util.HashMap;
import java.util.Map;

public class Library {
    private Map<Integer, Book> bookMap;
    private int nextIndex;

    public Library() {
        bookMap = new HashMap<>();
        nextIndex = 1; // Starting index at 1 for user-friendly display
    }

    public boolean addBook(String name, String author) {
        try {
            bookMap.put(nextIndex, new Book(name, author));
            nextIndex++;
            return true;
        } catch (Exception ex) {
            System.out.println("Exception happened while adding the book");
            return false;
        }
    }

    public void displayBooks() {
        if (bookMap.isEmpty()) {
            System.out.println("No books available in the library.");
            return;
        }
        bookMap.forEach((index, book) -> {
            String status = book.isBorrowed() ? " (Borrowed)" : " (Available)";
            System.out.println(index + ". " + book.getBookname() + " by " + book.getAuthorName() + status);
        });
    }

    public void showBorrowedBooks() {
        if (bookMap.isEmpty()) {
            System.out.println("No books in the Library.");
            return;
        }
        System.out.println("Borrowed Books:");
        bookMap.entrySet().stream()
            .filter(entry -> entry.getValue().isBorrowed())
            .forEach(entry -> System.out.println(entry.getKey() + ". " + entry.getValue().getBookname()));
    }

    public void returnBook(int bookIndex) {
        Book book = bookMap.get(bookIndex);
        if (book == null) {
            System.out.println("Invalid book index.");
            return;
        }
        if (!book.isBorrowed()) {
            System.out.println("This book wasn't borrowed.");
            return;
        }
        book.setBorrowedStatus(false);
        System.out.println("Book '" + book.getBookname() + "' returned successfully.");
    }

    public boolean borrowBook(int bookIndex) {
        Book book = bookMap.get(bookIndex);
        if (book == null) {
            System.out.println("Invalid book index.");
            return false;
        }
        if (book.isBorrowed()) {
            System.out.println("Book is already borrowed.");
            return false;
        }
        book.setBorrowedStatus(true);
        return true;
    }
}
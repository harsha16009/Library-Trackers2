import java.util.Scanner;

public class Executor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library lib = new Library();

        // Add some sample books
        lib.addBook("The Great Gatsby", "F. Scott Fitzgerald");
        lib.addBook("To Kill a Mockingbird", "Harper Lee");
        lib.addBook("1984", "George Orwell");
        lib.addBook("Pride and Prejudice", "Jane Austen");

        while (true) {
            System.out.println("\n** Library Management System **");
            System.out.println("1. Show all books");
            System.out.println("2. Show borrowed books");
            System.out.println("3. Borrow a book");
            System.out.println("4. Return a book");
            System.out.println("5. Add a new book");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    lib.displayBooks();
                    break;
                case 2:
                    lib.showBorrowedBooks();
                    break;
                case 3:
                    lib.displayBooks();
                    System.out.print("Enter the number of the book you want to borrow: ");
                    int borrowIndex = sc.nextInt();
                    if (lib.borrowBook(borrowIndex)) {
                        System.out.println("Book borrowed successfully!");
                    }
                    break;
                case 4:
                    lib.showBorrowedBooks();
                    System.out.print("Enter the number of the book you're returning: ");
                    int returnIndex = sc.nextInt();
                    lib.returnBook(returnIndex);
                    break;
                case 5:
                    System.out.print("Enter book title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter author name: ");
                    String author = sc.nextLine();
                    if (lib.addBook(title, author)) {
                        System.out.println("Book added successfully!");
                    }
                    break;
                case 6:
                    System.out.println("Thank you for using the Library System!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
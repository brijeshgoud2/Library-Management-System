package major.Book;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BookManager manager = new BookManager();

        while (true) {
            System.out.println("\n--- Library Menu ---");
            System.out.println("1. Add a new book");
            System.out.println("2. Display all books");
            System.out.println("3. Update book status");
            System.out.println("4. Delete a book");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter title: ");
                    String title = sc.nextLine();

                    System.out.print("Enter author: ");
                    String author = sc.nextLine();

                    System.out.print("Enter ISBN: ");
                    String isbn = sc.nextLine();

                    System.out.print("Enter status (Available/Issued): ");
                    String status = sc.nextLine();

                    if (!status.equalsIgnoreCase("Available") && !status.equalsIgnoreCase("Issued")) {
                        System.out.println("Invalid status. Please enter 'Available' or 'Issued'.");
                    } else {
                        manager.addBook(title, author, isbn, status);
                    }
                    break;

                case 2:
                    manager.displayBooks();
                    break;

                case 3:
                    System.out.print("Enter book ID to update: ");
                    int updateId = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter new status (Available/Issued): ");
                    String newStatus = sc.nextLine();

                    if (!newStatus.equalsIgnoreCase("Available") && !newStatus.equalsIgnoreCase("Issued")) {
                        System.out.println("Invalid status.");
                    } else {
                        manager.updateBookStatus(updateId, newStatus);
                    }
                    break;

                case 4:
                    System.out.print("Enter book ID to delete: ");
                    int deleteId = sc.nextInt();
                    manager.deleteBook(deleteId);
                    break;

                case 5:
                    System.out.println("Exiting program.");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}

import java.util.Scanner;
public class Driver5 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Movie title: ");
        String movieTitle = sc.nextLine();

        System.out.print("Enter Movie ID: ");
        int movieId = sc.nextInt();

        System.out.print("Enter Movie late days: ");
        int movieLateDays = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Book title: ");
        String bookTitle = sc.nextLine();

        System.out.print("Enter Book ID: ");
        int bookId = sc.nextInt();

        System.out.print("Enter Book late days: ");
        int bookLateDays = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Game title: ");
        String gameTitle = sc.nextLine();

        System.out.print("Enter Game ID: ");
        int gameId = sc.nextInt();

        System.out.print("Enter Game late days: ");
        int gameLateDays = sc.nextInt();

        Media[] media = {
                new Movie(movieTitle, movieId, movieLateDays),
                new Book(bookTitle, bookId, bookLateDays),
                new Game(gameTitle, gameId, gameLateDays)
        };

        double totalFees = 0;

        for (Media item : media) {

            double fee = item.lateFee();

            System.out.println(
                    item.getClass().getSimpleName() +
                            " - " + item.title +
                            " Late Fee: " + fee
            );

            totalFees += fee;
        }

        System.out.println("Total Late Fees: " + totalFees);

        sc.close();
    }
}
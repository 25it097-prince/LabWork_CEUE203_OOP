import java.util.Scanner;

public class Driver {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter how many cards you display: ");
        int n = sc.nextInt();

        Card[] cards = new Card[n];

        boolean duplicate = false;
        for(int i = 0; i < n; i++){

            System.out.print("Enter name of the card: ");
            String rank = sc.next();
            String suit = sc.next();

            cards[i] = new Card(rank, suit);

            for(int j = 0; j < i; j++){
                if(cards[i].equals(cards[j])){
                    System.out.println("Duplicate found: " + cards[i]);
                    duplicate = true;
                    break;
                }
            }
            if(duplicate){
                break;
            }
        }
        if (!duplicate) {
            System.out.println("No duplicate cards found.");
        }
        sc.close();
    }
}

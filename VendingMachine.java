import java.util.Scanner;
public class VendingMachine {
    enum Coin{
        ONE,
        TWO,
        FIVE,
        TEN
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int p=15,t=0;
        while(t<p) {
            System.out.println("Enter coin (ONE, TWO, FIVE, TEN): ");
            Coin coin = Coin.valueOf(sc.next().toUpperCase());
            int v=0;
            switch (coin) {
                case ONE:
                    v = 1;
                    break;
                case TWO:
                    v = 2;
                    break;
                case FIVE:
                    v = 5;
                    break;
                case TEN:
                    v= 10;
                    break;
                default:
                    v = 0;
                    break;
            }
            t = t + v;
            System.out.println("Total so far: " + t);
        }
        System.out.println("Paid. Change: " + (t - p));
        sc.close();
    }
}

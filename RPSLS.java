import java.util.Random;
import java.util.Scanner;
public class RPSLS {

    enum Move { ROCK, PAPER, SCISSORS, LIZARD, SPOCK }
    static int winner(Move a, Move b) {
        if (a == b)
            return 0; // same move -> tie

        boolean aWins = false;

        switch (a) {
            case ROCK:
                aWins = (b == Move.LIZARD || b == Move.SCISSORS);
                break;

            case PAPER:
                aWins = (b == Move.ROCK || b == Move.SPOCK);
                break;

            case SCISSORS:
                aWins = (b == Move.PAPER || b == Move.LIZARD);
                break;

            case LIZARD:
                aWins = (b == Move.SPOCK || b == Move.PAPER);
                break;

            case SPOCK:
                aWins = (b == Move.SCISSORS || b == Move.ROCK);
                break;
        }

        if (aWins)
            return 1;
        else
            return -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rng = new Random();
        Move[] moves = Move.values();
        int you = 0, cpu = 0;

        System.out.println("Best of 5. Moves: ROCK PAPER SCISSORS LIZARD SPOCK");

        for (int round = 1; round <= 5; round++) {
            System.out.print("Round " + round + " - your move: ");
            Move mine;
            try {
                mine = Move.valueOf(sc.next().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("  Invalid move, round skipped.");
                continue;
            }
            Move theirs = moves[rng.nextInt(moves.length)]; 

            int r = winner(mine, theirs);
            System.out.print("  You: " + mine + " | Computer: " + theirs + " -> ");
            if (r == 1)
            {
                you++;
                System.out.println("you win the round");
            }
            else if (r == -1)
            {
                cpu++;
                System.out.println("computer wins the round");
            }
            else
            {
                System.out.println("tie");
            }
        }

        System.out.println("Final score - You " + you + " : " + cpu + " Computer");
        System.out.println(you > cpu ? "You win!" : you < cpu ? "Computer wins!" : "Overall tie!");
        sc.close();
    }
}
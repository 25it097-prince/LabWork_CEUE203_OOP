import java.util.Objects;

public class Card {
    private String rank;
    private String suit;

    public Card() {}

    public Card(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    @Override
    public String toString() {
        return (rank + " of " + suit);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass())
            return false;

        Card card = (Card) obj;
        return rank.equalsIgnoreCase(card.rank) && suit.equalsIgnoreCase(card.suit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, suit);
    }
}


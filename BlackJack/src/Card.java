public class Card {
    public enum Rank {
        ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING
    }

    public enum Suit {
        HEARTS, DIAMONDS, CLUBS, SPADES
    }
    private Rank rank;
    private Suit suit;
    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    // Getter methods
    public Rank getRank() {
        return rank;
    }

    public Suit getSuit() {
        return suit;
    }
    public int getValue() {
        return switch (rank) {
            case ACE -> 11;
            case TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN ->
                    rank.ordinal() + 1;
            case JACK, QUEEN, KING -> 10;
            default -> 0;
        };
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}

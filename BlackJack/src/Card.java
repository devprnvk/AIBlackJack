/**
 * Card class provides the implementation of a Class Object
 * @author Pranav Kale
 */
public class Card {
    /**
     * Enumeration for the rank of the card
     */
    public enum Rank {
        ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING
    }

    /**
     * Enumeration for the type of Card it is
     */
    public enum Suit {
        HEARTS, DIAMONDS, CLUBS, SPADES
    }

    /**
     * Keeping the ranking private
     */
    private Rank rank;
    /**
     * Keeping the suit private
     */
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

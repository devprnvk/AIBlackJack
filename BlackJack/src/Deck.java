import java.util.List;
import java.util.Collections;
import java.util.ArrayList;

public class Deck {

    private List<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        initializeDeck();
    }

    public void initializeDeck() {
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Rank rank : Card.Rank.values()) {
                cards.add(new Card(rank, suit));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card dealCard() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("Deck is empty, cannot deal a card.");
        }
        return cards.remove(0);
    }

    // Method to check if the deck is empty
    public boolean isEmpty() {
        return cards.isEmpty();
    }

    // Method to get the number of cards remaining in the deck
    public int size() {
        return cards.size();
    }
}

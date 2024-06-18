import java.util.ArrayList;
import java.util.List;

public class Hand {
    private List<Card> cards;

    // Constructor to initialize an empty hand
    public Hand() {
        cards = new ArrayList<>();
    }

    // Method to add a card to the hand
    public void addCard(Card card) {
        cards.add(card);
    }

    public void clear() {
        cards.clear();
    }

    public int getTotalValue() {
        int val = 0;
        int aceCount = 0;

        for (Card c : cards) {
            val += c.getValue();
            if (c.getRank() == Card.Rank.ACE) {
                aceCount++;
            }
        }
        while (val > 21 && aceCount > 0) {
            val -= 10;
            aceCount--;
        }
        return val;
    }

    public boolean hasBlackjack() {
        return cards.size() == 2 && getTotalValue() == 21;
    }

    // Method to check if the hand has busted (total value > 21)
    public boolean hasBusted() {
        return getTotalValue() > 21;
    }

    // Method to get the cards in the hand
    public List<Card> getCards() {
        return cards;
    }
}

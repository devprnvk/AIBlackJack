import java.util.ArrayList;
import java.util.List;

public class Hand {
    private List<Card> cards;

    public Hand() {
        cards = new ArrayList<>();
    }

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

    public boolean hasBusted() {
        return getTotalValue() > 21;
    }

    public List<Card> getCards() {
        return cards;
    }

    @Override
    public String toString() {
        return cards.toString();
    }
}

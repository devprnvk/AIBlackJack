import java.util.ArrayList;
import java.util.List;

public class Player {
    private List<Hand> hands;
    private int balance; // Player's balance (chips)

    public Player(int initialBalance) {
        this.balance = initialBalance;
        this.hands = new ArrayList<>();
    }

    public List<Hand> getHands() {
        return hands;
    }

    public void addHand(Hand hand) {
        hands.add(hand);
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}

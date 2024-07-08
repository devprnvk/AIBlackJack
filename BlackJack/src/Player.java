import java.util.ArrayList;
import java.util.List;

/**
 * This Class defines the Player and its data
 * Includes the List of Hands, Player Chip Balance, and Operations
 * @author Pranav Kale
 */
public class Player {
    /** Field to keep track of hands */
    private List<Hand> hands;
    private int balance;

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

    public void placeBet(int betAmount) {
        if (betAmount > balance) {
            throw new IllegalArgumentException("Bet amount exceeds current balance.");
        }
        balance -= betAmount;
    }

    public void winBet(int betAmount) {
        balance += betAmount;
    }

    public void loseBet(int betAmount) {

    }

    public void tieBet(int betAmount) {
        balance += betAmount;
    }
}

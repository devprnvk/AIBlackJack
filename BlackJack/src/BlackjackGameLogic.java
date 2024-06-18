import java.util.List;

/*
Method to initialize the game state,
including creating a new deck of cards,
shuffling the deck,
 dealing initial hands to the player and dealer.
 */
public class BlackjackGameLogic {
    private Deck deck;
    private Hand playerHand;
    private Hand dealerHand;
    private boolean playerTurn;

    public BlackjackGameLogic() {
        deck = new Deck();
        deck.shuffle();
        playerHand = new Hand();
        dealerHand = new Hand();
        playerTurn = true;

        dealInitialHands();
    }

    private void dealInitialHands() {
        playerHand.addCard(deck.dealCard());
        playerHand.addCard(deck.dealCard());

        dealerHand.addCard(deck.dealCard());
        dealerHand.addCard(deck.dealCard());
    }

    public void hit() {
        if (playerTurn) {
            playerHand.addCard(deck.dealCard());
        }
    }

    public void stand() {
        playerTurn = false;
        dealerTurn();
    }

    private void dealerTurn() {
        while (dealerHand.getTotalValue() < 17) {
            dealerHand.addCard(deck.dealCard());
        }
        determineWinner();
    }

    private void determineWinner() {
        int playerTotal = playerHand.getTotalValue();
        int dealerTotal = dealerHand.getTotalValue();

        if (playerHand.hasBlackjack() && !dealerHand.hasBlackjack()) {
            System.out.println("Player has blackjack!");
        } else if (dealerHand.hasBlackjack() && !playerHand.hasBlackjack()) {
            System.out.println("Dealer has blackjack!");
        } else if (playerHand.hasBusted()) {
            System.out.println("Player busted!");
        } else if (dealerHand.hasBusted()) {
            System.out.println("Dealer busted!");
        } else {
            if (playerTotal > dealerTotal) {
                System.out.println("Player wins with a total of " + playerTotal);
            } else if (dealerTotal > playerTotal) {
                System.out.println("Dealer wins with a total of " + dealerTotal);
            } else {
                System.out.println("It's a tie!");
            }
        }
    }

    public boolean isGameOver() {
      return false;
    }

    public boolean isPlayerTurn() {
        return playerTurn;
    }
    public void split() {
        if (playerTurn && canSplit(playerHand)) {
            Hand hand1 = new Hand();
            Hand hand2 = new Hand();
            Card card = playerHand.getCards().get(1); // Get the second card for splitting
            playerHand.getCards().remove(1);
            hand1.addCard(playerHand.getCards().get(0));
            hand2.addCard(card);
            hand1.addCard(deck.dealCard());
            hand2.addCard(deck.dealCard());
            playerHand = hand1;
        }
    }

    private boolean canSplit(Hand hand) {
        List<Card> cards = hand.getCards();
        return cards.size() == 2 && cards.get(0).getRank() == cards.get(1).getRank();
    }
}

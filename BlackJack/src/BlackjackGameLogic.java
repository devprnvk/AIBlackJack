import java.util.List;

/**
Method to initialize the game state,
including creating a new deck of cards,
shuffling the deck,
 dealing initial hands to the player and dealer.
 */
public class BlackjackGameLogic {
    private Deck deck;
    private Player player;
    private Hand dealerHand;
    private boolean playerTurn;

    public BlackjackGameLogic(Player player) {
        this.player = player;
        resetGame();
    }

    public void resetGame() {
        deck = new Deck();
        deck.shuffle();
        player.getHands().clear();
        player.addHand(new Hand());
        dealerHand = new Hand();
        playerTurn = true;
        dealInitialHands();
    }

    private void dealInitialHands() {
        player.getHands().get(0).addCard(deck.dealCard());
        player.getHands().get(0).addCard(deck.dealCard());

        dealerHand.addCard(deck.dealCard());
        dealerHand.addCard(deck.dealCard());
    }

    public void hit() {
        if (playerTurn) {
            player.getHands().get(0).addCard(deck.dealCard());
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
        int playerTotal = player.getHands().get(0).getTotalValue();
        int dealerTotal = dealerHand.getTotalValue();

        System.out.println("Player's hand: " + player.getHands().get(0) + " (Total: " + playerTotal + ")");
        System.out.println("Dealer's hand: " + dealerHand + " (Total: " + dealerTotal + ")");

        if (player.getHands().get(0).hasBlackjack() && !dealerHand.hasBlackjack()) {
            System.out.println("Player has blackjack!");
        } else if (dealerHand.hasBlackjack() && !player.getHands().get(0).hasBlackjack()) {
            System.out.println("Dealer has blackjack!");
        } else if (player.getHands().get(0).hasBusted()) {
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
        return !playerTurn;
    }

    public boolean isPlayerTurn() {
        return playerTurn;
    }

    public Hand getPlayerHand() {
        return player.getHands().get(0);
    }

    public Hand getDealerHand() {
        return dealerHand;
    }
}


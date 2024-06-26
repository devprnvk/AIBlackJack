import java.util.Scanner;
import UI.BlackjackWindow;

/**
 * AI BlackJack Starting Point of the Program
 * Provides Play method in the command line and GUI
 * @author Pranav Kale
 */
public class Main {
    /**
     * Main method for program
     * @param args arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Player player = new Player(1000);  // Initialize player with a balance
        GameStatistics stats = new GameStatistics();
        BlackjackGameLogic game = new BlackjackGameLogic(player);

        boolean playAgain = true;
        while (playAgain) {
            stats.incrementGamesPlayed();
            while (!game.isGameOver()) {
                System.out.println("Player's hand: " + game.getPlayerHand() + " (Total: " + game.getPlayerHand().getTotalValue() + ")");
                System.out.println("Dealer's hand: " + game.getDealerHand().getCards().get(0) + " and [Hidden]");
                System.out.print("Do you want to 'hit' or 'stand'? ");
                String action = scanner.nextLine();

                if (action.equalsIgnoreCase("hit")) {
                    game.hit();
                    if (game.getPlayerHand().hasBusted()) {
                        System.out.println("Player's hand: " + game.getPlayerHand() + " (Total: " + game.getPlayerHand().getTotalValue() + ")");
                        System.out.println("Player busted!");
                        stats.incrementGamesLost();
                        break;
                    }
                } else if (action.equalsIgnoreCase("stand")) {
                    game.stand();
                    break;
                } else {
                    System.out.println("Invalid action. Please choose 'hit' or 'stand'.");
                }
            }

            if (!game.isPlayerTurn()) {
                game.stand();
                if (game.getPlayerHand().getTotalValue() > game.getDealerHand().getTotalValue() && !game.getPlayerHand().hasBusted()) {
                    stats.incrementGamesWon();
                } else if (game.getDealerHand().getTotalValue() > game.getPlayerHand().getTotalValue() && !game.getDealerHand().hasBusted()) {
                    stats.incrementGamesLost();
                } else if (game.getPlayerHand().getTotalValue() == game.getDealerHand().getTotalValue()) {
                    stats.incrementGamesTied();
                }
            }

            System.out.println("Current Score: Games Played: " + stats.getGamesPlayed() + " | Wins: " + stats.getGamesWon() + " | Losses: " + stats.getGamesLost() + " | Ties: " + stats.getGamesTied());

            System.out.print("Do you want to play again? (yes/no): ");
            String playAgainInput = scanner.nextLine();
            playAgain = playAgainInput.equalsIgnoreCase("yes");

            if (playAgain) {
                game.resetGame();
            }
        }

        scanner.close();
        System.out.println("Final Score: Games Played: " + stats.getGamesPlayed() + " | Wins: " + stats.getGamesWon() + " | Losses: " + stats.getGamesLost() + " | Ties: " + stats.getGamesTied());
        System.out.println("Win Percentage: " + stats.getWinPercentage() + "%");
    }
}
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
        Player player = null;
        AIPlayer aiPlayer = null;
        GameStatistics stats = new GameStatistics();
        BlackjackGameLogic game = null;

        System.out.println("Welcome to AI Blackjack!");
        System.out.print("Choose game mode: Easy or AI? ");
        String gameMode = scanner.nextLine().trim().toLowerCase();

        if (gameMode.equals("ai")) {
            aiPlayer = new AIPlayer(1000);
            game = new BlackjackGameLogic(aiPlayer);
        } else {
            player = new Player(1000);
            game = new BlackjackGameLogic(player);
        }


        boolean playAgain = true;
        while (playAgain && (gameMode.equals("easy") ? player.getBalance() > 0 : aiPlayer.getBalance() > 0)) {
            stats.incrementGamesPlayed();

            System.out.println("Current Balance: " + (gameMode.equals("easy") ? player.getBalance() : aiPlayer.getBalance()) + " chips");
            int betAmount = 0;
            while (true) {
                try {
                    System.out.print("Place your bet: ");
                    betAmount = Integer.parseInt(scanner.nextLine());
                    if (gameMode.equals("easy")) {
                        player.placeBet(betAmount);
                    } else {
                        aiPlayer.placeBet(betAmount);
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }

            while (!game.isGameOver()) {
                System.out.println("Player's hand: " + game.getPlayerHand() + " (Total: " + game.getPlayerHand().getTotalValue() + ")");
                System.out.println("Dealer's hand: " + game.getDealerHand().getCards().get(0) + " and [Hidden]");

                boolean validAction = false;
                while (!validAction) {
                    System.out.print("Do you want to 'hit' or 'stand'? ");
                    String action = scanner.nextLine();

                    if (action.equalsIgnoreCase("hit")) {
                        validAction = true;
                        game.hit();
                        if (game.getPlayerHand().hasBusted()) {
                            System.out.println("Player's hand: " + game.getPlayerHand() + " (Total: " + game.getPlayerHand().getTotalValue() + ")");
                            System.out.println("Player busted!");
                            if (gameMode.equals("easy")) {
                                player.loseBet(betAmount);
                            } else {
                                aiPlayer.loseBet(betAmount);
                            }
                            stats.incrementGamesLost();
                            break;
                        }
                    } else if (action.equalsIgnoreCase("stand")) {
                        validAction = true;
                        game.stand();
                        break;
                    } else {
                        System.out.println("Invalid action. Please choose 'hit' or 'stand'.");
                    }
                }
            }

            if (!game.isPlayerTurn()) {
                game.stand();
                int playerTotal = game.getPlayerHand().getTotalValue();
                int dealerTotal = game.getDealerHand().getTotalValue();

                if (playerTotal > dealerTotal && !game.getPlayerHand().hasBusted() || game.getDealerHand().hasBusted()) {
                    if (gameMode.equals("easy")) {
                        player.winBet(2 * betAmount);
                    } else {
                        aiPlayer.winBet(2 * betAmount);
                    }
                    stats.incrementGamesWon();
                } else if (dealerTotal > playerTotal && !game.getDealerHand().hasBusted()) {
                    if (gameMode.equals("easy")) {
                        player.loseBet(betAmount);
                    } else {
                        aiPlayer.loseBet(betAmount);
                    }
                    stats.incrementGamesLost();
                } else {
                    if (gameMode.equals("easy")) {
                        player.tieBet(betAmount);
                    } else {
                        aiPlayer.tieBet(betAmount);
                    }
                    stats.incrementGamesTied();
                }
            }

            System.out.println("Current Score: Games Played: " + stats.getGamesPlayed() + " | Wins: " + stats.getGamesWon() + " | Losses: " + stats.getGamesLost() + " | Ties: " + stats.getGamesTied());
            System.out.println("Current Balance: " + (gameMode.equals("easy") ? player.getBalance() : aiPlayer.getBalance()) + " chips");

            if ((gameMode.equals("easy") ? player.getBalance() : aiPlayer.getBalance()) <= 0) {
                System.out.println("You are out of chips!");
                break;
            }

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
        System.out.println("Final Balance: " + (gameMode.equals("easy") ? player.getBalance() : aiPlayer.getBalance()) + " chips");
    }
}
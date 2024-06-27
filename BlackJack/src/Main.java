import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        BlackjackWindow blackjackWindow = new BlackjackWindow();
        Scanner scanner = new Scanner(System.in);
        BlackjackGameLogic game = new BlackjackGameLogic();
        int playerWins = 0;
        int dealerWins = 0;

        boolean playAgain = true;
        while (playAgain) {
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
                        dealerWins++;
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
                    playerWins++;
                } else if (game.getDealerHand().getTotalValue() > game.getPlayerHand().getTotalValue() && !game.getDealerHand().hasBusted()) {
                    dealerWins++;
                }
            }

            System.out.println("Current Score: Player Wins: " + playerWins + " | Dealer Wins: " + dealerWins);

            System.out.print("Do you want to play again? (yes/no): ");
            String playAgainInput = scanner.nextLine();
            playAgain = playAgainInput.equalsIgnoreCase("yes");

            if (playAgain) {
                game.resetGame();
            }
        }

        scanner.close();
        System.out.println("Final Score: Player Wins: " + playerWins + " | Dealer Wins: " + dealerWins);
    }
}
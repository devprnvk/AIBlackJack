/**
 * This Class keeps track of all the Statistics in the Game
 * Includes Games Played, Won, Lost, and Tied
 * @author Pranav Kale
 */
public class GameStatistics {
    private int gamesPlayed;
    private int gamesWon;
    private int gamesLost;
    private int gamesTied;

    public GameStatistics() {

        resetStatistics();

    }

    public void resetStatistics() {
        gamesPlayed = 0;
        gamesWon = 0;
        gamesLost = 0;
        gamesTied = 0;
    }

    public void incrementGamesPlayed() {
        gamesPlayed++;
    }

    public void incrementGamesWon() {
        gamesWon++;
    }

    public void incrementGamesLost() {
        gamesLost++;
    }

    public void incrementGamesTied() {
        gamesTied++;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public int getGamesLost() {
        return gamesLost;
    }

    public int getGamesTied() {
        return gamesTied;
    }

    public double getWinPercentage() {
        if (gamesPlayed == 0) {
            return 0.0;
        }
        return (double) gamesWon / gamesPlayed * 100.0;
    }
}

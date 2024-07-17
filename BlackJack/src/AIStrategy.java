import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AIStrategy {
    public static String getAction(int playerHand, int dealerCard) {
        try {
            String[] cmd = {
                    "python3",
                    "predict_action.py",
                    String.valueOf(playerHand),
                    String.valueOf(dealerCard)
            };
            Process p = Runtime.getRuntime().exec(cmd);
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            return in.readLine();
        } catch (Exception e) {
            e.printStackTrace();
            return "stand";  // default action in case of error
        }
    }
}

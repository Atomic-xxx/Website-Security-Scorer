import checks.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        String url = "https://google.com";

        List<SecurityCheck> checks = new ArrayList<>();

        checks.add(new HttpsCheck());
        checks.add(new DomainCheck());

        int totalScore = 0;

        for (SecurityCheck check : checks) {
            int score = check.performCheck(url);
            System.out.println(check.getCheckName() + ": " + score);
            totalScore += score;
        }

        System.out.println("Total Score: " + totalScore);
    }
}
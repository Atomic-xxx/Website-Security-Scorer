package service;

import checks.*;
import java.util.*;

public class SecurityAnalyzer {

    private List<SecurityCheck> checks;

    public SecurityAnalyzer() {
        checks = new ArrayList<>();

        checks.add(new HttpsCheck());
        checks.add(new DomainCheck());
    }

    public int analyze(String url) {

        int totalScore = 0;

        for (SecurityCheck check : checks) {
            int score = check.performCheck(url);
            System.out.println(check.getCheckName() + ": " + score);
            totalScore += score;
        }

        return totalScore;
    }
}
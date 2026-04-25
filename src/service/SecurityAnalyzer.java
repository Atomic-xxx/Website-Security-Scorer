package service;

import checks.*;
import model.ScanResult;
import java.util.*;

public class SecurityAnalyzer {

    private List<SecurityCheck> checks;

    public SecurityAnalyzer() {
        checks = new ArrayList<>();

        checks.add(new HttpsCheck());
        checks.add(new DomainCheck());
        checks.add(new SSLCheck());
        checks.add(new IPCheck());
        checks.add(new NetworkCheck());
    }

    public ScanResult analyze(String url) {

        int totalScore = 0;
        Map<String, Integer> breakdown = new HashMap<>();

        for (SecurityCheck check : checks) {
            int score = check.performCheck(url);
            breakdown.put(check.getCheckName(), score);
            System.out.println(check.getCheckName() + ": " + score);
            totalScore += score;
        }

        return new ScanResult(url, totalScore, breakdown);
    }
}
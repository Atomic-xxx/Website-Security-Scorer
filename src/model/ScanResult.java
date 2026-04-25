package model;

import java.util.Map;

public class ScanResult {

    private String url;
    private int totalScore;
    private Map<String, Integer> breakdown;

    public ScanResult(String url, int totalScore, Map<String, Integer> breakdown) {
        this.url = url;
        this.totalScore = totalScore;
        this.breakdown = breakdown;
    }

    public String getUrl() {
        return url;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public Map<String, Integer> getBreakdown() {
        return breakdown;
    }
}
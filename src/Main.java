import service.SecurityAnalyzer;

public class Main {
    public static void main(String[] args) {

        String url = "https://google.com";

        SecurityAnalyzer analyzer = new SecurityAnalyzer();

        int totalScore = analyzer.analyze(url);

        System.out.println("Total Score: " + totalScore);
    }
}
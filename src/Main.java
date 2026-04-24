import checks.HttpsCheck;

public class Main {
    public static void main(String[] args) {

        String url = "https://google.com";

        HttpsCheck check = new HttpsCheck();

        int score = check.performCheck(url);

        System.out.println("Check: " + check.getCheckName());
        System.out.println("Score: " + score);
    }
}
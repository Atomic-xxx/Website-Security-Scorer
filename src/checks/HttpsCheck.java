package checks;

public class HttpsCheck implements SecurityCheck {

    @Override
    public int performCheck(String url) {
        if (url.startsWith("https://")) {
            return 30;
        }
        return 0;
    }

    @Override
    public String getCheckName() {
        return "HTTPS Check";
    }
}
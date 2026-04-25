package checks;

public class DomainCheck implements SecurityCheck {

    @Override
    public int performCheck(String url) {
        if (url.contains(".")) {
            return 15;
        }
        return 0;
    }

    @Override
    public String getCheckName() {
        return "Domain Legitimacy Check";
    }
}
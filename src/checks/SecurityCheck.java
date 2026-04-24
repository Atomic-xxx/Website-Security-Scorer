package checks;

public interface SecurityCheck {
    int performCheck(String url);
    String getCheckName();
}
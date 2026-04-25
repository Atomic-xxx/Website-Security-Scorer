package checks;

import java.net.InetAddress;

public class IPCheck implements SecurityCheck {

    @Override
    public int performCheck(String url) {
        try {
            InetAddress ip = InetAddress.getByName(new java.net.URL(url).getHost());
            return 10;
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public String getCheckName() {
        return "IP Check";
    }
}
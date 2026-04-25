package checks;

import java.net.InetAddress;

public class NetworkCheck implements SecurityCheck {

    @Override
    public int performCheck(String url) {
        try {
            InetAddress ip = InetAddress.getByName(new java.net.URL(url).getHost());
            if (ip.isReachable(2000)) {
                return 20;
            }
        } catch (Exception e) {}

        return 0;
    }

    @Override
    public String getCheckName() {
        return "Network Reachability";
    }
}
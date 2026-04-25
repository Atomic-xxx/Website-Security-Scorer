package checks;

import javax.net.ssl.HttpsURLConnection;
import java.net.URL;

public class SSLCheck implements SecurityCheck {

    @Override
    public int performCheck(String url) {
        try {
            URL siteURL = new URL(url);
            HttpsURLConnection conn = (HttpsURLConnection) siteURL.openConnection();
            conn.connect();
            return 20;
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public String getCheckName() {
        return "SSL Certificate Check";
    }
}
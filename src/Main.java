import service.SecurityAnalyzer;
import model.ScanResult;
import database.ResultDAO;

public class Main {
    public static void main(String[] args) {

        String url = "https://google.com";

        SecurityAnalyzer analyzer = new SecurityAnalyzer();

        ScanResult result = analyzer.analyze(url);

        System.out.println("Total Score: " + result.getTotalScore());
        
         ResultDAO dao = new ResultDAO();
         dao.saveResult(result);
    }
}
package database;

import model.ScanResult;
import java.sql.*;

public class ResultDAO {

    public void saveResult(ScanResult result) {

        String query = "INSERT INTO results (url, https_score, ssl_score, domain_score, ip_score, network_score, total_score) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, result.getUrl());
            stmt.setInt(2, result.getBreakdown().get("HTTPS Check"));
            stmt.setInt(3, result.getBreakdown().get("SSL Certificate Check"));
            stmt.setInt(4, result.getBreakdown().get("Domain Legitimacy Check"));
            stmt.setInt(5, result.getBreakdown().get("IP Check"));
            stmt.setInt(6, result.getBreakdown().get("Network Reachability"));
            stmt.setInt(7, result.getTotalScore());

            stmt.executeUpdate();

            System.out.println("Result saved to database!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
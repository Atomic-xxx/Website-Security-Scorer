package database;

import model.ScanResult;
import java.sql.*;

public class ResultDAO {

    public void saveResult(ScanResult result) {

        String query = "INSERT INTO results (url, total_score) VALUES (?, ?)";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, result.getUrl());
            stmt.setInt(2, result.getTotalScore());

            stmt.executeUpdate();

            System.out.println("Result saved to database!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
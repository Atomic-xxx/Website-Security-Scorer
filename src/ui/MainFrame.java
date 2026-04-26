package ui;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

import service.SecurityAnalyzer;
import model.ScanResult;
import database.ResultDAO;

public class MainFrame extends JFrame {

    private JTextField urlField;
    private JTextArea resultArea;
    private JLabel scoreLabel;
    private JPanel progressFill;

    public MainFrame() {

        setTitle("Website Security Scorer");
        setSize(900, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(30, 35, 45));

        // Title
        JLabel title = new JLabel("Website Security Scorer");
        title.setBounds(250, 25, 500, 40);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 30));
        panel.add(title);

        // Subtitle
        JLabel subtitle = new JLabel("Check if a website looks secure and trustworthy");
        subtitle.setBounds(200,65, 500, 25);
        subtitle.setForeground(new Color(190,190,190));
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        panel.add(subtitle);

        title.setHorizontalAlignment(SwingConstants.CENTER);
        subtitle.setHorizontalAlignment(SwingConstants.CENTER);

        // URL Label
        JLabel urlLabel = new JLabel("Enter URL:");
        urlLabel.setBounds(200, 120, 100, 25);
        urlLabel.setForeground(Color.WHITE);
        panel.add(urlLabel);

        // URL Field
        urlField = new JTextField();
        urlField.setBounds(300, 120, 250, 25);
        panel.add(urlField);

        // Buttons
        JButton scanBtn = new JButton("Scan");
        scanBtn.setBounds(570, 120, 80, 25);
        panel.add(scanBtn);

        JButton saveBtn = new JButton("Save");
        saveBtn.setBounds(660, 120, 80, 25);
        panel.add(saveBtn);

        // Result Area
        resultArea = new JTextArea();
        resultArea.setFont(new Font("Segoe UI", Font.BOLD, 14));
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setBounds(200, 170, 500, 250);
        panel.add(scrollPane);

        // Score Label
        scoreLabel = new JLabel("Total Score: 0");
        scoreLabel.setBounds(200, 440, 200, 25);
        scoreLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        scoreLabel.setForeground(Color.WHITE);
        panel.add(scoreLabel);

        // Progress Bar
        JPanel progressContainer = new JPanel();
        progressContainer.setBounds(200, 470, 500, 25);
        progressContainer.setBackground(Color.DARK_GRAY);
        progressContainer.setLayout(null);

        progressFill = new JPanel();
        progressFill.setBounds(0, 0, 0, 25); // initially empty
        progressFill.setBackground(Color.GREEN);

        progressContainer.add(progressFill);
        panel.add(progressContainer);

        // Add panel to frame
        add(panel);

        // Actions
        scanBtn.addActionListener(e -> scan());
        saveBtn.addActionListener(e -> save());
    }
    private void scan() {
        try{
        String url = urlField.getText().trim();

        if (url.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a URL");
            return;
        }

        SecurityAnalyzer analyzer = new SecurityAnalyzer();
        ScanResult result = analyzer.analyze(url);

        StringBuilder output = new StringBuilder();

        for (String key : result.getBreakdown().keySet()) {
            output.append(key)
                  .append(": ")
                  .append(result.getBreakdown().get(key))
                  .append("\n");
        }

        resultArea.setText("SCAN RESULTS:\n\n" + output.toString());
        int score = result.getTotalScore();
        scoreLabel.setText("TOTAL SCORE: " + score);

        int safeScore = Math.min(score, 100);
        int width = (safeScore * 500) / 100;

        progressFill.setBounds(0, 0, width, 25);

        if (score >= 80) {
            progressFill.setBackground(Color.GREEN);
        } else if (score >= 50) {
            progressFill.setBackground(new Color(255, 140, 0));
        } else {
            progressFill.setBackground(Color.RED);
        }

        progressFill.revalidate();
        progressFill.repaint();
        }
    catch (Exception e) {
    JOptionPane.showMessageDialog(this, "Invalid URL or error occurred");
   }
    
    }

    private void save() {
        String url = urlField.getText().trim();

        SecurityAnalyzer analyzer = new SecurityAnalyzer();
        ScanResult result = analyzer.analyze(url);

        ResultDAO dao = new ResultDAO();
        dao.saveResult(result);

        JOptionPane.showMessageDialog(this, "Saved to database!");
    }
}
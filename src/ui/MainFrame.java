package ui;

import javax.swing.*;
import java.awt.*;

import service.SecurityAnalyzer;
import model.ScanResult;
import database.ResultDAO;

public class MainFrame extends JFrame {

    private JTextField urlField;
    private JTextArea resultArea;
    private JLabel scoreLabel;
    private JProgressBar progressBar;

    public MainFrame() {

        setTitle("Website Security Scorer");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        progressBar = new JProgressBar(0, 100);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);


        // Top
        JPanel top = new JPanel();
        urlField = new JTextField(25);
        JButton scanBtn = new JButton("Scan");
        JButton saveBtn = new JButton("Save");

        top.add(new JLabel("Enter URL:"));
        top.add(urlField);
        top.add(scanBtn);
        top.add(saveBtn);

        // Center
        resultArea = new JTextArea();
        resultArea.setEditable(false);

        // Bottom
        scoreLabel = new JLabel("Total Score: 0");

        progressBar = new JProgressBar(0, 100);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);

        JPanel bottom = new JPanel(new GridLayout(2, 1));
        bottom.add(scoreLabel);
        bottom.add(progressBar);

        add(top, BorderLayout.NORTH);
        add(new JScrollPane(resultArea), BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);

        // Actions
        scanBtn.addActionListener(e -> scan());
        saveBtn.addActionListener(e -> save());
    }

    private void scan() {
        String url = urlField.getText().trim();

        SecurityAnalyzer analyzer = new SecurityAnalyzer();
        ScanResult result = analyzer.analyze(url);

        StringBuilder output = new StringBuilder();

        for (String key : result.getBreakdown().keySet()) {
            output.append(key)
                  .append(": ")
                  .append(result.getBreakdown().get(key))
                  .append("\n");
        }

        resultArea.setText(output.toString());
        scoreLabel.setText("Total Score: " + result.getTotalScore());
        progressBar.setValue(result.getTotalScore());
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
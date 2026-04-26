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
    private JProgressBar progressBar;

    public MainFrame() {

        setSize(900, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        progressBar = new JProgressBar(0, 100);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(30, 35, 45));
        JLabel title = new JLabel("Website Security Scorer");
        title.setBounds(250, 25, 500, 40);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 30));
        panel.add(title);
        JLabel subtitle = new JLabel("Check if a website looks secure and trustworthy");
        subtitle.setBounds(245, 65, 450, 25);
        subtitle.setForeground(new Color(190,190,190));
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        panel.add(subtitle);


        // Top
        setTitle("Website Security Scorer");
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

       // add(top, BorderLayout.NORTH);
        //add(new JScrollPane(resultArea), BorderLayout.CENTER);
        //add(bottom, BorderLayout.SOUTH);
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

        resultArea.setText("Scan Results:\n\n" + output.toString());
        scoreLabel.setText("Total Score: " + result.getTotalScore());
        progressBar.setValue(result.getTotalScore());
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
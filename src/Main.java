import ui.MainFrame;

public class Main {
    public static void main(String[] args) {
        try {
    UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
    } 
    catch (Exception e) {
        e.printStackTrace();
    }
        new MainFrame().setVisible(true);
    }
}
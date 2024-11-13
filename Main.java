import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
      
        JFrame window = new JFrame("A* Pathfinding");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.add(new DemoPanel());
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}
import javax.swing.*;
import java.awt.*;

/**
 * Created by Kirana on 18/05/2017.
 */
public class Game extends JFrame{
    public Game() {
        this.setTitle("Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 600);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setLayout(null);

        JLabel labellife = new JLabel();
        labellife.setBounds(50, 50, 600, 300);
        this.add(labellife);

        JPanel panelfill = new JPanel();
        panelfill.setBounds(50, 300, 490, 50);
        panelfill.setBackground(Color.BLUE);
        this.add(panelfill);

        JPanel panelkey= new JPanel();
        panelkey.setBounds(50, 360, 490, 200);
        panelkey.setBackground(Color.RED);
        this.add(panelkey);

        this.setVisible(true);
    }

    public static void main(String[] args) {
        new Game();
    }
}

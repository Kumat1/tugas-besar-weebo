import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Kirana on 17/05/2017.
 */
public class Hangman extends JFrame {
    public Hangman() {
        this.setTitle("Hangman");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.setLayout(null);

        ImageIcon img = new ImageIcon("img/logo.png");
        Image image = img.getImage();

        BufferedImage bi = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics g = bi.createGraphics();
        g.drawImage(image, 0, 0, 250, 250, null);
        ImageIcon newIcon = new ImageIcon(bi);

        JLabel imgLabel = new JLabel();
        imgLabel.setPreferredSize(new Dimension(250, 250));
        imgLabel.setIcon(newIcon);
        imgLabel.setLocation(50, 50);
        this.add(imgLabel);

        JComboBox combo = new JComboBox();
        combo.addItem("Sports");
        combo.addItem("Foods");
        combo.addItem("Animal");
        combo.addItem("Buildings");
        combo.addItem("Countries");
        combo.setBounds(180, 290, 100, 25);
        this.add(combo);

        JButton button = new JButton("Start");
        button.setBounds(180, 330, 100, 25);
        this.add(button);

        this.setVisible(true);
    }
}

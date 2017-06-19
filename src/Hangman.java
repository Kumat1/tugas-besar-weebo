import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Personal PC on 15/06/2017.
 */
public class Hangman extends JFrame {
    public JComboBox combo;
    public String scombo;

    private ActionListener actionStart = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hangman", "root", "");

                scombo = (String) combo.getSelectedItem();
                String sql = "SELECT * FROM kategori WHERE kategori = '"+ scombo +"'";
                Statement stat = connection.createStatement();
                ResultSet rse = stat.executeQuery(sql);

                ArrayList<String> list = new ArrayList<String>();
                while(rse.next()) {
                    list.add(rse.getString("kata"));
                }

                //random angka
                Random rand = new Random();
                int n = rand.nextInt(list.size());

                String kataTerpilih = list.get(n).toUpperCase();

                new Game(kataTerpilih);

            } catch (SQLException e1) {
                e1.printStackTrace();
            }

            Hangman.this.setVisible(false);
        }
    };

    public Hangman() {
        this.setTitle("Hangman");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 600);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setLayout(null);

        ImageIcon img = new ImageIcon("img/logo.png");
        Image image = img.getImage().getScaledInstance(250, 350, Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(image);

        JLabel imgLabel = new JLabel(newIcon);
        imgLabel.setBounds(165, 50, 250, 350);
        this.add(imgLabel);

        combo = new JComboBox();
        combo.addItem("Sports");
        combo.addItem("Foods");
        combo.addItem("Animals");
        combo.addItem("Buildings");
        combo.addItem("Countries");

        combo.setBounds(220, 420, 150, 35);
        this.add(combo);

        JButton button = new JButton("Start");
        button.setBounds(220, 470, 150, 35);
        button.addActionListener(actionStart);
        this.add(button);

        this.setVisible(true);
    }
}
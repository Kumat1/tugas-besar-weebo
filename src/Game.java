import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Objects;

import static com.sun.org.apache.xml.internal.security.keys.keyresolver.KeyResolver.length;

/**
 * Created by Personal PC on 15/06/2017.
 */
public class Game extends JFrame {
    private String kataTebakan;
    private JLabel letters[];
    private boolean[] tertebak;
    private int nyawa;
    private JLabel imgLabel, imgLabel0, imgLabel1, imgLabel2, imgLabel3, imgLabel4;

    private ActionListener actionKey = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            char pressedChar = command.charAt(0);

            boolean tebakanBenar = false;

            for(int i=0; i<kataTebakan.length(); i++) {
                if(pressedChar == kataTebakan.charAt(i)) {
                    letters[i].setText(command);
                    tertebak[i] = true;

                    tebakanBenar = true;
                }
            }

            //nyawa berkurang
            if (!tebakanBenar) {
                nyawa--;
                System.out.println("Sisa nyawa: " + nyawa);

                //tampilkan gambar berdasarkan sisa nyawa
                if(nyawa == 5) {
                    imgLabel2.setVisible(true);
                }
                if(nyawa == 4) {
                    imgLabel1.setVisible(true);
                }
                if(nyawa == 3) {
                    imgLabel3.setVisible(true);
                }
                if(nyawa == 2) {
                    imgLabel4.setVisible(true);
                }
                if (nyawa == 1) {
                    imgLabel.setVisible(true);
                }
                if(nyawa == 0) {
                    imgLabel0.setVisible(true);
                    JOptionPane.showMessageDialog(Game.this, "Game Over");
                    new Hangman();
                    Game.this.setVisible(false);
                }
            }

            //cek menang
            boolean hasWin = true;
            for (int i=0;i<kataTebakan.length();i++) {
                if (!tertebak[i]) {
                    hasWin = false;
                    break;
                }
            }

            if (hasWin) {
                JOptionPane.showMessageDialog(Game.this, "Win");
                new Hangman();
                Game.this.setVisible(false);
            } else {
                System.out.println("again");
            }

            JButton tombol = (JButton) e.getSource();
            tombol.setEnabled(false);
        }
    };

    public Game(String kata) {
        this.setTitle("Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 600);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setLayout(null);

        this.kataTebakan = kata;

        //set jumlah nyawa
        nyawa = 6;

        //panel nyawa
        JPanel panellife = new JPanel();
        panellife.setBounds(3, 3, 587, 300);
        panellife.setBackground(Color.WHITE);
        panellife.setLayout(null);
        this.add(panellife);

        //gambar Hangman
        ImageIcon img0 = new ImageIcon("img/logo.png");
        Image image0 = img0.getImage().getScaledInstance(115,170, Image.SCALE_SMOOTH);
        ImageIcon newIcon0 = new ImageIcon(image0);

        imgLabel0 = new JLabel(newIcon0);
        imgLabel0.setBounds(297,75,115,170);
        panellife.add(imgLabel0);

        ImageIcon img = new ImageIcon("img/head.png");
        Image image = img.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(image);

        imgLabel = new JLabel(newIcon);
        imgLabel.setBounds(315, 90, 80, 80);
        panellife.add(imgLabel);

        ImageIcon img1 = new ImageIcon("img/1.png");
        Image image1 = img1.getImage().getScaledInstance(150,255, Image.SCALE_SMOOTH);
        ImageIcon newIcon1 = new ImageIcon(image1);

        imgLabel1 = new JLabel(newIcon1);
        imgLabel1.setBounds(150,30,150,255);
        panellife.add(imgLabel1);

        ImageIcon img2 = new ImageIcon("img/2.png");
        Image image2 = img2.getImage().getScaledInstance(350,170, Image.SCALE_SMOOTH);
        ImageIcon newIcon2 = new ImageIcon(image2);

        imgLabel2 = new JLabel(newIcon2);
        imgLabel2.setBounds(125,140,350,170);
        panellife.add(imgLabel2);

        ImageIcon img3 = new ImageIcon("img/3.png");
        Image image3 = img3.getImage().getScaledInstance(245,135, Image.SCALE_SMOOTH);
        ImageIcon newIcon3 = new ImageIcon(image3);

        imgLabel3 = new JLabel(newIcon3);
        imgLabel3.setBounds(185,45,245,135);
        panellife.add(imgLabel3);

        ImageIcon img4 = new ImageIcon("img/4.png");
        Image image4 = img4.getImage().getScaledInstance(100,100, Image.SCALE_SMOOTH);
        ImageIcon newIcon4 = new ImageIcon(image4);

        imgLabel4 = new JLabel(newIcon4);
        imgLabel4.setBounds(300,65,100,100);
        panellife.add(imgLabel4);

        //panel tebakan
        JPanel panelfill = new JPanel();
        panelfill.setBounds(30, 315, 535, 45);
        panelfill.setBackground(Color.LIGHT_GRAY);
        panelfill.setLayout(null);
        this.add(panelfill);

        int panjang = kataTebakan.length();
        letters = new JLabel[panjang];
        int titikAwalX = 100;
        int titikAwalY = 0;
        for(int i=0; i<panjang; i++) {
            letters[i] = new JLabel("___");
            letters[i].setBounds(titikAwalX + (50*i), titikAwalY + 3, 50,40);
            panelfill.add(letters[i]);
        }

        tertebak = new boolean[panjang];
        for(int i=0; i<panjang; i++) {
            tertebak[i] = false;
        }

        //panel keyboard
        JPanel panelkey = new JPanel();
        panelkey.setBounds(30, 370, 535, 150);
        panelkey.setBackground(Color.PINK);
        panelkey.setLayout(null);

        JButton q = new JButton("Q");
        q.setBounds(3,3,50,40);
        q.addActionListener(actionKey);
        q.setActionCommand("Q");
        JButton w = new JButton("W");
        w.setBounds(56,3,50,40);
        w.addActionListener(actionKey);
        w.setActionCommand("W");
        JButton e = new JButton("E");
        e.setBounds(109,3,50,40);
        e.addActionListener(actionKey);
        e.setActionCommand("E");
        JButton r = new JButton("R");
        r.setBounds(162,3,50,40);
        r.addActionListener(actionKey);
        JButton t = new JButton("T");
        t.setBounds(215,3,50,40);
        t.addActionListener(actionKey);
        JButton y = new JButton("Y");
        y.setBounds(268,3,50,40);
        y.addActionListener(actionKey);
        JButton u = new JButton("U");
        u.setBounds(321,3,50,40);
        u.addActionListener(actionKey);
        JButton i = new JButton("I");
        i.setBounds(374,3,50,40);
        i.addActionListener(actionKey);
        JButton o = new JButton("O");
        o.setBounds(427,3,50,40);
        o.addActionListener(actionKey);
        JButton p = new JButton("P");
        p.setBounds(480,3,50,40);
        p.addActionListener(actionKey);
        JButton a = new JButton("A");
        a.setBounds(23, 46, 50, 40);
        a.addActionListener(actionKey);
        JButton s = new JButton("S");
        s.setBounds(76, 46, 50, 40);
        s.addActionListener(actionKey);
        JButton d = new JButton("D");
        d.setBounds(129, 46, 50, 40);
        d.addActionListener(actionKey);
        JButton f = new JButton("F");
        f.setBounds(182, 46, 50, 40);
        f.addActionListener(actionKey);
        JButton g = new JButton("G");
        g.setBounds(235, 46, 50, 40);
        g.addActionListener(actionKey);
        JButton h = new JButton("H");
        h.setBounds(288, 46, 50, 40);
        h.addActionListener(actionKey);
        JButton j = new JButton("J");
        j.setBounds(341, 46, 50, 40);
        j.addActionListener(actionKey);
        JButton k = new JButton("K");
        k.setBounds(394, 46, 50, 40);
        k.addActionListener(actionKey);
        JButton l = new JButton("L");
        l.setBounds(447, 46, 50, 40);
        l.addActionListener(actionKey);
        JButton z = new JButton("Z");
        z.setBounds(76, 89, 50, 40);
        z.addActionListener(actionKey);
        JButton x = new JButton("X");
        x.setBounds(129, 89, 50, 40);
        x.addActionListener(actionKey);
        JButton c = new JButton("C");
        c.setBounds(182, 89, 50, 40);
        c.addActionListener(actionKey);
        JButton v = new JButton("V");
        v.setBounds(235, 89, 50, 40);
        v.addActionListener(actionKey);
        JButton b = new JButton("B");
        b.setBounds(288, 89, 50, 40);
        b.addActionListener(actionKey);
        JButton n = new JButton("N");
        n.setBounds(341, 89, 50, 40);
        n.addActionListener(actionKey);
        JButton m = new JButton("M");
        m.setBounds(394, 89, 50, 40);
        m.addActionListener(actionKey);

        panelkey.add(q);
        panelkey.add(w);
        panelkey.add(e);
        panelkey.add(r);
        panelkey.add(t);
        panelkey.add(y);
        panelkey.add(u);
        panelkey.add(i);
        panelkey.add(o);
        panelkey.add(p);
        panelkey.add(a);
        panelkey.add(s);
        panelkey.add(d);
        panelkey.add(f);
        panelkey.add(g);
        panelkey.add(h);
        panelkey.add(j);
        panelkey.add(k);
        panelkey.add(l);
        panelkey.add(z);
        panelkey.add(x);
        panelkey.add(c);
        panelkey.add(v);
        panelkey.add(b);
        panelkey.add(n);
        panelkey.add(m);
        this.add(panelkey);

        this.setVisible(true);
        imgLabel0.setVisible(false);
        imgLabel.setVisible(false);
        imgLabel1.setVisible(false);
        imgLabel2.setVisible(false);
        imgLabel3.setVisible(false);
        imgLabel4.setVisible(false);
    }
}

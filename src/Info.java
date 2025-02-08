//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//



import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Info extends JPanel {
    Icon logo = new ImageIcon(this.getClass().getResource("/logo.png"));

    public Info(final JFrame jFrame, int delay) {
        final DrawPanel drawPanel = new DrawPanel(jFrame);
        this.setSize(drawPanel.getSize());
        this.setBackground(drawPanel.getBackground());
        this.setLayout(new BoxLayout(this, 1));
        JLabel[] title = new JLabel[]{new JLabel(this.logo), new JLabel("OOM MINI PROJECT"), new JLabel("Polygon Area Calculator")};


        int delayTime;
        for(delayTime = 0; delayTime < 3; ++delayTime) {
            title[delayTime].setAlignmentX(0.5F);
            title[delayTime].setAlignmentY(0.5F);
            title[delayTime].setFont(new Font("Century Gothic", 2, 80));
            title[delayTime].setForeground(new Color(195, 106, 25));
            this.add(title[delayTime]);
        }

//

        delayTime = delay * 1000;
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jFrame.getContentPane().removeAll();
                jFrame.setSize(drawPanel.getSize());
                jFrame.getContentPane().add(drawPanel);
                jFrame.revalidate();
                jFrame.repaint();
            }
        };
        Timer timer = new Timer(delayTime, taskPerformer);
        timer.start();
        timer.setRepeats(false);
    }

    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        jFrame.setTitle("Polygon Area Calculator");
        Info info = new Info(jFrame, 3);
        jFrame.setSize(info.getSize());
        jFrame.setResizable(false);
        Container cPane = jFrame.getContentPane();
        cPane.add(info);
        jFrame.setVisible(true);
        jFrame.setLocationRelativeTo((Component)null);
        jFrame.setDefaultCloseOperation(3);
    }
}

package database;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.JSlider;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.BorderLayout;
import javax.swing.JLayeredPane;

public class EnterPanel extends JPanel {

    public JLabel namelable, titlelable;
    public JButton enterbutton;
    public JTextField namefield;
    public JPasswordField pwdfield;
    public JFrame iframe;
    private JLabel pwdlabel;
    private JLabel lblNewLabel;

    public EnterPanel(JFrame frame) {
        // 创建一个JLayeredPane
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 800, 600);

        // 在JLayeredPane中添加背景图片
        JLabel background = new JLabel(new ImageIcon("C:\\Users\\15478\\Desktop\\figures\\背景.png"));
        background.setBounds(0, 0, 800, 600);
        layeredPane.add(background, new Integer(0));

        // 在JLayeredPane中添加文字和按钮
        titlelable = new JLabel("学生课程管理系统");
        titlelable.setHorizontalAlignment(SwingConstants.CENTER);
        titlelable.setBounds(40, 56, 710, 50);
        titlelable.setFont(new Font("华文新魏", Font.BOLD, 50));
        layeredPane.add(titlelable, new Integer(1));

        namelable = new JLabel("用户名:");
        namelable.setFont(new Font("微软雅黑", Font.BOLD, 23));
        namelable.setBounds(262, 374, 167, 29);
        layeredPane.add(namelable, new Integer(1));

        namefield = new JTextField(14);
        namefield.setBounds(345, 380, 150, 25);
        layeredPane.add(namefield, new Integer(1));

        pwdlabel = new JLabel("密   码:");
        pwdlabel.setFont(new Font("微软雅黑", Font.BOLD, 23));
        pwdlabel.setBounds(262, 414, 267, 23);
        layeredPane.add(pwdlabel, new Integer(1));

        pwdfield = new JPasswordField(14);
        pwdfield.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        pwdfield.setBounds(345, 420, 150, 25);
        pwdfield.setEchoChar('*');
        layeredPane.add(pwdfield, new Integer(1));

        enterbutton = new JButton("登录");
        enterbutton.setBounds(379, 367, 80, 25);
        enterbutton.addActionListener(new CreatNewWindow());
        enterbutton.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    new CreatNewWindow();
                }
            }
        });
        enterbutton.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (namefield.getText().equals("zy") && pwdfield.getText().equals("123456")) {
                    System.out.println("right");
                    new MainWindow();
                } else {
                    System.out.println("wrong");
                    new ErrorMsg();
                }
            }
        });
        layeredPane.add(enterbutton, new Integer(1));

        // 将JLayeredPane添加到JPanel中
        this.setLayout(new BorderLayout());
        this.add(layeredPane, BorderLayout.CENTER);
        this.setOpaque(true);
        setBackground(SystemColor.inactiveCaption);
        setForeground(Color.CYAN);

        iframe = frame;

        JLabel label = new JLabel("管理员登录界面");
        label.setFont(new Font("幼圆", Font.BOLD, 30));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBounds(0, 123, 710, 30);
        layeredPane.add(label, new Integer(1));
        
        lblNewLabel = new JLabel("");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setIcon(new ImageIcon("C:\\Users\\15478\\Desktop\\figures\\南开logo.png"));
        lblNewLabel.setBounds(0, 172, 750, 189);
        layeredPane.add(lblNewLabel, new Integer(1));
    }

    class CreatNewWindow implements ActionListener {
        int p = 0;

        public void actionPerformed(ActionEvent e) {

            JButton btn = (JButton) e.getSource();
            EnterFrame f = (EnterFrame) btn.getRootPane().getParent();

            if (namefield.getText().equals("zy") && pwdfield.getText().equals("123456")) {
                p = 1;
                System.out.println("right");
                new MainWindow();
            } else {
                System.out.println("wrong");
                new ErrorMsg();
            }
        }
    }

}

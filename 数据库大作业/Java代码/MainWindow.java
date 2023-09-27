package database;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class MainWindow extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		setTitle("学生信息管理系统");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 820, 530);

		// 设置背景图片
		JLabel background = new JLabel(new ImageIcon("C:\\Users\\15478\\Desktop\\figures\\背景2.png"));
		background.setBounds(0, 0, 820, 530);
		getContentPane().add(background);

		// 设置菜单栏
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		// 删除课程按钮
		JButton shanchuBtn = new JButton("课程信息删除");
		menuBar.add(shanchuBtn);
		shanchuBtn.addActionListener(new shanchuListener());

		shanchuBtn.setForeground(new Color(85, 107, 47));
		shanchuBtn.setFont(new Font("幼圆", Font.BOLD, 20));
		shanchuBtn.setBackground(new Color(135, 206, 235));

		// 插入新生按钮
		JButton charuBtn = new JButton("新生信息录入");
		menuBar.add(charuBtn);
		charuBtn.addActionListener(new charuListener());

		charuBtn.setForeground(new Color(85, 107, 47));
		charuBtn.setFont(new Font("幼圆", Font.BOLD, 20));
		charuBtn.setBackground(new Color(135, 206, 235));

		// 更新学生专业按钮
		JButton gengxinBtn = new JButton("转专业信息更新");
		menuBar.add(gengxinBtn);
		gengxinBtn.addActionListener(new gengxinListener());

		gengxinBtn.setForeground(new Color(85, 107, 47));
		gengxinBtn.setFont(new Font("幼圆", Font.BOLD, 20));
		gengxinBtn.setBackground(new Color(135, 206, 235));

		// 查询选课情况按钮
		JButton chaxunBtn = new JButton("选课信息查询");
		menuBar.add(chaxunBtn);
		chaxunBtn.addActionListener(new chaxunListener());

		chaxunBtn.setForeground(new Color(85, 107, 47));
		chaxunBtn.setFont(new Font("幼圆", Font.BOLD, 20));
		chaxunBtn.setBackground(new Color(135, 206, 235));

		// 退出系统按钮
		JButton tuichuBtn = new JButton("退 出 系 统");
		menuBar.add(tuichuBtn);
		tuichuBtn.addActionListener(new tuichuListener());

		tuichuBtn.setForeground(new Color(85, 107, 47));
		tuichuBtn.setFont(new Font("幼圆", Font.BOLD, 20));
		tuichuBtn.setBackground(new Color(135, 206, 235));

		// 设置按钮位置
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setLayout(null);
		panel.setBounds(0, 0, 820, 530);
		background.add(panel);

		shanchuBtn.setBounds(50, 50, 200, 50);
		charuBtn.setBounds(50, 120, 200, 50);
		gengxinBtn.setBounds(50, 190, 200, 50);
		chaxunBtn.setBounds(50, 260, 200, 50);
		tuichuBtn.setBounds(50, 330, 200, 50);

		panel.add(shanchuBtn);
		panel.add(charuBtn);
		panel.add(gengxinBtn);
		panel.add(chaxunBtn);
		panel.add(tuichuBtn);

		JLabel label = new JLabel("欢迎来到学生信息管理系统");
		label.setBackground(new Color(47, 79, 79));
		label.setForeground(new Color(210, 105, 30));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("微软雅黑", Font.BOLD, 30));
		label.setBounds(0, 203, 1015, 40);
		panel.add(label);

		setVisible(true);
		
		setVisible(true);
	}

	// 删除触发器
	class shanchuListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new ShanChu();
		}
	}

	// 插入触发器
	class charuListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new charu();
		}
	}

	// 更新触发器
	class gengxinListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new GengXin();
		}
	}

	// 查询触发器
	class chaxunListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new ChaXun();
		}
	}

	// 退出触发器
	class tuichuListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	}
}

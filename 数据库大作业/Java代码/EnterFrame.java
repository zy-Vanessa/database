package database;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class EnterFrame extends JFrame {

	private JPanel contentPane;
	public EnterPanel lp;  //enterpanel为自定义类，构造函数参数为一个JFrame类的对象
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EnterFrame frame = new EnterFrame();//创建EnterFrame类
					frame.setVisible(true);//设置窗口可见
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	public EnterFrame() {
		setTitle("学生信息管理系统"); //设置窗口标题“学生信息管理系统”
		setSize(800, 600);//设置窗口大小
		setLocation(500, 300);//设置窗口位置
		setResizable(false);//
		lp = new EnterPanel(this);  //创建EnterPanel类对象
		lp.enterbutton.setLocation(338, 472);//设置EnterPanel类对象中的enterbutton按钮位置
		//用getContentPane()方法获得JFrame的内容面板，再对其加入组件：frame.getContentPane().add(childComponent)
		this.getContentPane().add(lp);  
		lp.setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置窗口退出时关闭程序
		setVisible(true);
	}
}


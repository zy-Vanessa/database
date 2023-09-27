package database;

import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import java.awt.Dialog.ModalExclusionType;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ErrorMsg extends JFrame {

	private final JPanel panel = new JPanel();

	public ErrorMsg() {
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setTitle("错误提示");
		setSize(444, 283);
		setLocation(500, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		panel.setBackground(SystemColor.info);
		panel.setForeground(SystemColor.info);
		panel.setBounds(0, 0, 426, 236);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setForeground(SystemColor.info);
		lblNewLabel.setBackground(SystemColor.info);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 23, 426, 126);
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\15478\\Desktop\\figures\\error.png"));
		panel.add(lblNewLabel);
		
		JLabel label = new JLabel("用户名或密码错误");
		label.setFont(new Font("幼圆", Font.BOLD, 20));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(0, 152, 426, 36);
		panel.add(label);
		
		
		JButton button = new JButton("返回");//关闭按钮
		button.addActionListener(new ActionListener() {//关闭当前错误提示窗口
			public void actionPerformed(ActionEvent e) 
			{
				setVisible(false);//点击返回按钮时，关闭当前窗口
			}
		});
		
		button.setBackground(new Color(230, 230, 250));
		button.setFont(new Font("幼圆", Font.BOLD, 17));
		button.setBounds(159, 196, 113, 27);
		panel.add(button);
		
		setVisible(true);
	}


	public static void main(String args[]) {
		new ErrorMsg();
	}
}
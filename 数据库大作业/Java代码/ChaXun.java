package database;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;



import javax.swing.border.MatteBorder;
import javax.swing.border.LineBorder;


public class ChaXun extends JFrame {
	JLabel lb1=new JLabel("学生修课情况查询");
   	JLabel lb2=new JLabel("学生学号：");
   	JLabel lb3=new JLabel("课程名称：");
   	JTextField stunumgot=new JTextField(10);
   	JTextField crnamegot=new JTextField(10);
   	JButton queryBtn=new JButton("查  询");

   	JTable table;
	DefaultTableModel dtm;
	String columns[] = {"学生编号","学生姓名","专业名称","专业人数","课程编码","课程名称","课程成绩"};
	private final JButton returnBtn = new JButton("返回主菜单");
	
	
	public ChaXun(){
		getContentPane().setBackground(new Color(189, 183, 107));
		setTitle("学生修课情况查询");

		dtm=new DefaultTableModel();//这是显示信息的表格
		
		table = new JTable(dtm);
		table.setEnabled(false);
		table.setBorder(new LineBorder(new Color(72, 61, 139)));
		table.setFont(new Font("幼圆", Font.PLAIN, 12));
		table.setBackground(new Color(192, 192, 192));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS); 
		
		
		JScrollPane s1=new JScrollPane(table);
		dtm.setColumnCount(5);
		dtm.setColumnIdentifiers(columns);

		getContentPane().setLayout(null);
		lb1.setBackground(new Color(176, 196, 222));
		lb1.setHorizontalAlignment(SwingConstants.CENTER);
		lb1.setBounds(0,10,1078,30);
		lb1.setFont(new Font("华文新魏", Font.BOLD, 28));
		getContentPane().add(lb1);

		Font f=new Font("宋体",Font.PLAIN,12);
		lb2.setBounds(10,64,110,25);
		lb2.setFont(new Font("微软雅黑", Font.BOLD, 20));
		getContentPane().add(lb2);
		stunumgot.setBounds(112,68,112,23);
		stunumgot.setFont(f);
		getContentPane().add(stunumgot);
		lb3.setBounds(10,103,110,25);
		lb3.setFont(new Font("微软雅黑", Font.BOLD, 20));
   	    getContentPane().add(lb3);
   	    crnamegot.setBounds(112,107,112,23);
   	    crnamegot.setFont(f);
   	    getContentPane().add(crnamegot);
   	    
   	    queryBtn.setBounds(55,204,141,45);
   	    queryBtn.setFont(new Font("微软雅黑", Font.BOLD, 22));
   	    
//   	    queryBtn.addActionListener(new quListener());
   	    
   	    getContentPane().add(queryBtn);

		s1.setBounds(262,60,648,280);
		getContentPane().add(s1);

//设置边框---------------------------------------------------------------------------
		stunumgot.setBorder(BorderFactory.createLineBorder(Color.black));
   	    crnamegot.setBorder(BorderFactory.createLineBorder(Color.black));
   	    queryBtn.setBorder(BorderFactory.createRaisedBevelBorder());
		s1.setBorder(BorderFactory.createLineBorder(Color.black));
		
		returnBtn.setFont(new Font("微软雅黑", Font.BOLD, 22));
		returnBtn.setBorder(BorderFactory.createRaisedBevelBorder());
		returnBtn.setBounds(55, 281, 141, 45);
		
		getContentPane().add(returnBtn);		
		
		returnBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				setVisible(false);//点击返回按钮时，关闭当前窗口
			}
		});
		


		
//初始化表格窗口------------------------------------------------------------------------

		String Tim="select * from 学生选课情况";
		if(Home.query(Tim)){
			System.out.println(Tim);
			try{
				while(Home.rs.next()){
					String XueShengBianHao=Home.rs.getString("学生编号"); //getString("")中双引号里的是表格的列的名字
					String XueShengXingMing=Home.rs.getString("学生姓名");
					String ZhuanYeMingCheng=Home.rs.getString("专业名称");
					String ZhuanYeRenShu=Home.rs.getString("专业人数");
					String KeChengBianMa=Home.rs.getString("课程编码");
					String KeChengMingCheng=Home.rs.getString("课程名称");
					String KeChengChengJi=Home.rs.getString("课程成绩");					
					

					Vector v=new Vector();
					v.add(XueShengBianHao);
					v.add(XueShengXingMing);
					v.add(ZhuanYeMingCheng);
					v.add(ZhuanYeRenShu);
					v.add(KeChengBianMa);
					v.add(KeChengMingCheng);
					v.add(KeChengChengJi);
					
					dtm.addRow(v);//dtm是显示信息的表格
				}
			}
			catch(Exception eTIQ){
			  System.out.println("初始化表格失败！");
			}
		}
		
		setSize(947,400);
		setVisible(true);
		
       //为查询按钮加事件--------------------------------------------------------
		queryBtn.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        		System.out.println("查询按钮加事件");
        		String STIQ;
        		int rc=dtm.getRowCount();
        		for(int i=0;i<rc;i++){
        			dtm.removeRow(0);
        		}
      //------------------------
        		if(stunumgot.getText().equals("")&&crnamegot.getText().equals("")){//两者都为空，显示全部信息
        		   STIQ="select * from 学生选课情况";
        		}
        		else if(stunumgot.getText().equals("")&&!crnamegot.getText().equals("")){//学号栏为空，课程栏不为空，显示课程相关信息
        			STIQ="select * from 学生选课情况 where 课程名称 = '" + crnamegot.getText() +"'";
        			}
        		else if(!stunumgot.getText().equals("")&&crnamegot.getText().equals("")){//学号栏不为空，课程栏为空，显示学生相关信息
        		   STIQ="select * from 学生选课情况 where 学生编号 = '" + stunumgot.getText() +"' ";
          		}
        		else {//学号栏不为空，课程栏不为空，显示学生课程相关信息
        			STIQ="select * from 学生选课情况 where 学生编号 = '" + stunumgot.getText() + "' and 课程名称 = '"+ crnamegot.getText() +"' ";
        		}
        		System.out.println(STIQ);
        		if(Home.query(STIQ)){
        			try{
						while(Home.rs.next()){
							String XueShengBianHao=Home.rs.getString("学生编号"); //getString("")中双引号里的是表格的列的名字
							String XueShengXingMing=Home.rs.getString("学生姓名");
							String ZhuanYeMingCheng=Home.rs.getString("专业名称");
							String ZhuanYeRenShu=Home.rs.getString("专业人数");
							String KeChengBianMa=Home.rs.getString("课程编码");
							String KeChengMingCheng=Home.rs.getString("课程名称");
							String KeChengChengJi=Home.rs.getString("课程成绩");		

							Vector v=new Vector();
							v.add(XueShengBianHao);
							v.add(XueShengXingMing);
							v.add(ZhuanYeMingCheng);
							v.add(ZhuanYeRenShu);
							v.add(KeChengBianMa);
							v.add(KeChengMingCheng);
							v.add(KeChengChengJi);
							
							dtm.addRow(v);//dtm是显示信息的表格
						}
        			}
        			catch(Exception eT){}
        		}
        	}
        });				
		}

}


package database;

import java.sql.*;
import javax.swing.*;

public class Home {
    public static Connection cn;
    public static Statement st;//Statement用于在已经建立数据库连接的基础上，向数据库发送要执行的SQL语句。
    public static ResultSet rs;//ResultSet用于接收数据库所有查询记录，并对其内容予以显示

    public static int a;
    
    public static boolean openDB() {
        boolean joinFlag;
        try {
            joinFlag = true;
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            cn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;"
                                            +"DatabaseName=homework;"
            		                        +"user=sa;"
                                            +"password=123456;"
            		                        +"trustServerCertificate=true;");
            
            cn.setCatalog("homework");
            System.out.println("数据库连接成功");
            st = cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            return joinFlag;

        } catch (SQLException sqlEx) {
            System.out.println(sqlEx.getMessage());

            joinFlag = false;
            System.out.println("数据库连接失败");
            return joinFlag;

        } catch (ClassNotFoundException notfoundEX) {
            System.out.println(notfoundEX.getMessage());

            joinFlag = false;
            return joinFlag;
        }
    }


    public static void closeDB() {  
        try {  
        	cn.close();  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
    }  
    
    public static void main(String[] args){}
    
    
    //删除课程操作
    public static void decourse(int coursen) throws SQLException{
    	openDB();
		Statement stmt =cn.createStatement();
		try{
			cn.setAutoCommit(false);
		 stmt = cn.createStatement();  
		 int a=stmt.executeUpdate("delete from course where courseno='"  +   coursen   +   "'"); 
		 cn.commit();       //提交JDBC事务 
		 cn.setAutoCommit(true);// 恢复JDBC事务的默认提交方式 
		 if(a==1){
			 JOptionPane.showMessageDialog(null,"删除课程成功", "成功", JOptionPane.INFORMATION_MESSAGE);
		 }
		 else{
			 JOptionPane.showMessageDialog(null,"删除课程失败", "失败", JOptionPane.INFORMATION_MESSAGE);
		 }
		 stmt.close(); 
		 } 
		 catch (Exception exc) { 
			 cn.rollback();//回滚JDBC事务 
		  exc.printStackTrace(); 
		  stmt.close(); 
		 }
    }
    

    //新生录入操作
    public static void insertstu(String s1,String s2,String s3) throws SQLException{
    	openDB();
		Statement stmt =cn.createStatement();
		try{
			cn.setAutoCommit(false);
		 stmt = cn.createStatement(); 

		 int a=stmt.executeUpdate("insert into student values('"+s1+"','"+s2+"','"+s3+"')"); 
		 cn.commit();//提交JDBC事务 
		 cn.setAutoCommit(true);// 恢复JDBC事务的默认提交方式 
		 
		 stmt.close(); 
		 } 
		 catch (Exception exc) { 
			 cn.rollback();//回滚JDBC事务 
		  exc.printStackTrace(); 
		  stmt.close(); 
		 }
    }
    
    
    //转专业信息更新操作
    public static void updatestu(String s1,String s2) throws SQLException{//s1是学生学号，s2是新专业编号
    	openDB();
    	CallableStatement stmt =cn.prepareCall("{call change_major_proc(?,?)}");
		try{
			cn.setAutoCommit(false);
			stmt.setNString(1,s1); 
			stmt.setNString(2,s2); 
			stmt.registerOutParameter(1, Types.INTEGER);
			stmt.registerOutParameter(2, Types.INTEGER);			
		 a=0;
		 a=stmt.executeUpdate(); 
		 cn.commit();//提交JDBC事务 
		 cn.setAutoCommit(true);// 恢复JDBC事务的默认提交方式 
		 
		 if(a==1){
			 JOptionPane.showMessageDialog(null,"转专业信息更新成功", "成功", JOptionPane.INFORMATION_MESSAGE);
		 }
		 else{
			 JOptionPane.showMessageDialog(null,"转专业信息更新失败", "失败", JOptionPane.INFORMATION_MESSAGE);
		 }
		 stmt.close(); 
		 } 
		 catch (Exception exc) { 
			 cn.rollback();//回滚JDBC事务 
		  exc.printStackTrace(); 
		  stmt.close(); 
		 }
    }
    
    
    
    //查询操作
    public static boolean query(String sqlString) {
    	openDB() ;
        try {
            rs = null;
            System.out.println(sqlString);
            rs = st.executeQuery(sqlString);
        } catch (Exception Ex) {
            System.out.println("sql exception+   " + Ex);
            return false;
        }
        return true;
    }
}
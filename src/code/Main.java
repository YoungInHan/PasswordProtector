package code;

import java.sql.Connection;
import java.sql.DriverManager;

public class Main {

	public static void main(String[] args)throws Exception{
		getConnection();

		Password pass = new Password();
		pass.setPassword("Hello");
		
		System.out.println(pass.getRawPassword());
		System.out.println(pass.getStrength());
		
	}
	
	public static Connection getConnection()throws Exception {
		try{
			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/passwordprotector";
			String username = "root";
			String password = "caliorbust";
			Class.forName(driver);
			
			Connection conn = DriverManager.getConnection(url,username,password);
			System.out.println("Connected");
			return conn;
		}catch(Exception e){System.out.println(e);}
		return null;
	}
	
}

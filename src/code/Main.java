package code;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Main {

	public static void main(String[] args)throws Exception{
		createTable();
		
		String password = "caliorbust";	
		
		Password pass = new Password(password);
		pass.setRawPassword("fsd2$eD*");
		
		System.out.println(pass.getRawPassword());
		System.out.println(pass.getStrength());
		System.out.println(pass.getGrade());
		System.out.println(pass.getHiddenPass());
		
		Password pass2 = new Password(password);
		pass2.setHiddenPassword(" *zC:LZC");
		System.out.println(pass2.getRawPassword());
		
	}
	
	public static void createTable() throws Exception{
		try{
			Connection con = getConnection();
			PreparedStatement create = con.prepareStatement("CREATE TABLE IF NOT EXISTS userdata(id int NOT NULL AUTO_INCREMENT, website varchar(255), username varchar(255), password varchar(255), PRIMARY KEY(id))");
			
			create.executeUpdate();		
		}catch(Exception e){System.out.println(e);}
		finally{System.out.println("Function Complete.");}
	}
	
	
	public static Connection getConnection() throws Exception {
		try{
			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/passwordprotector";
			String username = "root";
			String password = "caliorbust";
			Class.forName(driver);
			
			Connection conn = DriverManager.getConnection(url,username,password);
			System.out.println("Connected");
			return conn;
		}catch(Exception e){System.out.println(e);};
		return null;
	}
	
}

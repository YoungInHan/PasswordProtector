package code;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

		public static String password = "caliorbust";
	public static void main(String[] args)throws Exception{
		Scanner scan = new Scanner(System.in);
		//create the table of user information (if doesn't already exist);
		createTable();
		
		
		inputColumn(password);
		
		get();
	}
	
	public static void inputColumn(String password) throws Exception {
		Scanner scan = new Scanner(System.in);
		String websiteInput = "", usernameInput = "", passwordInput = "", passEncrypted = "";
		System.out.println("enter a website");
		websiteInput = scan.nextLine();
		System.out.println("enter a username");
		usernameInput = scan.nextLine();
		System.out.println("enter a password");
		passwordInput = scan.nextLine();
		
		Password pass = new Password(password);
		pass.setRawPassword(passwordInput);
		
		passEncrypted = pass.getHiddenPass();
		
		post(websiteInput, usernameInput, passEncrypted);
	}
	
	public static ArrayList<String> get() throws Exception {
		try{
			Connection con = getConnection();
			PreparedStatement statement = con.prepareStatement("SELECT website,username,password FROM bla");
			ResultSet result = statement.executeQuery();
			
			ArrayList<String> array = new ArrayList<String>();
			while(result.next()){
				
				Password unencryptedPass = new Password(password);
				unencryptedPass.setHiddenPassword(result.getString("password"));
	
				System.out.print(result.getString("website"));
				System.out.print(" ");
				System.out.print(result.getString("username"));
				System.out.print(" ");
				System.out.println(unencryptedPass.getRawPassword());
				
				array.add(result.getString("username"));
			}
			System.out.println("Passwords selected");
			return array;
			
		}catch(Exception e){System.out.println(e);}
		return null;
	}
	
	
	public static void post(String var1, String var2, String var3) throws Exception{
		try{
			Connection con = getConnection();
			PreparedStatement posted = con.prepareStatement("INSERT INTO bla (website, username, password) VALUES ('"+var1+"', '"+var2+ "', '"+var3+"')");
			posted.executeUpdate();
		}catch(Exception e){System.out.println("darn");}
		finally {
			System.out.println("Insert Completed.");
		}
	}
	
	
	public static void createTable() throws Exception{
		try{
			Connection con = getConnection();
			PreparedStatement create = con.prepareStatement("CREATE TABLE IF NOT EXISTS bla(id int NOT NULL AUTO_INCREMENT, website varchar(255), username varchar(255), password varchar(255), PRIMARY KEY(id))");
			
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

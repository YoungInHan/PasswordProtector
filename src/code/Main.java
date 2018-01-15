package code;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JFrame;

public class Main {

	
	public static void main(String[] args)throws Exception{

		
		
		UserWindow w = new UserWindow();
		w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		w.setVisible(true);
		w.setSize(500, 500);
		
		/*	
		
		String password = "caliorbust";
		String username = "root";
		
		String website = "youtube";
		String websiteUser = "SamNoguchi";
		
		String websitePass = "Password1";
		
		
		
		getConnection(username, password);
		createTable(username, password);
		//inputColumn(username, password, website, websiteUser, websitePass);
		System.out.println(getWebsites(username, password));
		System.out.println(getUserData(username, password, website));
		
		*/
	
	}
	
	public static void inputColumn(String username, String password, String website, String websiteUser, String websitePass) throws Exception {
		
		Password pass = new Password(password); //key
		pass.setRawPassword(websitePass);
		
		String passEncrypted = pass.getHiddenPass();
		
		post(username,password,website, websiteUser, passEncrypted);

	}
	
	public static ArrayList<String> getWebsites(String username, String password) throws Exception {
		try{
			Connection con = getConnection(username, password);
			PreparedStatement statement = con.prepareStatement("SELECT website FROM userdata");
			ResultSet result = statement.executeQuery();
			
			ArrayList<String> array = new ArrayList<String>();
			while(result.next()){
				
				array.add(result.getString("website"));
			}
			
			return array;
			
		}catch(Exception e){System.out.println(e);}
		return null;
	}
	
	public static ArrayList<String> getUserData(String username, String password, String website) throws Exception {
		try{
			Connection con = getConnection(username, password);
			PreparedStatement statement = con.prepareStatement("SELECT username,password FROM userdata WHERE website = '"+website+"'");
			ResultSet result = statement.executeQuery();
			
			ArrayList<String> array = new ArrayList<String>();
			while(result.next()){
				
				array.add(result.getString("username"));
				Password unencryptedPass = new Password(password);
				unencryptedPass.setHiddenPassword(result.getString("password"));
				array.add(unencryptedPass.getRawPassword());
			}
			
			return array;
			
		}catch(Exception e){System.out.println(e);}
		return null;
	}
	
	public static void post(String username, String password,String website, String websiteUser, String websitePass) throws Exception{
		try{
			Connection con = getConnection(username, password);
			PreparedStatement posted = con.prepareStatement("INSERT INTO userdata (website, username, password) VALUES ('"+website+"', '"+websiteUser+ "', '"+websitePass+"')");
			posted.executeUpdate();
		}catch(Exception e){System.out.println(e);}
		finally {
			System.out.println("Insert Completed.");
		}
	}
	
	
	public static void createTable(String username, String password) throws Exception{
		try{
			Connection con = getConnection(username, password);
			PreparedStatement create = con.prepareStatement("CREATE TABLE IF NOT EXISTS userdata(id int NOT NULL AUTO_INCREMENT, website varchar(255), username varchar(255), password varchar(255), PRIMARY KEY(id))");
			create.executeUpdate();		
		}catch(Exception e){System.out.println(e);}
		finally{System.out.println("Function Complete.");}
	}
	
	
	public static Connection getConnection(String username, String password) throws Exception {
		try{
			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/passwordprotector";
			Class.forName(driver);
			
			Connection conn = DriverManager.getConnection(url,username,password);
			System.out.println("Connected");
			return conn;
		}catch(Exception e){System.out.println(e);};
		return null;
	}
	
}

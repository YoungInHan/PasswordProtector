package code;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

public class EncryptionAlgorithm {
	
	//creates key using database password
	private static String key = "caliorbust";
	
	//Encrypts user entered password using a stream cipher algorithm, returns encrypted password
	public static StringBuffer Encryptor(String pass) {
		StringBuffer password = new StringBuffer(pass);
		for (int i = 0; i < password.length();i++) {
			int temp = 0;
			temp = (int)password.charAt(i);
			temp = (((temp-32) + ((int)key.charAt(i))-32) % 92);
			password.setCharAt(i, (char)(temp+32));
		}
		return password;
	}
	//Decrypts user entered encrypted string, returns uncrypted password
	public static StringBuffer Decryptor(StringBuffer encodedPass) {
		StringBuffer password = new StringBuffer(encodedPass);
		for (int i = 0; i < password.length();i++) {
			int temp = 0;
			temp = (int)password.charAt(i)-32;
			temp = (temp - (int)key.charAt(i) + 32 );
		    temp = (((temp % 92) + 92) % 92);
			password.setCharAt(i, (char)(temp+32));
		}
		return password;
	}
	//turns database password key into key of Hex values
	public static String toHex(String arg) {
		try {
			return String.format("%x", new BigInteger(1, arg.getBytes("UTF-8")));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void main(String[] args) {
		
		//turns key into hex values
		key = toHex(key);
		
		//declare the user input password to be "userpass", could be any string
		String passIn = "userpass";
		
		//print the encrypted password
		StringBuffer encodedPass = new StringBuffer(Encryptor(passIn));
		System.out.println(encodedPass);
		
		//print the decrypted password
		StringBuffer decodedPass = new StringBuffer(Decryptor(encodedPass));
		System.out.println(decodedPass);
		
	}

}

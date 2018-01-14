package code;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

public class Password {
	
	 String rawPass;
	private String hiddenPass;
	private Strength strengthVal;
	private static String key;
	
	Password(String key){
		strengthVal = new Strength();
		setKey(key);
	}
	
	static void setKey(String key0){
		key = toHex(key0);
	}
	
	void setRawPassword(String password){
		rawPass = password;
		strengthVal.setStrength(rawPass);
		EncryptPass();
	}
	
	void setHiddenPassword(String password){
		hiddenPass = password;
		DecryptPass();
		strengthVal.setStrength(rawPass);
		
	}
	
	String getRawPassword(){
		return rawPass;
	}
	
	String getHiddenPass(){
		return hiddenPass;
	}
	
	int getStrength(){
		return strengthVal.getStrength();
	}
	
	String getGrade(){
		return strengthVal.getGrade();
	}
	
	void EncryptPass(){
		StringBuffer encodedPass = new StringBuffer(Encryptor(rawPass));
		hiddenPass= encodedPass.toString();
	}
	
	void DecryptPass(){
		StringBuffer decodedPass = new StringBuffer(Decryptor(hiddenPass));
		rawPass = decodedPass.toString();
	}
	
	
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
	public static StringBuffer Decryptor(String encodedPass) {
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
	
	
	
	
}

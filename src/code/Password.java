package code;

public class Password {
	
	private String rawPass;
	private String hiddenPass;
	private Strength strengthVal;
	
	Password(){
		rawPass = "";
	}
	
	void setPassword(String password){
		rawPass = password;
		strengthVal.setStrength(rawPass);
	}
	
	
	String getRawPassword(){
		return rawPass;
	}
	
	
	
	
	
	
	
	
}

package code;

public class Password {
	
	private String rawPass;
	private String hiddenPass;
	private Strength strengthVal;
	
	Password(){
		rawPass = "";
		strengthVal = new Strength();
		
	}
	
	void setPassword(String password){
		rawPass = password;
		strengthVal.setStrength(password);
	}
	
	
	String getRawPassword(){
		return rawPass;
	}
	
	int getStrength(){
		return strengthVal.getStrength();
	}
	
	
	
	
	
	
}

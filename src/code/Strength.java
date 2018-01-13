package code;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.*;

public class Strength {

	private int strength;
	
	Strength(){
		strength =0;
	}
	
	void setStrength(String password){
		strength = calculateStrength(password);
	}
	
	int getStrength(){
		return strength;
	}
	
	
	static int numConsecutive(String text, String pattern){
		int numConsc =0;
		
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(text);
		
		while(m.find()){
			if(m.group(0).length()>=2){
				numConsc += m.group().length();
			}
		}
		return numConsc;
	}
	
	static int numSequential(String text){
		int numSeq = 0;
		int seqCount = 1;
		
		for(int i = 0; i< text.length()-1; i++){
			char current = text.charAt(i);
			char next= text.charAt(i+1);
			if((int)next == current+1){
				seqCount++;
			}
			else{
				seqCount =1;
			}
			if(seqCount==3){
				numSeq+=3;
			}
			else if(seqCount>3){
				numSeq++;
			}
		}
		return numSeq;
	}
	
	static int numRepeating(String password){
		int numRep = 0;
		Map <Character, Integer> map = new HashMap<Character, Integer>();
		for(char c: password.toLowerCase().toCharArray()){
			if(map.containsKey(c)){
				int count = map.get(c);
				map.put(c, count+1);
			}
			else{
				map.put(c, 1);
			}
		}
		
		for(Integer values: map.values()){
			if(values>=2){
				numRep+=values;
			}
		}
		
		
		return numRep;
	}
	
	static int calculateStrength(String password){
		int score = 0;
		int length = password.length();
		int numUpperCase = 0, numLowerCase=0, numNumbers =0, numSymbols =0;
		int numMiddleSpecial =0, numRequirements=0;
		
		//Find number of uppercase, lowercase, digit and symbol characters
		
		for(int i =0; i<password.length(); i++){
			
			char c = password.charAt(i);
			
			if(Character.isUpperCase(c)){
				numUpperCase++;
			}
			else if(Character.isLowerCase(c)){
				numLowerCase++;
			}
			else if(Character.isDigit(c)){
				numNumbers++;
				if(i!=0||i!=password.length()-1){
					numMiddleSpecial++;
				}
			}
			else{
				numSymbols++;
			}
		}
		
		if(numLowerCase>0)
			numRequirements++;
		if(numUpperCase>0)
			numRequirements++;
		if(numNumbers>0)
			numRequirements++;
		if(numSymbols>0)
			numRequirements++;
		
		int numConscLetter =0, numConscUpper =0, numConscLower=0, numConscNum=0;
		int numSeqCharacter=0, numRepeats=0;

		//Look for Consecutive letters and numbers
	
		String upperCasePattern = "[A-Z]+";
		String lowerCasePattern = "[a-z]+";
		String numberPattern = "\\d+";
	     
		
		numConscUpper = numConsecutive(password, upperCasePattern);
		numConscLower = numConsecutive(password, lowerCasePattern);
		numConscNum = numConsecutive(password, numberPattern);
		
		//Looking for sequences
		
		numSeqCharacter= numSequential(password);
		
		//looking for repeat characters, case insensitive
		
		numRepeats = numRepeating(password);
		
		
		
		//---------Additions-----------//
		
		if(password.length()>=8){
			if(numRequirements>=3)
				score += numRequirements*2;
		}
		
		score += length*4;
		if(numUpperCase!=0)
			score += ((password.length()-numUpperCase)*2);
		if(numLowerCase!=0)
		score += ((password.length()-numLowerCase)*2);
		score += numNumbers*4;
		score += numSymbols*6;
		score += numMiddleSpecial*2;
		
		
		//---------Subtractions-----------//
		
		if(numUpperCase+numLowerCase == password.length()){
			score -= password.length();
		}else if(numNumbers == password.length()){
			score -= password.length();
		}
	     
		score -= numConscUpper*2;
		score -= numConscLower*2;
		score -= numConscNum*2;
		score -= numSeqCharacter*3;
		score -= Math.pow(numRepeats,2);

		if(score >100){
			score =100;
		}
		
		return score;
	}
	
	
	
}

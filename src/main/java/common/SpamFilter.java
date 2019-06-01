package common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SpamFilter {
	
	// Singleton Pattern
	private static SpamFilter instance;
	
	private SpamFilter () {}
	
	public static SpamFilter getInstance () {
		if (SpamFilter.instance == null) {
			SpamFilter.instance = new SpamFilter();
		}
		return instance;
	}
	
	final private String[] badWords = {"fuck", "damn", "shit", "idiot"};	
		
	String filter(String message) {
		String result = message;
		String substitue = "*";
		for (String word: badWords) {
			Pattern p = Pattern.compile(word);
			Matcher m = p.matcher(result);
			result = m.replaceAll(substitue.repeat(word.length()));
		}
		return result;
	}

}

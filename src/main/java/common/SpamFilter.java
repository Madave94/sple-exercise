package common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SpamFilter {
	
	private String message;
	final private String[] badWords = {"fuck", "damn", "shit", "idiot"};
	
	SpamFilter (String message) {
		this.message = message;
	}
		
	String filter() {
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

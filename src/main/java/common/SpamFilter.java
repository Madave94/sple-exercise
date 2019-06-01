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
		Pattern p = Pattern.compile("fuck");
		Matcher m = p.matcher(message);
		String result = m.replaceAll("****");
		return result;
	}

}

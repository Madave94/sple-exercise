package common;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class TextDecorator {
	
	/*
	 * Here I want to call the decorater method recursivly until
	 * there is no more user command.
	 * The user should decorate the colors by
	 * <color> message </color>
	 */
	public String decorateText(String message) {
		//TODO
		return null;
	}
	
	/*
	 * With different RegEx the user commands will be filtered step by step
	 */
	public String[] findNextCommand(String message) {
		Pattern startPattern = Pattern.compile("<{1}\\w+>{1}");
		Pattern endPattern = Pattern.compile("<{1}/{1}\\w+>{1}");
		Matcher startMatcher = startPattern.matcher(message);
		Matcher endMatcher = endPattern.matcher(message);
		String[] combined = new String[2];
		/*
		 * The regex extracts the color messages from the regular message
		 * and saves them in an extra variable. The method is inconsistent
		 * for using various colors in a row, like:
		 * <blue><yellow>mytext</blue></yellow>
		 */
		if (startMatcher.find() && endMatcher.find()) {
			combined[1] = message.substring
					(startMatcher.start()+1, startMatcher.end()-1).toUpperCase();
			int endPointSubStringA = startMatcher.start()-1;
			if (endPointSubStringA < 0) endPointSubStringA = 0;
			int endPointSubStringC = endMatcher.end()+1;
			if (endPointSubStringC > (message.length()-1)) endPointSubStringC = message.length()-1;
			combined[0] = message.substring(0, endPointSubStringA)+
					message.substring(startMatcher.end(), endMatcher.start())+
					message.substring(endPointSubStringC , message.length()-1);
		}
		return combined;
	}
	
	/*
	 * Connect to ColorPalette and and get the Color
	 * arg[0] = message
	 * arg[1] = command
	 */
	public String applyDecorator(String[] combined) {
		String message = combined[0];
		String command = combined[1];
		return ColorPalette.valueOf(command).getCode()+
				message+ColorPalette.RESET.getCode();
	}

}

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import common.*;

public aspect Color {

	pointcut decorateText(TextDecorator td, String content) : 
		set(String TextDecorator.decorated_content)
		&& args(content)
		&& this(td);
	
	after(TextDecorator td, String content) : decorateText(td, content) {
		td.decorated_content = new TextColor().decorateText(content);
	}
	
	class TextColor {
		
		// Pattern various methods
		Pattern startPattern = Pattern.compile("<{1}\\w+>{1}");
		Pattern endPattern = Pattern.compile("<{1}/{1}\\w+>{1}");
		
		/*
		 * Here I want to call the decorater method recursivly until
		 * there is no more user command.
		 * The user should decorate the colors by
		 * <color> message </color>
		 */
		public String decorateText(String message) {
			String currentMessage = message;
			while(hasAnotherDecorator(currentMessage)) 
			{
				String[] combined = findNextCommand(currentMessage);
				currentMessage = applyDecorator(combined);
			}
			return currentMessage;
		}
		
		public boolean hasAnotherDecorator(String message) {
			boolean result = false;
			Matcher startMatcher = startPattern.matcher(message);
			Matcher endMatcher = endPattern.matcher(message);
			if (startMatcher.find() && endMatcher.find()) result = true;		
			return result;
		}
		
		/*
		 * With different RegEx the user commands will be filtered step by step
		 */
		public String[] findNextCommand(String message) {
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
	
	enum ColorPalette {
		
		// Available Colors
		RESET ("\033[0m"),
		BLACK ("\033[0;30m"),
		RED ("\033[0;31m"),
		GREEN ("\033[0;32m"),
		YELLOW ("\033[0;33m"),
		BLUE ("\033[0;34m"),
		PURPLE ("\033[0;35m"),
		CYAN ("\033[0;36m"),
		WHITE ("\033[0;37m");
		
		// https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println
	    // Reset
		private String colorCode;
	    
	    ColorPalette(String colorCode) {
	    	this.colorCode = colorCode;    	
	    }
	    
	    public String getCode() {
	    	return colorCode;
	    }
	}

	
}
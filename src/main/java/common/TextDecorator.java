package common;

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
		//TODO
		return null;
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

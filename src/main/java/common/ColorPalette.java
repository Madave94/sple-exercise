//#if TextColor && CLI
package common;

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
//#endif
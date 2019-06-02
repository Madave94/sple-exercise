package plugin;

import common.TextColor;

public class CLITextColorPlugin implements TextColorPlugin{

	public String addColor(String msg) {
		return new TextColor().decorateText(msg);
	}
	
	

}

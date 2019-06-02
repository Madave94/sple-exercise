package plugin;

import common.SpamFilter;

public class SpamFilterPlugin implements FilterPlugin {

	public String filter(String msg) {
		return SpamFilter.getInstance().filter(msg);
	}

}

package plugin;

public class Plugin extends PluginConfig{
	
	private static Plugin instance;
	
	private Plugin() {
		super();
	}
	
	public static Plugin getInstance() {
		if (instance == null) {
			instance = new Plugin();
		}
		return instance;
	}
	
	public EncryptionPlugin getEncryptionPlugin() {
		return encryptionPlugin;
	}
	
	public FilterPlugin getSpamFilterPlugin() {
		return filterPlugin;
	}
	
	public TextColorPlugin getTextColorPlugin() {
		return textColorPlugin;
	}

}

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

}

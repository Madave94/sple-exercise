package plugin;

public abstract class PluginConfig {
	
	static PluginConfig plugin;
	
	static AuthentificationPlugin authentificationPlugin;
	static ChatLineListenerPlugin chatLineListenerPlugin;
	static EncryptionPlugin encryptionPlugin;
	static LoggingPlugin loggingPlugin;
	static FilterPlugin filterPlugin;
	static TextColorPlugin textColorPlugin;
	
	
	PluginConfig() {
		// Set Config here
		// mandatory feature
		chatLineListenerPlugin = null;
		
		// optional features
		authentificationPlugin = null;
		encryptionPlugin = null;
		loggingPlugin = null;
		filterPlugin = null;
		textColorPlugin = null;		
	}

}

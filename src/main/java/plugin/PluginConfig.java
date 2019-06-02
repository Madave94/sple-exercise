package plugin;

public abstract class PluginConfig {
	
	static PluginConfig plugin;
	
	static AuthentificationPlugin authentificationPlugin;
	static ChatLineListenerPlugin chatLineListenerPlugin;
	static EncryptionPlugin encryptionPlugin;
	static LoggingPlugin loggingPlugin;
	static SpamFilterPlugin spamFilterPlugin;
	static TextColorPlugin textColorPlugin;
	
	
	PluginConfig() {
		// Set Config here
		// mandatory feature
		chatLineListenerPlugin = null;
		
		// optional features
		authentificationPlugin = null;
		encryptionPlugin = null;
		loggingPlugin = null;
		spamFilterPlugin = null;
		textColorPlugin = null;		
	}

}

package plugin;

public abstract class PluginConfig {
	
	static PluginConfig plugin;
	
	AuthentificationPlugin authentificationPlugin;
	ChatLineListenerPlugin chatLineListenerPlugin;
	EncryptionPlugin encryptionPlugin;
	LoggingPlugin loggingPlugin;
	SpamFilterPlugin spamFilterPlugin;
	TextColorPlugin textColorPlugin;
	
	
	PluginConfig() {
		// Set Config here
		// mandatory feature
		chatLineListenerPlugin = new ConsolPlugin();
		if (chatLineListenerPlugin == null) {
			chatLineListenerPlugin = new ConsolPlugin();
		}
		
		// optional features
		authentificationPlugin = null;
		encryptionPlugin = null;
		loggingPlugin = null;
		spamFilterPlugin = null;
		textColorPlugin = null;		
	}

}

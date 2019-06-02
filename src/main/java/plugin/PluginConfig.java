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
		chatLineListenerPlugin = new ConsolPlugin();
		
		// optional features
		authentificationPlugin = new HandshakeAuthentificationPlugin();
		encryptionPlugin = new ROT13Plugin();
		loggingPlugin = new FileLoggingPlugin();
		filterPlugin = new SpamFilterPlugin();
		textColorPlugin = new CLITextColorPlugin();		
	}

}

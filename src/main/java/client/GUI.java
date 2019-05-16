//#if GUI
//@package client;
//@
//@import java.io.BufferedReader;
//@import java.io.EOFException;
//@import java.io.IOException;
//@import java.io.InputStreamReader;
//@
//@import javafx.application.Application;
//@import javafx.event.EventHandler;
//@import javafx.geometry.Insets;
//@import javafx.scene.Scene;
//@import javafx.scene.control.Button;
//@import javafx.scene.control.Label;
//@import javafx.scene.control.TextField;
//@import javafx.scene.input.KeyCode;
//@import javafx.scene.input.KeyEvent;
//@import javafx.scene.input.MouseEvent;
//@import javafx.scene.layout.BorderPane;
//@import javafx.scene.layout.HBox;
//@import javafx.scene.layout.Priority;
//@import javafx.stage.Stage;
//@
//@public class GUI extends Application implements ChatLineListener, Runnable {
//@
//@	protected Thread thread;
//@	private Client chatClient;
//@	
//@	TextField textField;
//@	Label label;
//@	Button button;
//@	String msg;
//@
//@	public GUI() {
//@	}
//@
//@	// stub for GUI
//@	public GUI(Client chatClient) {
//@
//@		// register listener so that we are informed whenever a new chat message
//@		// is received (observer pattern)
//@		chatClient.addLineListener(this);
//@		this.chatClient = chatClient;
//@
//@		Thread thread = new Thread(this);
//@		thread.start();
//@		launch();
//@	}
//@
//@	@Override
//@	public void run() {
//@		msg = null;
//@		try {
//@			Thread thisthread = Thread.currentThread();
//@			while (thread == thisthread) {
//@				if (msg != null || msg != "") {
//@					chatClient.send(msg);
//@					msg = "";
//@				}
//@			}
//@		} finally {
//@			System.out.println("GUI thread closed.");
//@			thread = null;
//@		}
//@	}
//@
//@	@Override
//@	public void newChatLine(String line) {
//@		label.setText(label.getText() + "\n" + line);
//@	}
//@
//@	@Override
//@	public void start(Stage stage) {
//@		String javaVersion = System.getProperty("java.version");
//@		String javafxVersion = System.getProperty("javafx.version");
//@
//@		// Control
//@		label = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
//@
//@		button = new Button("send");
//@		button.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
//@
//@		textField = new TextField();
//@
//@		EventHandler<MouseEvent> eventHandlerMouse = new EventHandler<MouseEvent>() {
//@			@Override
//@			public void handle(MouseEvent e) {
//@				msg = textField.getText();
//@				textField.setText("");
//@			}
//@		};
//@		
//@		textField.setOnKeyPressed(new EventHandler<KeyEvent>() {
//@		    @Override
//@		    public void handle(KeyEvent keyEvent) {
//@		        if (keyEvent.getCode() == KeyCode.ENTER)  {
//@		        	msg = textField.getText();
//@					textField.setText("");
//@		        }
//@		    }
//@		});
//@		
//@		button.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandlerMouse);
//@		
//@		HBox hbox = new HBox(textField, button);
//@		HBox.setHgrow(textField, Priority.ALWAYS);
//@		hbox.setSpacing(10);
//@		hbox.setMinHeight(hbox.getPrefHeight());
//@		
//@		BorderPane borderPane = new BorderPane();
//@		borderPane.setBottom(hbox);
//@		borderPane.setTop(label);
//@
//@		Scene scene = new Scene(borderPane, 640, 480);
//@
//@		stage.setScene(scene);
//@		stage.setTitle("Chat-App");
//@		stage.show();
//@	}
//@
//@	public static void main(String[] args) {
//@		launch();
//@	}
//@
//@}
//#endif

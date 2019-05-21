//#if GUI
//@package client;
//@
//@import javafx.application.Application;
//@import javafx.application.Platform;
//@import javafx.event.EventHandler;
//@import javafx.scene.Scene;
//@import javafx.scene.control.Button;
//@import javafx.scene.control.TextArea;
//@import javafx.scene.control.TextField;
//@import javafx.scene.input.KeyCode;
//@import javafx.scene.input.KeyEvent;
//@import javafx.scene.input.MouseEvent;
//@import javafx.scene.layout.BorderPane;
//@import javafx.scene.layout.HBox;
//@import javafx.scene.layout.Priority;
//@import javafx.stage.Stage;
//@
//@public class Javafx_GUI extends Application implements ChatLineListener {
//@
//@	private Client chatClient;
//@
//@	TextField textField;
//@	TextArea label;
//@	Button button;
//@
//@	public Javafx_GUI() {
//@	}
//@
//@	// stub for GUI
//@	public Javafx_GUI(Client chatClient) {
//@
//@		// register listener so that we are informed whenever a new chat message
//@		// is received (observer pattern)
//@		chatClient.addLineListener(this);
//@		chatClient.printListener();
//@		this.chatClient = chatClient;
//@
//@		launch();
//@	}
//@
//@	@Override
//@	public void newChatLine(String line) {
//@		try {
//@			new Thread(() -> {
//@				chatClient.printListener();
//@				System.out.println("I can see my string - " +  line);
//@				Platform.runLater(() -> label.appendText(line));
//@			}).start();			
//@		} catch (Exception e) {
//@			System.out.println(line);
//@		}
//@	}
//@
//@	public void send() {
//@		String msg = textField.getText() + "\n";
//@		System.out.print(msg);
//@		textField.setText("");
//@		// label.appendText(msg);
//@		if (msg != null || msg != "") {
//@			new Thread(() -> Platform.runLater(() -> chatClient.send(msg))).start();
//@			//chatClient.send(msg);
//@		}
//@	}
//@
//@	@Override
//@	public void start(Stage stage) {
//@		String javaVersion = System.getProperty("java.version");
//@		String javafxVersion = System.getProperty("javafx.version");
//@
//@		// Control
//@		label = new TextArea("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
//@
//@		button = new Button("send");
//@		button.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
//@
//@		textField = new TextField();
//@
//@		EventHandler<MouseEvent> eventHandlerMouse = new EventHandler<MouseEvent>() {
//@			@Override
//@			public void handle(MouseEvent e) {
//@				send();
//@				//newChatLine(textField.getText());
//@				e.consume();
//@			}
//@		};
//@
//@		textField.setOnKeyPressed(new EventHandler<KeyEvent>() {
//@			@Override
//@			public void handle(KeyEvent keyEvent) {
//@				if (keyEvent.getCode() == KeyCode.ENTER) {
//@					send();
//@					//newChatLine(textField.getText());
//@					keyEvent.consume();
//@				}
//@			}
//@		});
//@
//@		button.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandlerMouse);
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
//@}
//#endif

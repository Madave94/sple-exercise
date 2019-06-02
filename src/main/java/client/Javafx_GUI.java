
package client;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

public class Javafx_GUI extends Application implements ChatLineListener {

	private Client chatClient;

	TextField textField;
	TextArea label;
	Button button;
	HBox hbox;
	BorderPane borderPane;
	Scene scene;
	EventHandler<MouseEvent> eventHandlerMouse;

	// stub for GUI
	public Javafx_GUI(Client chatClient) {

		// register listener so that we are informed whenever a new chat message
		// is received (observer pattern)
		
		chatClient.addLineListener(this);
		this.chatClient = chatClient;	
		
		launch();
	}

	@Override
	public void newChatLine(String line) {
		label.appendText(line);
	}

	public void send() {
		String msg = textField.getText() + "\n";
		System.out.print(msg);
		textField.setText("");
		chatClient.send(msg);
	}

	@Override
	public void start(Stage stage) {
		// Control
		label = new TextArea();

		button = new Button("send");
		button.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");

		textField = new TextField();

		eventHandlerMouse = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				send();
			}
		};

		textField.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent keyEvent) {
				if (keyEvent.getCode() == KeyCode.ENTER) {
					send();
				}
			}
		});

		button.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandlerMouse);

		hbox = new HBox(textField, button);
		HBox.setHgrow(textField, Priority.ALWAYS);
		hbox.setSpacing(10);
		hbox.setMinHeight(hbox.getPrefHeight());

		borderPane = new BorderPane();
		borderPane.setBottom(hbox);
		borderPane.setTop(label);

		scene = new Scene(borderPane, 640, 480);

		stage.setScene(scene);
		stage.setTitle("Chat-App");
		stage.show();
	}
}

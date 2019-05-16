package client;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class GUI extends Application implements ChatLineListener, Runnable {

	protected Thread thread;
	private Client chatClient;

	public GUI() {
	}

	// stub for GUI
	public GUI(Client chatClient) {

		// register listener so that we are informed whenever a new chat message
		// is received (observer pattern)
		chatClient.addLineListener(this);
		this.chatClient = chatClient;

		Thread thread = new Thread(this);
		thread.start();
		launch();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

	@Override
	public void newChatLine(String line) {
		// TODO Auto-generated method stub
		// Here happens a System.out.println in the Console
	}

	public String testButton(String line) {
		return line;
	}

	@Override
	public void start(Stage stage) {
		Group root = new Group();
		ObservableList list = root.getChildren();

		String javaVersion = System.getProperty("java.version");
		String javafxVersion = System.getProperty("javafx.version");

		// Pane

		// Control
		Label label = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");

		Circle circle = new Circle();
		circle.setCenterX(300.0f);
		circle.setCenterY(135.0f);
		circle.setRadius(25.0f);
		circle.setFill(Color.AQUA);

		TextField textField = new TextField();
		textField.setLayoutX(50);
		textField.setLayoutY(100);

		EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				String toPrint = testButton(textField.getText());
				label.setText(toPrint);
				circle.setFill(Color.DARKSLATEBLUE);
			}
		};

		circle.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);

		list.add(textField);
		list.add(label);
		list.add(circle);
		Scene scene = new Scene(root, 640, 480);

		stage.setScene(scene);
		stage.setTitle("Chat-App");
		stage.show();
	}

	public static void main(String[] args) {
		launch();
	}

}

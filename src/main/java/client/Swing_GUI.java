//#if GUI
//@package client;
//@
//@import javax.swing.JButton;
//@import javax.swing.JFrame;
//@import javax.swing.JPanel;
//@import javax.swing.JScrollPane;
//@import javax.swing.JTextArea;
//@import javax.swing.JTextField;
//@
//@import java.awt.Color;
//@import java.awt.Dimension;
//@import java.awt.BorderLayout;
//@import java.awt.GridBagConstraints;
//@import java.awt.GridBagLayout;
//@import java.awt.event.ActionEvent;
//@import java.awt.event.ActionListener;
//@
//@// source: https://codereview.stackexchange.com/questions/25461/simple-chat-room-swing-gui
//@// source recommended by Jan Froehlich
//@
//@public class Swing_GUI extends JFrame implements ChatLineListener {
//@	
//@	/**
//@	 * 
//@	 */
//@	private static final long serialVersionUID = 1L;
//@	
//@	protected Thread thread;
//@	private Client chatClient;
//@	
//@    JFrame frame;
//@    JButton sendMessage;
//@    JTextField messageBox;
//@    JTextArea chatBox;
//@
//@	public Swing_GUI(Client chatClient) {
//@		
//@		// register listener so that we are informed whenever a new chat message
//@		// is received (observer pattern)
//@		chatClient.addLineListener(this);
//@		this.chatClient = chatClient;	
//@		
//@		frame = new JFrame("ChatApp");		
//@		
//@		display();
//@	}
//@	
//@	public void display() {
//@        frame.setVisible(true);
//@        JPanel southPanel = new JPanel();
//@        frame.add(BorderLayout.SOUTH, southPanel);
//@        southPanel.setBackground(Color.GRAY);
//@        southPanel.setLayout(new GridBagLayout());
//@
//@        messageBox = new JTextField(35);
//@        sendMessage = new JButton("Send");
//@        chatBox = new JTextArea();
//@        chatBox.setEditable(false);
//@        frame.add(new JScrollPane(chatBox), BorderLayout.CENTER);
//@
//@        chatBox.setLineWrap(true);
//@
//@        GridBagConstraints left = new GridBagConstraints();
//@        left.anchor = GridBagConstraints.WEST;
//@        GridBagConstraints right = new GridBagConstraints();
//@        right.anchor = GridBagConstraints.EAST;
//@        right.weightx = 2.0;
//@
//@        southPanel.add(messageBox, left);
//@        southPanel.add(sendMessage, right);
//@
//@        sendMessage.addActionListener(new sendMessageButtonListener());
//@        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//@        frame.setSize(500, 300);
//@        frame.setMinimumSize(new Dimension(500, 300));
//@    }
//@
//@	
//@	public void newChatLine(String line) {
//@		chatBox.append(line + "\n");
//@		
//@	}
//@	
//@	
//@	class sendMessageButtonListener implements ActionListener {
//@	    public void actionPerformed(ActionEvent event) {
//@	    	String msg = messageBox.getText();
//@	    	chatClient.send(msg);
//@	        messageBox.setText("");
//@	    }
//@	}
//@}
//#endif

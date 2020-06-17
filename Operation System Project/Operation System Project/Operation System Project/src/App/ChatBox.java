package App;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

public class ChatBox extends JFrame {

	public static JTextArea txtChatBox;
	private JPanel contentPane;
	private JTextField txtMessage;
	
	public ChatBox(Socket socket, String userName) {
		initComponents(socket, userName);
	}
	
	private void initComponents(Socket socket, String userName) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 685, 532);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtChatBox = new JTextArea();
		txtChatBox.setWrapStyleWord(true);
		txtChatBox.setEditable(false);
		txtChatBox.setBounds(6, 12, 500, 402);
		txtChatBox.setBackground(new Color(240, 248, 255));
		txtChatBox.setLineWrap(true);
		contentPane.add(txtChatBox);
		
		txtMessage = new JTextField();
		txtMessage.setBounds(16, 430, 391, 36);
		txtMessage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtMessage.setText("");
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(txtMessage.getText().trim().equals("")) {
					txtMessage.setText("");
				}
			}
		});
		txtMessage.setForeground(UIManager.getColor("textHighlight"));
		txtMessage.setText("Nhap tin nhan...");
		contentPane.add(txtMessage);
		txtMessage.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 417, 506, 12);
		separator.setBackground(UIManager.getColor("inactiveCaptionText"));
		contentPane.add(separator);
		
		JButton btnSend = new JButton("");
		btnSend.setBounds(419, 430, 36, 36);
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String message = txtMessage.getText();
				
				if(!message.trim().equals("")) {
					try {
						txtChatBox.append("\nMe : " + message);
						PrintStream os = new PrintStream(socket.getOutputStream());
						os.println("\n[" + userName + "]" + " " + message);	
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Error while send message to server!");
						e1.printStackTrace();
					}
				}
			}
		});
		
		btnSend.setIcon(new ImageIcon("imagesapp-2.png"));
		contentPane.add(btnSend);
		
		JButton btnExit = new JButton("");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					PrintStream os = new PrintStream(socket.getOutputStream());
					os.println(userName  + " da roi khoi nhom chat.");
					os.close();
					dispose(); // AFK.
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnExit.setIcon(new ImageIcon("Close.png"));
		btnExit.setBounds(470, 430, 36, 36);
		contentPane.add(btnExit);
		
		JLabel lbImages = new JLabel("");
		lbImages.setIcon(new ImageIcon("luckyTo copy.jpg"));
		lbImages.setBounds(0, 6, 679, 498);
		contentPane.add(lbImages);
		setVisible(true);
	}
}

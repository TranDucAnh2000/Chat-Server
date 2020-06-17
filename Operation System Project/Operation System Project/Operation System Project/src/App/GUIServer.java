package App;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import model.Server;
import java.awt.Toolkit;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUIServer extends JFrame {

	private JPanel contentPane;
	public static JTextArea txtShow;
	private JTextField txtPort;

	public GUIServer() {
		initComponents();
	}
	
	private void initComponents() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("server.png"));
		setTitle("SERVER");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 669, 528);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(6, 6, 657, 494);
		contentPane.add(panel);
		panel.setLayout(null);
		
		txtShow = new JTextArea();
		txtShow.setLineWrap(true);
		txtShow.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		txtShow.setEditable(false);
		txtShow.setBounds(263, 33, 388, 455);
		panel.add(txtShow);
		txtShow.setColumns(10);
		
		JLabel lbImages = new JLabel("");
		lbImages.setIcon(new ImageIcon("server1.jpg"));
		lbImages.setBounds(6, 141, 645, 347);
		panel.add(lbImages);
		
		JLabel lbServer = new JLabel("SERVER");
		lbServer.setForeground(new Color(255, 127, 80));
		lbServer.setHorizontalAlignment(SwingConstants.CENTER);
		lbServer.setBackground(new Color(245, 255, 250));
		lbServer.setFont(new Font("Chalkboard", Font.PLAIN, 70));
		lbServer.setBounds(6, 5, 260, 139);
		panel.add(lbServer);
		
		JLabel lbPort = new JLabel("Port : ");
		lbPort.setFont(new Font("Chalkboard SE", Font.PLAIN, 15));
		lbPort.setForeground(new Color(255, 0, 0));
		lbPort.setBounds(472, 5, 46, 16);
		panel.add(lbPort);
		
		txtPort = new JTextField();
		txtPort.setEnabled(false);
		txtPort.setEditable(false);
		txtPort.setText("9999");
		txtPort.setBounds(530, 5, 56, 26);
		panel.add(txtPort);
		txtPort.setColumns(10);
		
		JButton btnStart = new JButton("");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread(new Server(Integer.parseInt(txtPort.getText()))).start();
			}
		});
		btnStart.setIcon(new ImageIcon("statr-2.jpg"));
		btnStart.setBounds(619, 5, 32, 26);
		panel.add(btnStart);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new GUIServer();
	}

}

package App;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;
import javax.swing.UIManager;
import model.Client;
import model.User;
import javax.swing.JComboBox;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;

public class Login extends JFrame{

	private JFrame frame;
	private JTextField txtIP;
	private JTextField txtName;
	private JCheckBox check;
	private JButton btnStart;
	
	public Login() {
		initialize();
	}

	private void initialize() {
		
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(".jpg"));
		frame.setBounds(100, 100, 648, 548);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(6, 6, 291, 514);
		panel.setBackground(new Color(0, 255, 255));
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel Title = new JLabel("CHAT CLIENT - SERVER");
		Title.setBackground(new Color(255, 255, 0));
		Title.setFont(new Font("Chalkboard SE", Font.PLAIN, 13));
		Title.setHorizontalAlignment(SwingConstants.CENTER);
		Title.setBounds(29, 289, 218, 53);
		panel.add(Title);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(229, 261, -187, 16);
		panel.add(separator);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(29, 277, 223, 1);
		panel.add(separator_2);
		
		JLabel lbImages = new JLabel("");
		lbImages.setIcon(new ImageIcon("tcp-ip.jpg"));
		lbImages.setHorizontalAlignment(SwingConstants.CENTER);
		lbImages.setBounds(17, 6, 256, 271);
		panel.add(lbImages);
		
		JSeparator separator_1_1_1 = new JSeparator();
		separator_1_1_1.setForeground(Color.DARK_GRAY);
		separator_1_1_1.setBounds(39, 280, 206, 12);
		panel.add(separator_1_1_1);
		
		JLabel lblNewLabel_1 = new JLabel("IP Address");
		lblNewLabel_1.setBounds(361, 101, 84, 56);
		frame.getContentPane().add(lblNewLabel_1);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(361, 173, 264, 12);
		frame.getContentPane().add(separator_1);
		
		txtIP = new JTextField();
		txtIP.setBounds(440, 115, 195, 26);
		txtIP.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtIP.setText("");
			}
		});
		txtIP.setText("Enter IP Address");
		txtIP.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 13));
		txtIP.setBackground(SystemColor.window);
		frame.getContentPane().add(txtIP);
		txtIP.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Nick Name");
		lblNewLabel_1_1.setBounds(361, 197, 84, 56);
		lblNewLabel_1_1.setFont(new Font("Chalkboard SE", Font.PLAIN, 13));
		frame.getContentPane().add(lblNewLabel_1_1);
		
		txtName = new JTextField();
		txtName.setBounds(440, 212, 195, 26);
		txtName.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtName.setText("");
			}
		});
		txtName.setText("Enter nick name");
		txtName.setFont(new Font(".AppleSystemUIFont", Font.PLAIN, 13));
		txtName.setColumns(10);
		txtName.setBackground(SystemColor.window);
		frame.getContentPane().add(txtName);
		
		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setBounds(361, 277, 264, 12);
		frame.getContentPane().add(separator_1_1);
		
		JComboBox comboBox = new JComboBox();
		String[] components = new String[] {"1111", "2222", "9999"};
		comboBox.setModel(new DefaultComboBoxModel(components));
		comboBox.setBounds(541, 306, 84, 27);
		frame.getContentPane().add(comboBox);
		
		JLabel lbPort = new JLabel("Port :");
		lbPort.setForeground(Color.GRAY);
		lbPort.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lbPort.setBounds(501, 310, 40, 16);

		
		frame.getContentPane().add(lbPort);
		
		btnStart = new JButton("START");
		btnStart.setBounds(361, 358, 264, 70);
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(isValid1()) {
					String hostName = txtIP.getText();					
					int port = Integer.parseInt(comboBox.getSelectedItem().toString());
					Client client = new Client(hostName,  port);
					User user = new User(txtName.getText());
					client.setUser(user);
					
					new ChatBox(client.getSocket(), txtName.getText());
				}	
			}
		});
		btnStart.setForeground(new Color(220, 20, 60));
		btnStart.setBackground(new Color(255, 140, 0));
		btnStart.setFont(new Font("Chalkboard", Font.PLAIN, 13));
		frame.getContentPane().add(btnStart);
		
		check = new JCheckBox("Agree");
		check.setBounds(361, 306, 128, 23);
		check.setEnabled(false);
		check.setSelected(true);
		frame.getContentPane().add(check);
		
		JLabel lbConditions = new JLabel("<html>\n<body>\n<u>Terms and Conditions</u>\n</body>\n</html>");
		lbConditions.setBounds(503, 504, 139, 16);
		lbConditions.setFont(new Font("Bradley Hand", Font.PLAIN, 13));
		lbConditions.setForeground(UIManager.getColor("Button.disabledText"));
		frame.getContentPane().add(lbConditions);
		frame.setVisible(true);
	}
	
	private boolean isValid1() {
		if(txtIP.getText().equals("") || txtName.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Enter full field to start !");
			return false;
		} else if(txtIP.getText().equals("localhost") && txtName.getText().length() > 6) {
			return true;
		} else if(!txtIP.getText().equals("localhost")) {
			JOptionPane.showMessageDialog(null, "Default : Localhost");
			return false;
		}
		JOptionPane.showMessageDialog(null, "Enter 6 characters for nick name !");
		txtName.setText("");
		return false;
	}
	
	public static void main(String[] args) {
		new Login();
	}
}

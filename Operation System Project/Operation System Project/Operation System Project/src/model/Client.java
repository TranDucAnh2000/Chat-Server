package model;

import java.io.IOException;
import java.net.Socket;
import javax.swing.JOptionPane;
import controller.ReadClient;

public class Client {
	
	private User user;
	private String ipAddress; // VD : ket noi cuc bo : localhost...
	private int portToConnect; // Cong de ket noi den server.(Cong ben phia Server)
	private Socket socket; // Tao socket() de ket noi den Server. Neu dung giao dien khi ta muon gui cho Server : new WriteClient(socket, sms).	
	
	public Client(String ipAddress, int portToConnect) {
		this.ipAddress = ipAddress;
		this.portToConnect = portToConnect;
		connectToServer();
	}
	
	private void connectToServer() {
		try {
			socket = new Socket(ipAddress, portToConnect);
			new ReadClient(socket).start(); // Doc du lieu tu server.
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Fail to connect to Server!");
		} 
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

	public Socket getSocket() {
		return socket;
	}

	public String getIPAddress() {
		return ipAddress;
	}

	public void setIPAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public int getPortToConnect() {
		return portToConnect;
	}

	public void setPortToConnect(int portToConnect) {
		this.portToConnect = portToConnect;
	}
}

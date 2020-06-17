package model;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import App.GUIServer;
import controller.ServerThread;

public class Server implements Runnable{
	
	private int portForConnect; // Cong cho phep cac client ket noi.
	private ServerSocket serverSocket;
	private Socket socket; // Luong socket cua client ket noi den.
	private static List<Socket> socketList; 	
	
	private SimpleDateFormat formater = new SimpleDateFormat("HH:mm:ss");
	
	public Server(int portForConnect) {
		socketList = new ArrayList<>();
		this.portForConnect = portForConnect;
	}
	
	private void acceptClient() {
		
			try {
				serverSocket = new ServerSocket(portForConnect);
				GUIServer.txtShow.append("[" + formater.format(new Date()) + "]" + "  Sever is listening...");
				while(true) {
					socket = serverSocket.accept(); // Chap nhan cac ket noi tu clients.
					
					socketList.add(socket);
					
					sendToAllSocket("New user has entered the room!");
					GUIServer.txtShow.append("\nNew user has connected : " + socket);
					
					new ServerThread(socket).start(); // Muc dich doc du lieu tu luong va gui den tat ca cac clients khac.
				}
			} catch (IOException e) {
					try {
						if(socket != null) socket.close();
						if(serverSocket != null) serverSocket.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				JOptionPane.showMessageDialog(null, "Server is occupied !");
			}
	}
	
	private void sendToAllSocket(String message) { // Muc dich :  A has entered hoac A has quitted.
		PrintStream os;
		for(int  i = 0; i < socketList.size(); i++) {
			if(socketList.get(i).getPort() != socket.getPort()) {
				try {
					os = new PrintStream(socketList.get(i).getOutputStream());
					os.println(message);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public Socket getSocket() {
		return socket; // socket hien tai.
	}
	
	public int getPortForConnect() {
		return portForConnect;
	}

	public void setPortForConnect(int portForConnect) {
		this.portForConnect = portForConnect;
	}
	
	public static List<Socket> getSocketList() { 
		return socketList;
	}

	@Override
	public void run() {
		acceptClient();
		
	}
}

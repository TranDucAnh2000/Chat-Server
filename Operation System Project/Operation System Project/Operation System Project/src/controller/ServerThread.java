package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import javax.swing.JOptionPane;

import App.GUIServer;
import model.Server;

public class ServerThread extends Thread{

	private Socket socket;
	private BufferedReader br;
	private PrintStream os;
	
	public ServerThread(Socket socket) {
		try {
			this.socket = socket;
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch(IOException io) {
			JOptionPane.showMessageDialog(null, "Error while reading message from Client");
				try {
					if(br != null) br.close();
					if(socket != null) socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

	@Override
	public void run() {
		try {
			String message = "";
			while(true) {
				message = br.readLine();
				
				if(message.contains("Exit")) {
					notifyToAllClient(message.replace("@abcd", "has quitted!"));
					GUIServer.txtShow.append(socket + " is closed");
					Server.getSocketList().remove(socket);
					this.interrupt();
				} else {
					notifyToAllClient(message);
				}
			}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "BYE BYE NHA :)");
		}
	}
	
	private void notifyToAllClient(String message) { 
		Socket socket1;
		if(Server.getSocketList().size() == 1) {
			socket1 = Server.getSocketList().get(0);
			try {
				os = new PrintStream(socket1.getOutputStream());
				if(message.contains("thang lone server")) { // dua ty sao cang v.
					os.println("[Server] Tao kick m bay gio, thich chui tau ko?");
				} else if(message.contains("ghet thang server vai")) {
					os.println("[Server] Ban nay noi xau minh nha !! :)");
				} else if(message.contains("anh server dep trai")) {
						os.println("[Server] Ban dang yeu nhu Huu Huy vay :3");
				}else {
					os.println("[Server] Chao mung ban den voi nhom chat !! :))");
				}
				return;
			} catch (IOException e) {
				e.printStackTrace();
			}			
		}
		
		for(int i = 0; i < Server.getSocketList().size(); i++) {
			socket1 = Server.getSocketList().get(i);
			try {
				if(socket1.getPort() != socket.getPort()) {
					os = new PrintStream(socket1.getOutputStream());
					os.println(message);
				}
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Fail to notify to All clients!");
				try {
					if(os != null) os.close();
					if(socket1 != null) socket1.close();
				} catch(IOException ex) {
					ex.printStackTrace();
				}
			}
		}
	}
}


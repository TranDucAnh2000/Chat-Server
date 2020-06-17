package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import javax.swing.JOptionPane;
import App.ChatBox;

public class ReadClient extends Thread{

	private BufferedReader br;
	
	public ReadClient(Socket socket) {
		
		try {
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch(IOException io) {
			JOptionPane.showMessageDialog(null, "Error while reading message from Server");
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
				ChatBox.txtChatBox.append("\n" + message);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
}

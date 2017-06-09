package chatClient;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPChatClient {

	public static void main(String[] args){
		System.out.println("Client Running...");
		Socket clientSocket;
		
		while(true){
			//Connects to the Server port
			try {
				clientSocket = new Socket("localhost", 2613);
				BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
				
				String sentence;
				sentence = inFromUser.readLine();
				
				if(sentence.equals("Quit")){
					break;
				}
				
				//Creates stream to send to server
				DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
				outToServer.writeBytes(sentence + '\n');
				
				//Reads what the server sent back
				BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				String modifiedSentence = inFromServer.readLine();
				System.out.println("FROM SERVER: " + modifiedSentence);
				
				clientSocket.close();

			} catch (UnknownHostException e) {
				System.out.println("Unknown Host Exception");
				e.printStackTrace();
				break;
			} catch (IOException e) {
				System.out.println("IO Exception");
				e.printStackTrace();
				break;
			}
		}
	}
}

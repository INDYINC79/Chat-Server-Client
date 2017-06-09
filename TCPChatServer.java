package chatClient;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class TCPChatServer {
	private static final int PORT = 2613;
	
	public static void main(String[] args) throws Exception{
		System.out.println("Server running...");
		
		//create a socket
		ServerSocket serverSocket = new ServerSocket(PORT);
		
		//Server is always listening with this while statement
		while(true){
			
			Socket receiveSocket =  serverSocket.accept();
			
			//Captures what client sent
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(receiveSocket.getInputStream()));
			
			//Reads and converts what was received from client
			String clientSentence = inFromClient.readLine();
			System.out.println("Received: " + clientSentence);
			
			String capitalizedSentence = convertAndSort(clientSentence);
			
			//capitalizedSentence = clientSentence.toUpperCase() + '\n';
			
			//Required to send data back to client
			DataOutputStream outToClient = new DataOutputStream(receiveSocket.getOutputStream());
			outToClient.writeBytes(capitalizedSentence);
			
			receiveSocket.close();
		}
	}
	
	public static String convertAndSort(String numbers){
		String[] arrayNumString = numbers.split("\\s");
		int[] arrayNum = new int[arrayNumString.length];
		
		for(int i = 0; i < arrayNumString.length; i++){
			arrayNum[i] = Integer.parseInt(arrayNumString[i]);
		}
		
		Arrays.sort(arrayNum);
		
		String complete = Arrays.toString(arrayNum);
		complete = complete.replace(",", "");
		complete = complete.substring(1, complete.length()-1);
		return complete;
	}
}

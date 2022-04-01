
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * This class launch the server side application using TCP.
 * This server translate English to other languages.
 * The connected client can input English text into the server to translate it.
 */

public class ServerTranslationApplication {
	
	public static void main(String[] args) throws IOException{
		
		ServerSocket serverSocket = null;
		
		try {
			
			// Bind ServerSocket to a part
			int portNo = 4228;
			serverSocket = new ServerSocket(portNo);
			
			ServerTranslator serverTranslator = new ServerTranslator();
			
			System.out.println("Waiting for request");
			
			while(true) {
				
				// Accept client request for connection
				Socket clientSocket = serverSocket.accept();
				
				// Get text input form client
				DataInputStream inputStream = new DataInputStream(clientSocket.getInputStream());
				String engText = inputStream.readUTF();
				int language = inputStream.readInt();
				
				// Translate the text to respective language
				String output = serverTranslator.translate(engText, language);
				
				// Create stream to write data on the network
				DataOutputStream outputStream = new DataOutputStream(clientSocket.getOutputStream());
				
				// Send current date back to client
				outputStream.writeUTF(output);
				
				// Close the socket
				clientSocket.close();
			}
			
		}catch (IOException ioe) {
			if (serverSocket != null)
				serverSocket.close();
			
			ioe.printStackTrace();
		}
	}
}
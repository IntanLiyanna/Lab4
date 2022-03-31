import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * This class launch the server side application using TCP.
 * The server generates current date.
 * Each connected client will received current date from the server.
 */

public class ServerWordCountApplication {

	/**
	 * Main entry point to the server side application
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		// Launch the server frame
		ServerWordCountFrame serverFrame = new ServerWordCountFrame();
		serverFrame.setVisible(true);
		
		// Binding to a port or any other port no you are fancy of
		int portNo = 4228;
		ServerSocket serverSocket = new ServerSocket(portNo);
		
		WordCountGenerator wordsLengthGenerator = new WordCountGenerator();
		
		// Counter to keep track the number of requested connection
		int totalRequest = 0;
		
		// Server needs to be alive forever
		while (true) {
			
			// Message to indicate server is alive
			serverFrame.updateServerStatus(false);
			
			// Accept client request for connection
			Socket clientSocket = serverSocket.accept();
			
			// Generate words length
			String wordsLength = wordsLengthGenerator.getWordsLength();
			
			// Create stream to write data on the network
			DataOutputStream outputStream = new DataOutputStream(clientSocket.getOutputStream());
			
			// Send words length back to the client
			outputStream.writeUTF(wordsLength);
			
			// Close the socket
			clientSocket.close();
		
			// Update the request status
			serverFrame.updateRequestStatus(
					"Data sent to the client: " + wordsLength);
			serverFrame.updateRequestStatus("Accepted connection to from the "
					+ "client. Total request = " + ++totalRequest );
			
		}
		
	}
}
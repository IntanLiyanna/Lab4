import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * This method launch the frame and manage the connection to the server.
 */

public class ClientWordCountApplication {

	public static void main(String[] args) 
			throws UnknownHostException, IOException {
		
		// Launch client-side frame
		ClientWordCountFrame clientWordsLengthFrame = new ClientWordCountFrame();
		clientWordsLengthFrame.setVisible(true);
		
		// Connect to the server @ localhost, port 4228
		Socket socket = new Socket(InetAddress.getLocalHost(), 4228);
		
		// Update the status of the connection
		clientWordsLengthFrame.updateConnectionStatus(socket.isConnected());
		
		// Read from network
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(socket.getInputStream()));
		
		// Display the words length
		String wordsLength = bufferedReader.readLine();
		clientWordsLengthFrame.updateServerDate(wordsLength);
		
		// Close everything
		bufferedReader.close();
		socket.close();

	}

}
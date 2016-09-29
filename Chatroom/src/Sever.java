import java.io.IOException;
import java.net.ServerSocket;

public class Sever {

	public static String serverID, path;
		
	public static void main(String[] args) {
		
		//Get the path and serverID data from command line	
		ArgsReader.read(args);
		path = ArgsReader.getPath();
		serverID = ArgsReader.getServerID();
			
		//Read the config file to get all the server info		
		ConfigReader configReader = new ConfigReader();
		configReader.read(serverID, path);
		
		/*
		 * After reading the config file, set up two listening thread
		 * One for client port and one for server port
		 */
			
		try {
			//Create serversocket for client port
			ServerSocket server_client = 
					new ServerSocket(configReader.getClientPort(),50,configReader.getServerIP());
			
			//Create serversocket for server port
			ServerSocket server_server = 
					new ServerSocket(configReader.getServerPort(),50,configReader.getServerIP());
			
			//Listening to client port thread
			new ListenToClient(server_client).start();
			
			//Listening to server port thread
			new ListenToServer(server_server).start();
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}

package serve;

import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class ConnectServer {
	public ConnectServer(int port) {
		try {
			ServerSocket server = new ServerSocket(port);
			while(true) {
				Socket client = server.accept();
			} 
		} catch (Exception e) {} 
}
}

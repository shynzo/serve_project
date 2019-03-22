package teste;

import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
	
	public static void main(String[] args) 
									throws UnknownHostException, IOException, Exception{
		Encrypt men = new Encrypt();
		InetAddress ip;
		ip = InetAddress.getLocalHost();
		Socket client = new Socket("127.0.0.1", 5984);
		Scanner t = new Scanner(System.in);
		PrintStream s = new PrintStream(client.getOutputStream());
		byte[] menS;
		while(t.hasNextLine()) {
			menS = men.encrypt(t.toString(), "Çç256gjYZAA62K");
		}
		s.close();
		t.close();
	}

}

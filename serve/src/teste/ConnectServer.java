package teste;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ConnectServer {
	static Decrypt men = new Decrypt();
	static String key = "Çç256gjYZAA62K";
	static byte[] mens;
	public static void main(String[] args) throws IOException, UnknownHostException{
		try {
			ServerSocket server = new ServerSocket(5984);
			while(true) {
				Socket client = server.accept();
				Scanner entrada = new Scanner(client.getInputStream());
				mens = entrada.toString().getBytes();
		        	while(entrada.hasNextLine()){
		        		String b = men.decrypt(mens, key);
		        		
		        		System.out.println("cliente: " + b);
		            }
		            entrada.close();
		            server.close();
			} 
		} catch (Exception e) {} 
	}
}

 package teste1;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) throws IOException, Exception {
			DataInputStream in;
			ServerSocket servidor = new ServerSocket(12345);
			System.out.println("Porta 12345 aberta!");
			String str, strd;
			Decrypt decrypt = new Decrypt();
			Socket cliente = servidor.accept();
			System.out.println("Nova conexão com o cliente " +     
			cliente.getInetAddress().getHostAddress());
			boolean con = false;
			in = new DataInputStream(cliente.getInputStream());
			while(!con) {
				str = in.readUTF();
				System.out.println(str);
				strd = decrypt.decrypt(str, "y/B?E(H+KbPeShVm");
				System.out.println("Mensagem: " + strd);
			}
			
         }
	
     }

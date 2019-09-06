package chat_socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Servidor {
	public static void main(String[] args) 
			throws IOException, Exception {
		String mensagem;
		ServerSocket servidor = new ServerSocket(51433);
		System.out.println("Socket iniciado!");
		InetAddress localhost = InetAddress.getLocalHost();
		System.out.println("O seu IP é: " + localhost.getHostAddress() + " !");
		Socket cliente = servidor.accept();
		DataOutputStream out = new DataOutputStream(cliente.getOutputStream());
		DataInputStream in = new DataInputStream(cliente.getInputStream());
		try{
			while(!servidor.isClosed()){
				mensagem = in.readUTF();
				System.out.println(mensagem);
				out.writeUTF(mensagem);
				out.flush();
			}
			servidor.close();
		}catch(SocketException e){
			
			e.printStackTrace();
		}
	}
}

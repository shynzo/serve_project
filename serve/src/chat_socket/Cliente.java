package cola_prova;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {

	public static void main(String[] args) 
			throws UnknownHostException, IOException, Exception{
		Scanner t = new Scanner(System.in);
		System.out.print("Insira o seu nome:\n>");
		String nome = t.nextLine();
		System.out.print("Insira o IP da máquina:\n>");
		String ip = t.nextLine();
		try {
			Socket client = new Socket(ip, 51433);
			DataOutputStream out = new DataOutputStream(client.getOutputStream());
			DataInputStream in = new DataInputStream(client.getInputStream());
			String str;
			try {
			while(true){
			if(client.isConnected()) {
				System.out.print(">");
				str = t.nextLine();
				out.writeUTF(nome.concat(": ").concat(str));
				out.flush();
				str = " ";
				String mens = in.readUTF();
				System.out.println(mens);
			} else {
				break;
				}
			}
		} catch (SocketException e) {
			System.out.println("Conexão encerrada");
			client.close();
		}
	} catch (ConnectException e) {
		System.out.println("Erro! Socket não encontrado.");
		
	}
		t.close();	
	}
}

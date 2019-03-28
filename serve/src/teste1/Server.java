 package teste1;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Server {

public static void listarquivos() {
  File folder = new File("C:\\Users\\Usuário\\Desktop\\programas");
  String[] files = folder.list();
  for (String file : files){
    System.out.println("\t>" + file);
  }
}

public static void exec(String a) {
	try {
		String[] command = {"cmd.exe", "/C", "Start", "C:\\Users\\Usuário\\Desktop\\programas\\".concat(a).concat(".bat")};
		Process p =  Runtime.getRuntime().exec(command);
	} catch (IOException e){
		System.out.println("Erro! Programa não encontrado.");
	}
}

public static void main(String[] args) throws IOException, Exception {

	DataInputStream in;
	ServerSocket servidor = new ServerSocket(12345);
	System.out.println("Porta 12345 aberta!");
	String mens, aesB, ivB, mend;
	boolean arq = false;
	Decrypt decrypt = new Decrypt();
	Socket cliente = servidor.accept();
	System.out.println("Nova conexão com o cliente " +     
	cliente.getInetAddress().getHostAddress());
	in = new DataInputStream(cliente.getInputStream());
	try {
	while(!servidor.isClosed()) {
		aesB = in.readUTF();
		ivB = in.readUTF();
		mens = in.readUTF();
		mend = decrypt.decrypt(mens, aesB, ivB);
		if(mend.equals("te amo")) {
			System.out.println("eu sei que você me ama.");
		} else if(mend.equals("fechar")) {
			System.out.println("Conexão encerrada.");
			servidor.close();
		} else if(mend.contains("abrir")) {
			arq = true;
			while(arq) {
			System.out.println("O que deseja abrir?");
			listarquivos();
			aesB = in.readUTF();
			ivB = in.readUTF();
			mens = in.readUTF();
			mend = decrypt.decrypt(mens, aesB, ivB);
			if(mend.equals("firefox")) {
				exec(mend);
			    arq = false;
			} else {
				arq = false;
				}
			} 
		} else {
			System.out.println("Mensagem: " + mend);
		}
	}
	} catch (SocketException e) {
		System.out.println("Conexão perdida.");
		servidor.close();
		}
	}
}

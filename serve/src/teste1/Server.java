 package teste1;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Server {

public static void listarquivos() {
  File folder = new File("C:\\Users\\gusta\\Desktop\\programas");
  String[] files = folder.list();
  for (String file : files){
    System.out.println("\t>" + file);
  }
}

public static void exec(String a) {
	try {
		String[] command = {"cmd.exe", "/C", "Start", "C:\\Users\\gusta\\Desktop\\programas\\".concat(a).concat(".bat")};
		Process p =  Runtime.getRuntime().exec(command);
	} catch (IOException e){
		System.out.println("Erro! Programa não encontrado.");
	}
}

public static void criardir() {
	File diretorio = new File("C:\\Users\\Snake\\Desktop\\Macros");
    diretorio.mkdir();
}
public static void main(String[] args) throws IOException, Exception {

	ServerSocket servidor = new ServerSocket(12345);
	System.out.println("Porta 12345 aberta!");
	String mens, aesB, ivB, mend;
	boolean arq = false;
	Decrypt decrypt = new Decrypt();
	Socket cliente = servidor.accept();
	System.out.println("Nova conexão com o cliente " +     
	cliente.getInetAddress().getHostAddress());
	DataInputStream in;
	in = new DataInputStream(cliente.getInputStream());
	try {
	while(!servidor.isClosed()) {
		aesB = in.readUTF();
		ivB = in.readUTF();
		mens = in.readUTF();
		mend = decrypt.decrypt(mens, aesB, ivB);
		switch(mend) {
			case "te amo":
				System.out.println("eu sei que você me ama.");
				break;
			case "fechar":
				System.out.println("Conexão encerrada.");
				servidor.close();	
				break;
			case "abrir":
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
				break;
			default:
				System.out.println("Mensagem: " + mend);
				break;
		}
	}} catch (SocketException e) {
		System.out.println("Conexão perdida.");
		servidor.close();
		}
	}
}

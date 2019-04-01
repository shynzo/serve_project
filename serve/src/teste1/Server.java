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
	  file = file.replace(".bat", "");
    System.out.println("\t> " + file);
  }
}

public static void executar(String a) {
	  File folder = new File("C:\\Users\\Usuário\\Desktop\\programas");
	  String[] files = folder.list();
	  for (String file : files){
		  file = file.replace(".bat", "");
		  if(file.equalsIgnoreCase(a))
			  Exec.Executar(file);
		  else
			  continue;
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
	DataInputStream in = new DataInputStream(cliente.getInputStream());
	try {
	while(!servidor.isClosed()) {
		aesB = in.readUTF();
		ivB = in.readUTF();
		mens = in.readUTF();
		mend = decrypt.decrypt(mens, aesB, ivB);
		switch(mend) {
			case "abrir":
				arq = true;
				while(arq) {
				System.out.println("O que deseja abrir?");
				listarquivos();
				aesB = in.readUTF();
				ivB = in.readUTF();
				mens = in.readUTF();
				mend = decrypt.decrypt(mens, aesB, ivB);
				executar(mend);
				mend = "";
				arq = false;
				}
				break;
			case "fechar":
				System.out.println("Conexão encerrada.");
				servidor.close();	
				break;
			case "configurar":
				String nome, nomexe, camP;
				System.out.println("Como quer chamar o seu programa?");
				aesB = in.readUTF();
				ivB = in.readUTF();
				mens = in.readUTF();
				nome = decrypt.decrypt(mens, aesB, ivB);
				System.out.println("Qual o caminho do seu programa?");
				aesB = in.readUTF();
				ivB = in.readUTF();
				mens = in.readUTF();
				camP = decrypt.decrypt(mens, aesB, ivB);
				System.out.println("Qual o nome do seu programa?");
				aesB = in.readUTF();
				ivB = in.readUTF();
				mens = in.readUTF();
				nomexe = decrypt.decrypt(mens, aesB, ivB);
				CriarArquivo.criararq(nome, nomexe, camP);
				break;
			default:
				Mensagens.mensagem(mend);
				break;
		}
	}} catch (SocketException e) {
		System.out.println("Conexão perdida.");
		servidor.close();
		}
	}
}

package teste1;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Server {

public static void listarquivos() {
  File folder = new File("C:\\SERVE");
  String[] files = folder.list();
  for (String file : files){
	  file = file.replace(".bat", "");
    System.out.println("\t> " + file);
  }
}

public static void executar(String a) {
	  File folder = new File("C:\\SERVE");
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
	File dir = new File("C:\\SERVE");
    if(dir.mkdir())
    	System.out.println("Pasta criada no caminho: " + dir.getAbsolutePath());
}

public static void main(String[] args) 
		throws IOException, Exception {
	criardir();
	ServerSocket servidor = new ServerSocket(51433);
	System.out.println("Socket iniciado!");
	InetAddress localhost = InetAddress.getLocalHost();
	System.out.println("O seu IP é: " + localhost.getHostAddress() + " !");
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
				while(true) {
					System.out.println("O que deseja verificar?");
					aesB = in.readUTF();
					ivB = in.readUTF();
					mens = in.readUTF();
					mend = decrypt.decrypt(mens, aesB, ivB);
					String file = mend;
					arq = VerificaAberto.verf(mend);
					if(arq){
						System.out.println("Confirmar encerramento do programa ".concat(file.concat(".exe"))+"?(S/N)");
						aesB = in.readUTF();
						ivB = in.readUTF();
						mens = in.readUTF();
						mend = decrypt.decrypt(mens, aesB, ivB);
			            if(mend.equalsIgnoreCase("s")){
			            	Process taskill = Runtime.getRuntime().exec("TASKKILL /F /IM ".concat(file.concat("*")) + " /T");
			            	System.out.println("Processo encerrado.");
			            	break;
			            }
			            else
			            	break;
					}
					arq = false;
				}
				break;
			case "encerrar":
				System.out.println("Conexão encerrada.");
				servidor.close();	
				break;
			case "configurar":
				String nome = "", nomexe = "", camP = "";
				System.out.println("Como quer chamar o seu programa?");
				aesB = in.readUTF();
				ivB = in.readUTF();
				mens = in.readUTF();
				nome = decrypt.decrypt(mens, aesB, ivB);
				if("cancelar".equalsIgnoreCase(nome)){
					System.out.println("Operação encerrada!");
					break;
				} else {
				System.out.println("Qual o caminho do seu programa?");
				aesB = in.readUTF();
				ivB = in.readUTF();
				mens = in.readUTF();
				camP = decrypt.decrypt(mens, aesB, ivB);
				if("cancelar".equalsIgnoreCase(camP)){
					System.out.println("Operação encerrada!");
					break;
				} else {
				System.out.println("Qual o nome do seu programa?");
				aesB = in.readUTF();
				ivB = in.readUTF();
				mens = in.readUTF();
				nomexe = decrypt.decrypt(mens, aesB, ivB);
				if("cancelar".equalsIgnoreCase(nomexe)){
					System.out.println("Operação encerrada!");
					break;
				} else {
				CriarArquivo.criararq(nome, nomexe, camP);
				}}}
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
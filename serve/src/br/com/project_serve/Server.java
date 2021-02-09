package br.com.project_serve;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Server {

	public static void criardir() {
		File dir = new File("C:\\SERVE");
		if (dir.mkdir())
			System.out.println("Pasta criada no caminho: " + dir.getAbsolutePath());
	}

	
	public static void main(String[] args) throws IOException, Exception {

		criardir();
		String menc, aesB, ivB, mend;
		
		ServerSocket servidor = new ServerSocket(51433);

		System.out.println("Socket iniciado!");
		InetAddress localhost = InetAddress.getLocalHost();
		System.out.println("O seu IP é: " + localhost.getHostAddress() + " !");
		Socket cliente = servidor.accept();
		System.out.println("Nova conexão com o cliente " + cliente.getInetAddress().getHostAddress());
		DataInputStream in = new DataInputStream(cliente.getInputStream());

		try {
			while (!servidor.isClosed()) {
				aesB = in.readUTF();
				ivB = in.readUTF();
				menc = in.readUTF();
				mend = Decrypt.decrypt(menc, aesB, ivB);
				switch (mend) {
				case "configurar":
					String[] config = { "", "", "" };
					System.out.println("Como quer chamar o seu programa?");
					aesB = in.readUTF();
					ivB = in.readUTF();
					menc = in.readUTF();
					config[0] = Decrypt.decrypt(menc, aesB, ivB);
					if ("cancelar".equalsIgnoreCase(config[0])) {
						System.out.println("Operação encerrada!");
						break;
					} else {
						System.out.println("Qual o caminho do seu programa?");
						aesB = in.readUTF();
						ivB = in.readUTF();
						menc = in.readUTF();
						config[1] = Decrypt.decrypt(menc, aesB, ivB);
						if ("cancelar".equalsIgnoreCase(config[0])) {
							System.out.println("Operação encerrada!");
							break;
						} else {
							System.out.println("Qual o nome do seu programa?");
							aesB = in.readUTF();
							ivB = in.readUTF();
							menc = in.readUTF();
							config[2] = Decrypt.decrypt(menc, aesB, ivB);
							if ("cancelar".equalsIgnoreCase(config[2])) {
								System.out.println("Operação encerrada!");
								break;
							} else {
								CriarArquivo.criararq(config[0], config[2], config[1]);
							}
						}
					}
					break;

				case "encerrar":
					System.out.println("Conexão encerrada.");
					servidor.close();
					break;

				default:
					Comandos.mensagem(mend);
					break;
				}
			}
		} catch (SocketException e) {
			System.out.println("Conexão perdida.");
			servidor.close();
		}
	}
}
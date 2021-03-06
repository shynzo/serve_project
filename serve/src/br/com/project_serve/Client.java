package br.com.project_serve;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Scanner;

import br.com.project_serve.Encrypt;

public class Client {

	public static void main(String[] args) throws UnknownHostException, IOException, Exception {
		DataOutputStream out;
		SecureRandom random = new SecureRandom();
		byte[] iv = new byte[16];
		byte[] aesKey = new byte[16];
		Scanner t = new Scanner(System.in);
		System.out.print("Insira o IP da m�quina:\n>");
		String ip = t.nextLine();
		try {
			Socket client = new Socket(ip, 51433);
			out = new DataOutputStream(client.getOutputStream());
			String str;
			try {
				while (true) {
					if (client.isConnected()) {
						random.nextBytes(aesKey);
						random.nextBytes(iv);
						String aesKeyAsString = Base64.getEncoder().encodeToString(aesKey);
						String ivAsString = Base64.getEncoder().encodeToString(iv);
						System.out.print(">");
						str = t.nextLine();
						byte[] mens = Encrypt.encrypt(str, aesKeyAsString, ivAsString);
						String encryptedString = Base64.getEncoder().encodeToString(mens);
						out.writeUTF(aesKeyAsString);
						out.flush();
						out.writeUTF(ivAsString);
						out.flush();
						out.writeUTF(encryptedString);
						out.flush();
						str = " ";
					} else {
						break;
					}
				}
			} catch (SocketException e) {
				System.out.println("Conex�o encerrada");
				client.close();
			}
		} catch (ConnectException e) {
			System.out.println("Erro! Socket n�o encontrado.");

		}
		t.close();
	}
}
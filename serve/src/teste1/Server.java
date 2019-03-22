 package teste1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Server {
	
	static final byte[] key = "Çç256gjYZAA62K".getBytes(StandardCharsets.UTF_8);
	static AdvancedEncryptionStandard advancedEncryptionStandard = new AdvancedEncryptionStandard(key);

	public static void main(String[] args) throws IOException, Exception {
			ServerSocket servidor = new ServerSocket(12345);
			System.out.println("Porta 12345 aberta!");
			
			Socket cliente = servidor.accept();
			System.out.println("Nova conexão com o cliente " +     
			cliente.getInetAddress().getHostAddress());
			byte[] mend;
			DataInputStream dIn = new DataInputStream(cliente.getInputStream());
			Scanner entrada = new Scanner(cliente.getInputStream());
			while (entrada.hasNextLine()) {
				int length = dIn.readInt();
				if(length>0) {
				    byte[] message = new byte[length];
				    dIn.readFully(message, 0, message.length);
				}
				System.out.println("cliente: " + new String(mend));
			}

             entrada.close();
             servidor.close();
         }
     }

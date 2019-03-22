package teste1;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client {
	
	static final byte[] key = "Çç256gjYZAA62K".getBytes(StandardCharsets.UTF_8);
	static AdvancedEncryptionStandard advancedEncryptionStandard = new AdvancedEncryptionStandard(key);
   
	public static void main(String[] args) throws UnknownHostException
												, IOException, Exception {
    	byte[] mens;
        Socket cliente = new Socket("127.0.0.1", 12345);
        System.out.println("O cliente se conectou ao servidor!");
        
        Scanner teclado = new Scanner(System.in);
        OutputStream socketOutputStream = cliente.getOutputStream();
        while (teclado.hasNextLine()) {
        	mens = AdvancedEncryptionStandard.encrypt(teclado.toString().getBytes(StandardCharsets.UTF_8));
        	socketOutputStream.write(mens);
        }

        teclado.close();
    }
}
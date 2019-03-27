package teste1;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import teste.Encrypt;

public class Client {
	
	public static void main(String[] args) 
									throws UnknownHostException, IOException, Exception{
		DataOutputStream out;
		Encrypt men = new Encrypt();
		Scanner t = new Scanner(System.in);
		Socket client = new Socket("127.0.0.1", 12345);
		byte[] mens;
		out = new DataOutputStream(client.getOutputStream());
		String str;
		while(client.isClosed()==false){
			str = t.nextLine();
			mens = men.encrypt(str, "y/B?E(H+KbPeShVm");
			System.out.println(new String(mens));
			out.writeUTF(new String(mens));
			out.flush();
			str = " ";
		}
	t.close();
	
	}

}

package teste1;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Testando {
	
	static final byte[] key = "Çç256gjYZAA62K".getBytes(StandardCharsets.UTF_8);
	static AdvancedEncryptionStandard advancedEncryptionStandard = new AdvancedEncryptionStandard(key);
	
	public static void main(String[] args) throws Exception{
		Scanner t = new Scanner(System.in);
		String a;
		byte[] mens;
		a = t.nextLine();
		mens = a.getBytes(StandardCharsets.UTF_8);
		byte[] menc = AdvancedEncryptionStandard.encrypt(mens);
		byte[] mend = AdvancedEncryptionStandard.decrypt(menc);
		System.out.println(new String(mens));
		System.out.println(new String(menc));
		System.out.println(new String(mend));
	}
}
	 


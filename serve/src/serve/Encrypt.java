package serve;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Encrypt {
	
	String IV = "AAAAAAAAAAAAAAAA";
	
	public byte[] encrypt(String menE, String keys) throws Exception {
		Cipher encrypt = Cipher.getInstance("AES/EBC/PKCS5Padding", "SunJCE");
		SecretKeySpec key = new SecretKeySpec(keys.getBytes("UTF-8"), "AES");
		encrypt.init(Cipher.ENCRYPT_MODE, key, encrypt.getParameters());
		return encrypt.doFinal(menE.getBytes());
	}
}

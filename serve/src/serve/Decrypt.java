package serve;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Decrypt{

	String IV = "AAAAAAAAAAAAAAAA";
	
	public String decrypt(String menD, String keys) throws Exception{
		Cipher decrypt = Cipher.getInstance("AES/EBC/PKCS5Padding", "SunJCE");
		SecretKeySpec key = new SecretKeySpec(keys.getBytes("UTF-8"), "AES");
		decrypt.init(Cipher.DECRYPT_MODE, key, decrypt.getParameters());
		return new String(decrypt.doFinal(menD.getBytes()));
	}
	
}

package teste1;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;

public class Decrypt{

	String IV = "AAAAAAAAAAAAAAAA";
	
	public String decrypt(byte[] menD, String keys) throws Exception{
		Cipher decrypt = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
		SecretKeySpec key = new SecretKeySpec(keys.getBytes("UTF-8"), "AES");
		decrypt.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(IV.getBytes("UTF-8")));
		return new String(decrypt.doFinal(menD),"UTF-8");
	}
	
}

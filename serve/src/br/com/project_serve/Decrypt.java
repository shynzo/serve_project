package br.com.project_serve;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Decrypt {

	public static String decrypt(String encryptedMessage, String key, String iv) throws Exception {
		Cipher decrypt = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
		SecretKeySpec secretKey = new SecretKeySpec(Base64.getDecoder().decode(key), "AES");
		decrypt.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(Base64.getDecoder().decode(iv)));
		return new String(decrypt.doFinal(Base64.getDecoder().decode(encryptedMessage)), StandardCharsets.UTF_8);
	}

}

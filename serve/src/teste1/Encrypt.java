package teste1;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Encrypt {
	
	public byte[] encrypt(String message, String key, String iv) throws Exception {
        Cipher encrypt = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
        SecretKeySpec secretKey = new SecretKeySpec(Base64.getDecoder().decode(key), "AES");
        encrypt.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(Base64.getDecoder().decode(iv)));
        return encrypt.doFinal(message.getBytes(StandardCharsets.UTF_8));
    }
}

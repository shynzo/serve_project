package teste1;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AdvancedEncryptionStandard
{
    private static byte[] key;
    private static final String ALGORITHM = "AES";
    
    public AdvancedEncryptionStandard(byte[] key){
        AdvancedEncryptionStandard.key = key;
    }

    public static byte[] encrypt(byte[] plainText) throws Exception{
        SecretKeySpec secretKey = new SecretKeySpec(key, ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        return cipher.doFinal(plainText);
    }
    public static byte[] decrypt(byte[] cipherText) throws Exception{
        SecretKeySpec secretKey = new SecretKeySpec(key, ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        return cipher.doFinal(cipherText);
    }
}
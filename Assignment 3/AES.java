
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;


public class AES {
    public static SecretKey key;
    public static IvParameterSpec iv;

    public static void generateKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128); 
        key = keyGenerator.generateKey();
    }

    public static void generateIV() {
        byte[] ivBytes = new byte[16]; 
        new SecureRandom().nextBytes(ivBytes);
        iv = new IvParameterSpec(ivBytes);
    }

    public static byte[][] encrypt(String message) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key, iv);
        byte[] ciphertext = cipher.doFinal(message.getBytes(StandardCharsets.US_ASCII));
        return new byte[][]{iv.getIV(), ciphertext};
    }



    public static String decrypt(byte[] ivBytes, byte[] ciphertext) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);
        cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);
        byte[] plaintext = cipher.doFinal(ciphertext);
        return new String(plaintext, StandardCharsets.US_ASCII);
    }



    public static void main(String[] args) throws Exception {

        
       String message = "Sanjivani College of Engineering Kopargaon";

        System.out.print("The message is: "+message);
        System.out.println();
        
        
        generateKey();
        generateIV();




        byte[][] encryptedData = encrypt(message);
       

        String cipherTextBase128 = Base64.getEncoder().encodeToString(encryptedData[1]);
        System.out.println("Cipher Text will be" + cipherTextBase128);

        String decryptedText = decrypt(encryptedData[0], encryptedData[1]);

        
        if (decryptedText.equals(message)) {
            System.out.println("Plain Text will be: " + decryptedText);
        } else {
            System.out.println("Your Message is corrupted!");
        }

        
    }
}
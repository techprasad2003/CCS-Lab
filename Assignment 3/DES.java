
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Scanner;

public class DES {
    public static SecretKey key;
    public static IvParameterSpec iv;

    public static void generateKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
        keyGenerator.init(56); 
        key = keyGenerator.generateKey();
    }

    public static void generateIV() {
        byte[] ivBytes = new byte[8]; 
        new SecureRandom().nextBytes(ivBytes);
        iv = new IvParameterSpec(ivBytes);
    }

    public static byte[][] encrypt(String message) throws Exception {
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key, iv);
        byte[] ciphertext = cipher.doFinal(message.getBytes(StandardCharsets.US_ASCII));
        return new byte[][]{iv.getIV(), ciphertext};
    }



    public static String decrypt(byte[] ivBytes, byte[] ciphertext) throws Exception {
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);
        cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);
        byte[] plaintext = cipher.doFinal(ciphertext);
        return new String(plaintext, StandardCharsets.US_ASCII);
    }



    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
       

        System.out.print("Enter the message to send: ");
        String message = sc.nextLine();
        sc.close();
        generateKey();
        generateIV();




        byte[][] encryptedData = encrypt(message);

        String cipherTextBase64 = Base64.getEncoder().encodeToString(encryptedData[1]);
        System.out.println("Cipher Text will be" + cipherTextBase64);

        String decryptedText = decrypt(encryptedData[0], encryptedData[1]);




        
        if (decryptedText.equals(message)) {
            System.out.println("Plain Text will be: " + decryptedText);
        } else {
            System.out.println("Your Message is corrupted!");
        }

        
    }
}
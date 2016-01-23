package cmi.bdo.oauth.security;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.security.AlgorithmParameters;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.UUID;

/**
 * An encryption utility to encrypt strings to sha-256 or sha-512 hash format.
 *
 * @author Jonathan Leijendekker
 */
public class Encryptor {

    // ===================================
    // SHA-256 encryption
    // ===================================

    /**
     * Encrypt the string into sha-256 hash format
     *
     * @param s
     * @return Encrypted string
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public static String sha256(String s) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        final MessageDigest md = MessageDigest.getInstance("SHA-256");

        md.update(s.getBytes());
        byte[] digest = md.digest();

        return convertByteToHex(digest);
    }

    /**
     * Encrypt the string into sha-256 hash format. Requires java 8 and above
     *
     * @param s
     * @return Encrypted string using the Base64 encoder
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public static String sha256Base64(String s) throws NoSuchAlgorithmException {
        final MessageDigest md = MessageDigest.getInstance("SHA-256");

        md.update(s.getBytes());
        byte[] digest = md.digest();

        String hexString = Base64.getEncoder().encodeToString(digest);

        return hexString;

    }

    // ===================================
    // SHA-512 encryption
    // ===================================

    /**
     * Encrypt the string into sha-512 hash format
     *
     * @param s
     * @return Encrypted string
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public static String sha512(String s) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        final MessageDigest md = MessageDigest.getInstance("SHA-512");

        md.update(s.getBytes());
        byte[] digest = md.digest();

        return convertByteToHex(digest);
    }

    /**
     * Encrypt the string into sha-512 hash format. Requires java 8 and above
     *
     * @param s
     * @return Encrypted string using the Base64 encoder
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public static String sha512Base64(String s) throws NoSuchAlgorithmException {
        final MessageDigest md = MessageDigest.getInstance("SHA-512");

        md.update(s.getBytes());
        byte[] digest = md.digest();

        String hexString = Base64.getEncoder().encodeToString(digest);

        return hexString;

    }

    private static String convertByteToHex(byte data[]) {
        StringBuffer hexData = new StringBuffer();
        for (int byteIndex = 0; byteIndex < data.length; byteIndex++)
            hexData.append(Integer.toString((data[byteIndex] & 0xff) + 0x100, 16).substring(1));

        return hexData.toString();
    }

    // ===================================
    // AES-256 encryption
    // ===================================
    private static int iterations = 65536;
    private static int keySize = 256;
    private static byte[] ivBytes;

    private static SecretKey secretKey;

    /**
     * Encrypt the text using AES
     *
     * @param text
     * @return
     * @throws Exception
     */
    public static String aesEncrypt(String text) throws Exception {

        keySize = Cipher.getMaxAllowedKeyLength("AES");

        if(keySize > 256)
            keySize = 256;

        char[] plaintext = text.toCharArray();

        byte[] saltBytes = UUID.randomUUID().toString().getBytes();

        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        PBEKeySpec spec = new PBEKeySpec(plaintext, saltBytes, iterations, keySize);
        secretKey = skf.generateSecret(spec);
        SecretKeySpec secretSpec = new SecretKeySpec(secretKey.getEncoded(), "AES");

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretSpec);
        AlgorithmParameters params = cipher.getParameters();
        ivBytes = params.getParameterSpec(IvParameterSpec.class).getIV();
        byte[] encryptedTextBytes = cipher.doFinal(String.valueOf(plaintext).getBytes("UTF-8"));

        return DatatypeConverter.printBase64Binary(encryptedTextBytes);
    }

    /**
     * Decrypt the encrypted text using AES
     *
     * @param text
     * @return
     * @throws Exception
     */
    public static String aesDecrypt(String text) throws Exception {

        char[] encryptedText = text.toCharArray();

        byte[] encryptedTextBytes = DatatypeConverter.parseBase64Binary(new String(encryptedText));
        SecretKeySpec secretSpec = new SecretKeySpec(secretKey.getEncoded(), "AES");

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretSpec, new IvParameterSpec(ivBytes));

        byte[] decryptedTextBytes = null;

        try {
            decryptedTextBytes = cipher.doFinal(encryptedTextBytes);
        }   catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }   catch (BadPaddingException e) {
            e.printStackTrace();
        }

        return new String(decryptedTextBytes);

    }
}

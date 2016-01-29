package cmi.bdo.oauth;

import cmi.bdo.oauth.security.Encryptor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * @author Jonathan Leijendekker
 *         Date: 01/24/2016
 *         Time: 5:00 PM
 */
public class EncryptorTest {

    public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        System.out.println(Encryptor.sha512("password"));
        System.out.println(Encryptor.bcrypt("password"));

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        System.out.println(bCryptPasswordEncoder.encode("password"));
        System.out.println(bCryptPasswordEncoder.encode("password"));
    }

}

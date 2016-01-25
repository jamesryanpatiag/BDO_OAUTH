package cmi.bdo.oauth;

import cmi.bdo.oauth.security.Encryptor;

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
    }

}

package cmi.bdo.oauth;

/**
 * @author Jonathan Leijendekker
 *         Date: 01/28/2016
 *         Time: 7:59 PM
 */
public class StringReplaceTest {

    public static void main(String[] args) {
        String s = "httpss://localhost/";
        System.out.println(s.replaceFirst("https://|http://", ""));
    }

}

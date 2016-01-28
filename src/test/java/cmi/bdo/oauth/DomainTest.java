package cmi.bdo.oauth;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

/**
 * @author Jonathan Leijendekker
 *         Date: 01/24/2016
 *         Time: 2:34 AM
 */
public class DomainTest {

    public static void main(String[] args) throws URISyntaxException, MalformedURLException {
        String url = "localhost:8000/?name=asd";

        //String domain = DomainServiceUtil.getDomain(url);

        //System.out.println(domain);

        String appendedUri = DomainServiceUtil.appendUri(url, "name=Jonathan").toString();

        System.out.println(appendedUri);
    }

}

package cmi.bdo.oauth;

import cmi.bdo.oauth.util.DomainUtil;

import java.net.URISyntaxException;

/**
 * @author Jonathan Leijendekker
 *         Date: 01/24/2016
 *         Time: 2:34 AM
 */
public class DomainTest {

    public static void main(String[] args) throws URISyntaxException {
        String url = "http://www.domainsample.com/?name=";

        String domain = DomainUtil.getDomain(url);

        System.out.println(domain);

        String appendedUri = DomainUtil.appendUri(url, "name=Jonathan").toString();

        System.out.println(appendedUri);
    }

}

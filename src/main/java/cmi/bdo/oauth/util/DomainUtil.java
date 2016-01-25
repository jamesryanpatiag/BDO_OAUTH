package cmi.bdo.oauth.util;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author Jonathan Leijendekker
 *         Date: 01/24/2016
 *         Time: 2:34 AM
 */
public class DomainUtil {

    /**
     * Get the domain name from a url. Returns null if the url can't be parsed to a domain name
     *
     * @param url
     * @return
     */
    public static String getDomain(String url) {

        if (!url.startsWith("http://") && !url.startsWith("https://"))
            url = "http://" + url;

        URI uri;
        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            uri = null;
        }

        String hostUri = null;
        if (uri != null) hostUri = uri.getHost();

        String domain = null;
        if (hostUri != null) domain = hostUri.startsWith("www.") ? hostUri.substring(4) : hostUri;

        return domain;
    }

}

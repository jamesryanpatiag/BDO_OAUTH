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

        url = appendScheme(url);

        URI uri;
        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            uri = null;
        }

        String authority = null;
        if (uri != null) authority = uri.getAuthority();

        String domain = null;
        if (authority != null) domain = authority.startsWith("www.") ? authority.substring(4) : authority;

        return domain;
    }

    public static URI appendUri(String uri, String appendQuery) throws URISyntaxException {

        uri = appendScheme(uri);

        URI oldUri = new URI(uri);

        String newQuery = oldUri.getQuery();
        if (newQuery == null) {
            newQuery = appendQuery;
        } else if (oldUri.toString().endsWith(appendQuery.substring(0, appendQuery.indexOf("=") + 1))) {
            newQuery += appendQuery.substring(appendQuery.indexOf("=") + 1, appendQuery.length());
        } else {
            newQuery += "&" + appendQuery;
        }

        URI newUri = new URI(oldUri.getScheme(), oldUri.getAuthority(),
                oldUri.getPath(), newQuery, oldUri.getFragment());

        //if (newUri.toString().startsWith("http://") || newUri.toString().startsWith("https://"))
        //    newUri = new URI(newUri.toString().replaceFirst("http://|https://", ""));

        return newUri;
    }

    private static String appendScheme(String url) {
        if (!url.startsWith("http://") && !url.startsWith("https://"))
            url = "http://" + url;

        return url;
    }

}

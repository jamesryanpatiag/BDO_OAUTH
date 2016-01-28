package cmi.bdo.oauth;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author Jonathan Leijendekker
 *         Date: 01/24/2016
 *         Time: 2:34 AM
 */
public class DomainServiceUtil {

    private static URI oldUri;

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
        if (uri != null) hostUri = uri.getAuthority();

        String domain = null;
        if (hostUri != null) domain = hostUri.startsWith("www.") ? hostUri.substring(4) : hostUri;

        return domain;
    }

    public static URI appendUri(String uri, String appendQuery) {

        if (!uri.startsWith("http://") && !uri.startsWith("https://"))
            uri = "http://" + uri;

        try {
            oldUri = new URI(uri);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        System.out.println(oldUri);

        System.out.println(oldUri.getHost());

        String newQuery = oldUri.getQuery();
        if (newQuery == null) {
            newQuery = appendQuery;
        } else if (oldUri.toString().endsWith(appendQuery.substring(0, appendQuery.indexOf("=") + 1))) {
            newQuery += appendQuery.substring(appendQuery.indexOf("=") + 1, appendQuery.length());
        } else {
            newQuery += "&" + appendQuery;
        }

        URI newUri = null;
        try {
            newUri = new URI(oldUri.getScheme(), oldUri.getAuthority(),
                    oldUri.getPath(), newQuery, oldUri.getFragment());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return newUri;
    }

}

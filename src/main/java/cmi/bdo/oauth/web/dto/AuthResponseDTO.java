package cmi.bdo.oauth.web.dto;

/**
 * @author Jonathan Leijendekker
 *         Date: 01/20/2016
 *         Time: 10:14 PM
 */

public class AuthResponseDTO {

    private String clientKey;

    private String redirectUri;

    public String getClientKey() {
        return clientKey;
    }

    public void setClientKey(String clientKey) {
        this.clientKey = clientKey;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }

}

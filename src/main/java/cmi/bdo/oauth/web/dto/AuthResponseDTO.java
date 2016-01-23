package cmi.bdo.oauth.web.dto;

import cmi.bdo.oauth.validation.annotation.ValidateAuthParams;

/**
 * @author Jonathan Leijendekker
 *         Date: 01/20/2016
 *         Time: 10:14 PM
 */

@ValidateAuthParams
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

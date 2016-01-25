package cmi.bdo.oauth.web.dto;

import cmi.bdo.oauth.validation.annotation.ValidAuthParams;
import cmi.bdo.oauth.validation.annotation.ValidUser;

/**
 * @author Jonathan Leijendekker
 *         Date: 01/24/2016
 *         Time: 3:41 PM
 */
public class LoginAuthContextDTO {

    @ValidAuthParams
    private AuthResponseDTO authResponseDTO;
    @ValidUser
    private LoginDTO loginDTO;

    public AuthResponseDTO getAuthResponseDTO() {
        return authResponseDTO;
    }

    public void setAuthResponseDTO(AuthResponseDTO authResponseDTO) {
        this.authResponseDTO = authResponseDTO;
    }

    public LoginDTO getLoginDTO() {
        return loginDTO;
    }

    public void setLoginDTO(LoginDTO loginDTO) {
        this.loginDTO = loginDTO;
    }

}

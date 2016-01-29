package cmi.bdo.oauth.validation;

import cmi.bdo.oauth.service.LoginService;
import cmi.bdo.oauth.web.dto.LoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Jonathan Leijendekker
 *         Date: 01/24/2016
 *         Time: 12:07 PM
 */

@Component
public class UserValidator {

    @Autowired
    private LoginService loginService;

    public String validate(LoginDTO loginDTO) {
        return loginService.validateUserLogin(loginDTO);
    }
}

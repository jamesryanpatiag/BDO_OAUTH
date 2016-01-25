package cmi.bdo.oauth.validation.validator;

import cmi.bdo.oauth.config.Constants;
import cmi.bdo.oauth.service.LoginService;
import cmi.bdo.oauth.validation.annotation.ValidUser;
import cmi.bdo.oauth.web.dto.LoginDTO;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * @author Jonathan Leijendekker
 *         Date: 01/24/2016
 *         Time: 12:07 PM
 */
public class UserValidator implements ConstraintValidator<ValidUser, LoginDTO> {

    @Autowired
    private LoginService loginService;

    @Override
    public void initialize(ValidUser validUser) {

    }

    @Override
    public boolean isValid(LoginDTO loginDTO, ConstraintValidatorContext constraintValidatorContext) {

        constraintValidatorContext.disableDefaultConstraintViolation();

        try {
            String message = loginService.validateUserLogin(loginDTO);

            if (Constants.SUCCESS.equals(message))
                return true;

            constraintValidatorContext
                    .buildConstraintViolationWithTemplate(message)
                    .addConstraintViolation();

            return false;

        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate(Constants.VALID_USER_LOGIN_ERROR)
                    .addConstraintViolation();

            return false;
        }

    }
}

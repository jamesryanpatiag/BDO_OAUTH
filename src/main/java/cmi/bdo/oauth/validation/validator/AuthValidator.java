package cmi.bdo.oauth.validation.validator;

import cmi.bdo.oauth.config.Constants;
import cmi.bdo.oauth.service.AuthenticationService;
import cmi.bdo.oauth.validation.annotation.ValidAuthParams;
import cmi.bdo.oauth.web.dto.AuthResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Jonathan Leijendekker
 *         Date: 01/20/2016
 *         Time: 10:26 PM
 */

public class AuthValidator implements ConstraintValidator<ValidAuthParams, AuthResponseDTO> {

    @Autowired
    private AuthenticationService authenticationService;

    @Override
    public void initialize(ValidAuthParams validAuthParams) {

    }

    @Override
    public boolean isValid(AuthResponseDTO authResponseDTO, ConstraintValidatorContext constraintValidatorContext) {

        constraintValidatorContext.disableDefaultConstraintViolation();

        if (authResponseDTO.getClientKey() == null ||
                authResponseDTO.getClientKey().trim().length() == 0) {

            constraintValidatorContext
                    .buildConstraintViolationWithTemplate(Constants.BLANK_CLIENTKEY)
                    .addConstraintViolation();

            return false;
        } else if (authResponseDTO.getRedirectUri() == null ||
                authResponseDTO.getRedirectUri().trim().length() == 0) {
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate(Constants.BLANK_REDIRECTURI)
                    .addConstraintViolation();

            return false;
        } else {

            try {
                Integer.parseInt(authResponseDTO.getClientKey());
            } catch (Exception e) {
                constraintValidatorContext
                        .buildConstraintViolationWithTemplate(Constants.INVALID_CLIENTKEY_FORMAT)
                        .addConstraintViolation();
                return false;
            }

            return authenticationService.isValidClient(authResponseDTO, constraintValidatorContext);

        }
    }

}

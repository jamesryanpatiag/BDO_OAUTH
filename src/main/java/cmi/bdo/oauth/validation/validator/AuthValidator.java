package cmi.bdo.oauth.validation.validator;

import cmi.bdo.oauth.validation.annotation.ValidateAuthParams;
import cmi.bdo.oauth.web.dto.AuthResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Jonathan Leijendekker
 *         Date: 01/20/2016
 *         Time: 10:26 PM
 */

public class AuthValidator extends JdbcDaoSupport implements ConstraintValidator<ValidateAuthParams, AuthResponseDTO> {

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    @Override
    public void initialize(ValidateAuthParams validateAuthParams) {

    }

    @Override
    public boolean isValid(AuthResponseDTO authResponseDTO, ConstraintValidatorContext constraintValidatorContext) {

        constraintValidatorContext.disableDefaultConstraintViolation();

        if (authResponseDTO.getClientKey() == null ||
                authResponseDTO.getClientKey().trim().length() == 0) {

            constraintValidatorContext
                    .buildConstraintViolationWithTemplate("Client ID/key can't be null or blank")
                    .addConstraintViolation();

            return false;
        } else if (authResponseDTO.getRedirectUri() == null ||
                authResponseDTO.getRedirectUri().trim().length() == 0) {
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate("Redirect URI can't be null or blank")
                    .addConstraintViolation();

            return false;
        } else {

            /*getJdbcTemplate().queryForObject(
                    "SELECT 1 " +
                            "FROM client " +
                            "WHERE ",
                    new Object[] {""}
            );*/

        }

        return false;
    }

}

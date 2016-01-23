package cmi.bdo.oauth.validation.validator;

import cmi.bdo.oauth.config.Constants;
import cmi.bdo.oauth.validation.annotation.ValidateAuthParams;
import cmi.bdo.oauth.web.dto.AuthResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

            int clientKey = 0;

            try {
                clientKey = Integer.parseInt(authResponseDTO.getClientKey());
            } catch(Exception e) {
                constraintValidatorContext
                        .buildConstraintViolationWithTemplate(Constants.INVALID_CLIENTKEY_FORMAT)
                        .addConstraintViolation();
                return false;
            }

            final String sql = "SELECT 1" +
                    "   FROM bdo_oauth.client" +
                    " WHERE client_key = ? " +
                    "   AND client_uri = ?" +
                    "   AND client_active = 1" +
                    " LIMIT 1; ";

            PreparedStatement ps;

            ResultSet rs;

            Connection conn = getConnection();

            try {
                ps = conn.prepareStatement(sql);
                ps.setInt(1, clientKey);
                ps.setString(2, authResponseDTO.getRedirectUri());

                rs = ps.executeQuery();

                if (rs != null) {
                    if (rs.next())
                        return true;

                    // return an error message if the resultset did not returned a row
                    constraintValidatorContext
                            .buildConstraintViolationWithTemplate("Client key and Redirect URI was not found")
                            .addConstraintViolation();
                    return false;
                }

            } catch (SQLException e) {
                e.printStackTrace();

                constraintValidatorContext
                        .buildConstraintViolationWithTemplate("Exception thrown: " + e.getMessage())
                        .addConstraintViolation();
                return false;
            }

        }
        return false;
    }

}

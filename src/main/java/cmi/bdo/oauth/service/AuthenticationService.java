package cmi.bdo.oauth.service;

import cmi.bdo.oauth.config.Constants;
import cmi.bdo.oauth.web.dto.AuthResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import javax.validation.ConstraintValidatorContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Jonathan Leijendekker
 *         Date: 01/23/2016
 *         Time: 2:09 PM
 */

@Service
@Transactional
public class AuthenticationService {

    @Autowired
    private DataSource dataSource;

    public boolean isValidClient(AuthResponseDTO authResponseDTO, ConstraintValidatorContext constraintValidatorContext) {
        final String sql = "SELECT 1" +
                "   FROM bdo_oauth.client" +
                " WHERE client_key = ? " +
                "   AND client_uri = ?" +
                "   AND client_active = 1" +
                " LIMIT 1; ";

        PreparedStatement ps = null;

        ResultSet rs = null;

        Connection conn = null;

        try {

            conn = dataSource.getConnection();

            ps = conn.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(authResponseDTO.getClientKey()));
            ps.setString(2, authResponseDTO.getRedirectUri());

            rs = ps.executeQuery();

            if (rs != null) {
                if (rs.next())
                    return true;

                // return an error message if the resultset did not returned a row
                constraintValidatorContext
                        .buildConstraintViolationWithTemplate(Constants.CLIENTKEY_REDIRECTURI_NOT_FOUND)
                        .addConstraintViolation();
                return false;
            }

            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null || !ps.isClosed())
                    ps.close();
                if (rs != null || !rs.isClosed())
                    rs.close();
                if (conn != null || !conn.isClosed())
                    conn.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }

        return false;
    }

}

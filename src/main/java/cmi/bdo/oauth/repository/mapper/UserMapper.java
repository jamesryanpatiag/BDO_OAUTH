package cmi.bdo.oauth.repository.mapper;

import cmi.bdo.oauth.web.dto.UserDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Jonathan Leijendekker
 *         Date: 01/24/2016
 *         Time: 11:35 PM
 */
public class UserMapper implements RowMapper {
    @Override
    public UserDTO mapRow(ResultSet rs, int i) throws SQLException {
        return new UserDTO() {
            {
                setId(rs.getLong("user_id"));
                setUserName(rs.getString("user_name"));
                setFirstName(rs.getString("user_first"));
                setMiddleName(rs.getString("user_middle"));
                setLastName(rs.getString("user_last"));
                setEmail(rs.getString("user_email"));
                setPassword(rs.getString("user_password"));
                setActive(rs.getInt("user_active"));
                setLdap(rs.getInt("user_ldap"));
                setCreated(rs.getString("user_created"));
                setUpdated(rs.getString("user_updated"));
            }
        };
    }
}

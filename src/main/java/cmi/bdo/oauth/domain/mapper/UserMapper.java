package cmi.bdo.oauth.domain.mapper;

import cmi.bdo.oauth.domain.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Jonathan Leijendekker
 *         Date: 1/29/2016
 *         Time: 3:10 PM
 */
public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int i) throws SQLException {
        User user = new User() {
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

        return user;
    }
}
